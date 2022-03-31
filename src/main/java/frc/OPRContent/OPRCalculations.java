package frc.OPRContent;

import java.lang.Math;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.Iterator;
import java.util.List;
import frc.Jama.Matrix;


/** OPR calculator for 6 team matches (two 3-team alliances)
can either implement Cholesky decomposition or MMSE depending on type of dataset, where
the former is recommended for large datasets, use MMSE for smaller datasets
*/

//pulls data from Match class (get data per each match)
public class OPRCalculations{
    private static OPRProcessing processing = new OPRProcessing();
    private static OPRList oprList = new OPRList();

    //populates playingTeams[match index][red=0 or blue=1][team in alliance], team numbers (reads from oprList)
    public static int[][][] getPlayingTeams(int teamsPerAlliance) {
        int[][][] playingTeams = new int[processing.getMatchListSize()][2][teamsPerAlliance];
        //List<Match> matchesList = processing.getMatchList();
        for (int i = 0; i < processing.getMatchListSize(); i++) { 
            for (int k = 0; k < teamsPerAlliance; k++) {
                playingTeams[i][0][k] = oprList.getRedTeamNumbers().get(i*3+k);
                playingTeams[i][1][k] = oprList.getBlueTeamNumbers().get(i*3+k);
            }
        }
        return playingTeams;
    }

    //populates teamList[team number]
    public static int[] getTeams(int teamsPerAlliance, int[][][] playingTeams) {
        Set<Integer> sortedTeams = new TreeSet<>();
        for(int i = 0; i < playingTeams.length; i++) {
            for(int j = 0; j < teamsPerAlliance; j++) { //usually 3 teams
                sortedTeams.add(playingTeams[i][0][j]); //red alliance in 0th
                sortedTeams.add(playingTeams[i][1][j]); //blue alliance in 1st
            }
        }
        int[] teamList = new int[playingTeams.length];
        Iterator<Integer> teamHashSet = sortedTeams.iterator();
        int i = 0;
        while (teamHashSet.hasNext()) {
            teamList[i++] = teamHashSet.next();
        }
        return teamList;
    }

    //populates scores[#match points][red=0 or blue=1]  (reads from oprList based off teamList ordering)
    //score will be repeated each time per team
    public static int[][] getTeamScores(int teamsPerAlliance, int[] teamList) {
        int[][] scores = new int[processing.matches.size()*3][2];
        //Iterator<Integer> teamListSet = teamList.iterator(); 
        int teamSize = teamList.length;
        while (teamSize >= 0) {
            for (int i = 0; i < processing.matches.size()*3; i++) {
                for (int j = 0; j < 2; j++) {
                   if (j == 0) {
                        scores[i][j] = oprList.getRedTeamScores().get(i);
                   } else {
                        scores[i][j] = oprList.getBlueTeamScores().get(i);
                   }
                }
            }
            teamSize--;
        }
        return scores;
    }

    //lower triangular matrices
    public static Matrix getTriangular(Matrix matrix, int numRows, int numColumns) {
        matrix = new Matrix(numRows, numColumns);
        if (numRows != numColumns) {
            throw new IllegalArgumentException("numRows doesn't match numColumns");
        } else {
            for(int i = 0; i < numRows; i++) {
                for(int j = 0; j < numColumns; j++) {
                    if (j > i) {
                        matrix.set(i, j, 0);
                    }
                }
            }
        }
        return matrix;
    }

   //teamList[team number], scores[#match points][red=0 or blue=1], playingTeams[match index][red=0 or blue=1][#teams per alliance]
    public static double[] computeOPRwithMMSE(int[] teamList, int teamsPerAlliance, int[][][] playingTeams, int[][] scores) {
        List<Integer> TeamList = new ArrayList<>();
        int numTeams = teamList.length;
        for (int i = 0; i < numTeams; i++) {
            TeamList.add(teamList[i]);
        }
        int numScoredMatches = 0;
        for (int i = 0; i < processing.matches.size()*3; i++) {
            for (int j = 0; j < 2; j++) {
                if(scores[i][j] > 0) {
                    numScoredMatches++;
                }
            }
        }

        //A matrices contain array of which teams are in which matches
        //M matrices contain array of alliance scores
        Matrix Ar = new Matrix(numScoredMatches, numTeams); //red alliance
        Matrix Ab = new Matrix(numScoredMatches, numTeams); //blue alliance
        Matrix Mr = new Matrix(numScoredMatches, 1);
        Matrix Mb = new Matrix(numScoredMatches, 1);
        Matrix Ao = new Matrix(2*numScoredMatches, numTeams);
        Matrix Mo = new Matrix(2*numScoredMatches, 1);
        int match = 0;
        double scoreSum = 0;
        for (int i = 0; i < scores.length; i++) {
            if (scores[i][0] > 0) {
                for (int j = 0; j < teamsPerAlliance; j++) {
                    //first index is the row, second is the column, last is value
                    Ar.set(match, TeamList.indexOf(playingTeams[i][0][j]), 1); 
                    Ab.set(match, TeamList.indexOf(playingTeams[i][1][j]), 1);
                }
            //row, column, score
            Mr.set(match, 0, scores[i][0]);
            Mb.set(match, 1, scores[i][1]);
            }
            scoreSum += scores[i][0];
            scoreSum += scores[i][1];
            match++;
        }
            //set matrices (start and end row index, start and end column index, matrix used)
            //set Ao as a combination of submatrices Ar and Ab
            Ao.setMatrix(0, numScoredMatches -1, 0, numTeams-1, Ar);
            Ao.setMatrix(numScoredMatches, 2*numScoredMatches -1, 0, numTeams, Ab);
            double averageTeamOffenseScore = scoreSum/(2*numScoredMatches*numTeams);
            for (int i = 0; i < numScoredMatches; i++) {
                //adjust single robot's OPR by subtracting the other two teams' on alliance
                Mr.set(i, 0, Mr.get(i, 0) - 2*averageTeamOffenseScore);
                Mb.set(i, 0, Mb.get(i, 0) - 2*averageTeamOffenseScore);
            } 
            //set Mo as a combination of submatrices Mr and Mb
            Mo.setMatrix(0, numScoredMatches-1, 0, 0, Mr);
            Mo.setMatrix(numScoredMatches, 2*numScoredMatches-1, 0, 0, Mb);

            if (teamList.length > 100) {
                Ao = getTriangular(Ao, 2*numScoredMatches, numTeams);
            }

            double MrTotal = 0;
            double MbTotal = 0;
            for (int i = 1; i < numScoredMatches; i++) {
                //sum of total scores per alliance per match
                MrTotal += Mr.get(i, 1);
                MbTotal += Mb.get(i, 1);
            }
            double avMr = MrTotal/numScoredMatches;
            double avMb = MbTotal/numScoredMatches;
            //will be squared variance of scores for each alliance
            double MrPerAlliance = 0;
            double MbPerAlliance = 0;
            //will be squared variance of scores for each team on the respective alliances
            for (int i = 0; i < numScoredMatches; i++) {
                MrPerAlliance += Math.pow(avMr-Mr.get(i, 1), 2);
                MbPerAlliance += Math.pow(avMb-Mb.get(i, 1), 2);
            }
            MrPerAlliance = MrPerAlliance/numScoredMatches;
            MbPerAlliance = MbPerAlliance/numScoredMatches;
            double mmse = (MrPerAlliance + MbPerAlliance)/2;
            //the more random the scores, the larger mmse is (generally between 0-3)
            if (mmse > 3) {
                mmse = 3;
            } else if (mmse < 0) {
                mmse = 0;
            }    

            Matrix AoMatrixInverse;
            try{
                //Ao'Ao+mmse*identity matrix of Ao
                AoMatrixInverse = Ao.transpose().times(Ao).plus(Matrix.identity(numTeams, numTeams).times(mmse)).inverse();
            } catch (Exception e) {
                return null; //matrix isn't invertible
            }
        double[] opr = new double[teamList.length];
        Matrix OPR = AoMatrixInverse.times(Ao.transpose().times(Mo));
        for (int i = 0; i < numTeams; i++) {
            OPR.set(i, 0, OPR.get(i, 0) + averageTeamOffenseScore);
            opr[i] = OPR.get(i, 0);
        }
        return opr;
    }

    //with Chloesky Decomposition (implemented in mmse method)
    public static double[] computeOPRwithChloesky(double mmse, int[] teamList, int teamsPerAlliance, int[][][] playingTeams, int[][] scores, double averageTeamOffenseScore, int numScoredMatches, int numTeams, Matrix Ao, Matrix Mo) {
        computeOPRwithMMSE(teamList, teamsPerAlliance, playingTeams, scores);
        Matrix AoNew = getTriangular(Ao, 2*numScoredMatches, numTeams);
        Matrix AoMatrixInverseNew;
        try {
            AoMatrixInverseNew = AoNew.transpose().times(AoNew);
        } catch (Exception e) {
            return null;
        }
        double[] opr = new double[teamList.length];
        Matrix OPR = AoMatrixInverseNew.times(AoNew.transpose().times(Mo));
        for (int i = 0; i < numTeams; i++) {
            OPR.set(i, 0, OPR.get(i, 0) + averageTeamOffenseScore);
            opr[i] = OPR.get(i, 0);
        }
        return opr;
    }

    public static double[] getData(int[] teamList, int teamsPerAlliance, int[][][] playingTeams, int scores[][], int[] opr) {
        return computeOPRwithMMSE(teamList, teamsPerAlliance, playingTeams, scores);
    }
}

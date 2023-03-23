package frc.data;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import frc.OPRContent.OPRCalculations;
import frc.OPRContent.OPRProcessing;
import frc.OPRContent.TeamWinPercentage;



//saves scores as csv file (array of team numbers paired with corresponding OPRs and win percentages)
public class CSVofOPRs {
    public static final String comma = ",";
    public static final String separator = "\n";
    public static final String header = "teamNumber,OPR,winPercentage";
    static OPRProcessing processing = new OPRProcessing();


    public static FileWriter getCSVofOPRs(int[] teamList, double[] oprs, double[] winPercentage) {
        Set<Double> opr = new TreeSet<>();
        Set<Double> percentage = new TreeSet<>();
        teamList = OPRCalculations.getTeams();
        int[][][] playingTeams = OPRCalculations.getPlayingTeams();
        int[][] scores = OPRCalculations.getTeamScores(3, teamList);

        double[] oprList = new double[teamList.length];
        double[] percentageList  = new double[teamList.length];
        Iterator<Double> OPRHashSet = opr.iterator();
        Iterator<Double> percentageHashSet = percentage.iterator();
        int j = 0;

        for (int i = 0; i < teamList.length; i++) {
            for (double e : OPRCalculations.computeOPRwithMMSE()) {
                opr.add(e);
            }

            while (OPRHashSet.hasNext()) {
            oprList[j++] = OPRHashSet.next();
            percentageList[j++] = percentageHashSet.next();
            
            percentage.add((double) TeamWinPercentage.getPercentage(teamList[i]));
        }

        }

        FileWriter writer = null;

        try {
            //intialize writer
            writer = new FileWriter("TeamOPR.csv"); //to do: specify filepath
            writer.append(header);
            writer.append(comma);
    
            Iterator<Double> score = opr.iterator();
            j = 0;
            while (score.hasNext()) {
                //create each line of the CSV file
                score.next();
                writer.append(String.valueOf(teamList[j++]));
                writer.append(comma);
                writer.append(String.valueOf(oprList[j++]));
                writer.append(comma);
                writer.append(String.valueOf(percentageList[j++]));
                writer.append(separator);
            }
            System.out.println("CSV of OPRs creation successful");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException io) {
                System.out.println("File Writer did not close");
                io.printStackTrace();
            }
        }
        return writer;
    }
}

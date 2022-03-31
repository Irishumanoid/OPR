package frc.OPRContent;

import java.util.List;
import java.util.ArrayList;


//this class converts all data from DataIO to a format easily accessible by OPRCalculations 
public class OPRList {
    OPRProcessing oprProcessing = new OPRProcessing();
    List<Integer> teamListRed = new ArrayList<>();
    
    public List<Integer> getRedTeamNumbers() {
        for (Match element : oprProcessing.matches) { 
            for (int i : element.getRedTeamNumbers())
                teamListRed.add(i);
        }
        return teamListRed;
    }

    public List<Integer> getBlueTeamNumbers() {
        List<Integer> teamListBlue = new ArrayList<>();
        for (Match element : oprProcessing.matches) {
            for (int i : element.getBlueTeamNumbers()) 
                teamListBlue.add(i);
        }
        return teamListBlue;
    }

    //puts the teams scores into array using stream
    public List<Integer> getRedTeamScores() {
        List<Integer> teamScoresRed = new ArrayList<>();
        for (Match element : oprProcessing.matches) {
                teamScoresRed.add(element.getRedTeamScore());
        }      
        return teamScoresRed;     
    }    

    
    public List<Integer> getBlueTeamScores() {
        List<Integer> teamScoresBlue = new ArrayList<>();
        for (Match element : oprProcessing.matches) {
           teamScoresBlue.add(element.getBlueTeamScore());
        }
        return teamScoresBlue;
    }

    //puts winning teams into an array using stream (red win = true, blue win = false)
    public List<Boolean> getWinner() {
        List<Boolean> winner = new ArrayList<>();
        for (Match element : oprProcessing.matches) {
            winner.add(element.getWinningTeam());
        }
        return winner;
    }
}


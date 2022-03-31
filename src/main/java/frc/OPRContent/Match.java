package frc.OPRContent;

import java.io.Serializable;


public class Match implements Serializable {
    private int[] redTeamNumbers;
    private int redTeamScore;
    private int[] blueTeamNumbers;
    private int blueTeamScore;
    private boolean winningTeam;

    public int[] getRedTeamNumbers() {
        return redTeamNumbers;
    }

    public int getR0() {
        return redTeamNumbers[0];
    }

    public int getR1() {
        return redTeamNumbers[1];
    }

    public int getR2() {
        return redTeamNumbers[2];
    }

    public int getB0() {
        return blueTeamNumbers[0];
    }

    public int getB1() {
        return blueTeamNumbers[1];
    }

    public int getB2() {
        return blueTeamNumbers[2];
    }

    public int getRedTeamScore() {
        return redTeamScore;
    }

    public int[] getBlueTeamNumbers() {
        return blueTeamNumbers;
    }

    public int getBlueTeamScore() {
        return blueTeamScore;
    }

    public boolean getWinningTeam() {
        return winningTeam;
    }

    public Match(int[] redTeamNumbers, int redTeamScore, int[] blueTeamNumbers, int blueTeamScore, boolean winningTeam) {
        this.redTeamNumbers = redTeamNumbers;
        this.blueTeamNumbers = blueTeamNumbers;
        this.redTeamScore = redTeamScore;
        this.blueTeamScore = blueTeamScore;
        this.winningTeam = winningTeam;
    }


    @Override
    public String toString() {
        return "Match(redTeamNumbers="+redTeamNumbers+", redTeamScore="+redTeamScore+", blueTeamNumbers="+blueTeamNumbers+", blueTeamScore="+blueTeamScore+", winningTeam="+winningTeam+"),";
    }
}



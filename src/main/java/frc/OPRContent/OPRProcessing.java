package frc.OPRContent;

import java.util.ArrayList;
import java.util.List;

import frc.data.DataIO;

public class OPRProcessing {

    public List<Match> matches = new ArrayList<>();
    
    public void assign() {
        matches.add(new Match(new int[]{2,2,2}, 3, new int[]{2,2,2}, 7, false));
        matches.add(new Match(new int[]{2,6,2}, 9, new int[]{0,2,2}, 4, true));
    }

    //public List<Match> matches = DataIO.readMatchData("ChainLynx.matches");

    public List<Match> getMatchList() {
        assign();
       return matches;
    }

    public int getMatchListSize() {
        return matches.size();
    }
    
}

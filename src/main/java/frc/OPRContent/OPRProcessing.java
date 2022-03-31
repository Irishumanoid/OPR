package frc.OPRContent;

import java.util.List;

import frc.data.output.DataIO;
//import lombok.Data;  //can't get this import to work so will implement w/o lombok @Data annotation

public class OPRProcessing {

    public List<Match> matches = DataIO.readMatchData("ChainLynx.matches");

    public List<Match> getMatchList() {
       return matches;
    }

    public int getMatchListSize() {
        return matches.size();
    }
    
}

package frc.OPRContent;

import java.util.List;
import java.util.stream.*;

/**read number of matches a team wins (on their alliance) / number of matches a team is in
     */
public class TeamWinPercentage {
    private static OPRProcessing oprProcessing = new OPRProcessing();
    private static OPRList oprList = new OPRList();

    public static double getPercentage(int currentTeamNumber) {
        int numMatchesIn = 0;
        int numMatchesWon = 0;
        List<Integer> nums = oprList.getRedTeamNumbers().stream().filter(e -> e.equals(currentTeamNumber)).collect(Collectors.toList());
        numMatchesIn += nums.size();
        for (Match element : oprProcessing.matches) {
            if(currentTeamNumber == element.getR0() || currentTeamNumber == element.getR1() || currentTeamNumber == element.getR2() && element.getWinningTeam() == true) {
                numMatchesWon++;
            }  
        }
        List<Integer> nums1 = oprList.getBlueTeamNumbers().stream().filter(e -> e.equals(currentTeamNumber)).collect(Collectors.toList());
        numMatchesIn += nums1.size();
        for (Match element : oprProcessing.matches) {
            if(currentTeamNumber == element.getB0() || currentTeamNumber == element.getB1() || currentTeamNumber == element.getB2() && element.getWinningTeam() == false) {
                numMatchesWon++;
            }  
        }
            return numMatchesWon/numMatchesIn;
            
    }
    

    public static double returnTeamWinPercentage(int currentTeamNumber) {
        return getPercentage(currentTeamNumber);
    }
}

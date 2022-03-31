package frc.robot.subsystems;

import frc.robot.OPRCalculations;
import frc.robot.OPRList;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

//saves scores as csv file (array of team numbers paired with corresponding OPRs)
public class CSVofOPRs {
    public static final String comma = ",";
    public static final String separator = "\n";
    public static final String format = "teamNumber, OPR";

    public static getCSVofOPRs(int[] teamList, int[] opr) {
        int counterVal = 0;
        List OPRList = new ArrayList();

        for (int i = 0; i < teamList.length; i++) {
            counterVal = i;
            TeamOPR team.append(counterVal) = new TeamOPR(teamNumber[i], opr[i]);
            OPRList.add(team.append(counterVal));
        }

        FileWriter writer = null;

        try {
            //intialize writer
            writer = new FileWriter("TeamOPR.csv");
            writer.append(format);
            writer.append(comma);
        
            //create iterator to check if OPRList has any remaining entries
            Iterator iterate = OPRList.iterator();
            while (iterate.hasNext()) {
                //create each line of the CSV file
                OPRList OPRs = (OPRList) iterate.next();
                writer.append(String.valueOf(OPRs.getTeamNumber()));
                writer.append(comma);
                writer.append(String.valueOf(OPRs.getOPR()));
                writer.append(separator);
            }
            System.out.println("CSV creation successful");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException io) {
                System.out.println("File Writer did not close properly");
                io.printStackTrace();
            }
        }
    }
}

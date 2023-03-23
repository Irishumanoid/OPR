package frc.data;

import lombok.Data;

@Data
public class CSVFormat {
    public int teamNumber;
    public double oprScore;
    public double winPercentage;
    
    public CSVFormat(int teamNumber,double oprScore,double winPercentage){   
        this.teamNumber=teamNumber;
        this.oprScore=oprScore;
        this.winPercentage=winPercentage;
    }
}

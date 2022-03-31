package frc.data.output;

public class CSVFormat {
    public int teamNumber;
    public double oprScore;
    public double winPercentage;
    
    public CSVFormat(int teamNumber,double oprScore,double winPercentage){   
        this.teamNumber=teamNumber;
        this.oprScore=oprScore;
        this.winPercentage=winPercentage;
    }
    public int getTeamNumber(){
        return teamNumber;
    }
     public double getoprScore(){
        return oprScore;
    }
    public double winPercentage(){
        return winPercentage;
    }
    public void setTeamNumber(int teamNumber){
        this.teamNumber=teamNumber;
    }
     public void setoprScore(double oprScore){
       this.oprScore=oprScore;
    }
    public void setWinPercentage(double winPercentage){
        this.winPercentage=winPercentage;
    }
    @Override
    public String toString() {
        return "Book [teamNumber="+teamNumber+", oprScore="+oprScore+", winPercentage="+winPercentage+"]";
    }
}

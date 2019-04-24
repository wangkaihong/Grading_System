package BackEnd;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by wangkaihong on 2019/4/9.
 */
public class Report {
    /*
    * Return statistic data of 1-each assignment, 2-each course to Report_UI
    * including Min, Max, Average(lowest and highest score removed), Median
    */
    private String report;
    private ArrayList<Assignment> assignments;
    private Sheet sheet;

    public Report() {

    }
    public Report(ArrayList<Assignment> listAssign, Sheet sheet){
        this.assignments = listAssign;
        this.sheet = sheet;
    }
    public String to_UI() {
        return null;
    }
    public ArrayList<String> reportAssignToUI(int assignIndex){
        ArrayList<String> res = new ArrayList<String>();
        ArrayList<Double> listScore = new ArrayList<Double>();
        double min = 0;
        double max = 0;
        double ave = 0;
        double med = 0;
        double sumD = 0;
        if (assignIndex == -1){
            System.out.println("Invalid Assignment Name");
            return res;
        }
        for(Double tempD : this.sheet.getScoreColumn(assignIndex)){
            listScore.add(tempD);
            sumD = sumD + tempD;
        }
        int listSize = listScore.size();
        Collections.sort(listScore);
        min = Collections.min(listScore);
        max = Collections.max(listScore);
        ave = (sumD - min - max) / listSize;
        if(listSize % 2 == 0){
            med = (listScore.get(listSize/2) + listScore.get(listSize/2-1)) / 2;
        } else{
            med = listScore.get(listSize/2);
        }
        res.add(Double.toString(min));
        res.add(Double.toString(max));
        res.add(Double.toString(ave));
        res.add(Double.toString(med));

        return res;
    }
    public ArrayList<String> reportCourseToUI(String courseName){
        ArrayList<String> res = new ArrayList<String>();
        ArrayList<Double> listScore = new ArrayList<Double>();
        double min = 0;
        double max = 0;
        double ave = 0;
        double med = 0;
        double sumD = 0;
        for(ArrayList<Cell> listCell : this.sheet.getAllCell()){
            for(Cell tempC : listCell){
                listScore.add(tempC.getScore());
                sumD = sumD + tempC.getScore();
            }
        }
        int listSize = listScore.size();
        Collections.sort(listScore);
        min = Collections.min(listScore);
        max = Collections.max(listScore);
        ave = (sumD - min - max) / listSize;
        if(listSize % 2 == 0){
            med = (listScore.get(listSize/2) + listScore.get(listSize/2-1)) / 2;
        } else{
            med = listScore.get(listSize/2);
        }
        res.add(Double.toString(min));
        res.add(Double.toString(max));
        res.add(Double.toString(ave));
        res.add(Double.toString(med));

        return res;
    }
}

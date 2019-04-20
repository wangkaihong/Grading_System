package BackEnd;

/**
 * Created by wangkaihong on 2019/4/9.
 */
public class Assignment implements Reportable{
    private String name;
    private double total;
    private String scoring_method; // three possible fields: deduction, percentage, raw

    public Assignment() {
        this.name = "";
        this.total = 0;
        this.scoring_method = "";
    }

    public Assignment(String name, double total, String scoring_method) {
        this.name = name;
        this.total = total;
        this.scoring_method = scoring_method;
    }

    public void changeAssignment(String name, double total, String scoring_method) {
        this.name = name;
        this.total = total;
        this.scoring_method = scoring_method;
    }

    public Assignment(String n, double t, boolean s){
        name = n;
        total = t;
        scoring_method = s;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setScoring_method(String scoring_method) {
        this.scoring_method = scoring_method;
    }

    public String getName() {
        return name;
    }

    public double getTotal() {
        return total;
    }

    public String isScoring_method() {
        return scoring_method;
    }

    public Report getReport() {
        return null;
    }
}

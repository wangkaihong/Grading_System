package BackEnd;

/**
 * Created by wangkaihong on 2019/4/9.
 */
public class Assignment implements Reportable{
    private String name;
    private double total;
    private boolean scoring_method;

    public Assignment() {

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

    public void setScoring_method(boolean scoring_method) {
        this.scoring_method = scoring_method;
    }

    public String getName() {
        return name;
    }

    public double getTotal() {
        return total;
    }

    public boolean isScoring_method() {
        return scoring_method;
    }
    public Report getReport() {
        return null;
    }
}

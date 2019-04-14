package BackEnd;

import java.util.ArrayList;

/**
 * Created by wangkaihong on 2019/4/9.
 */
public class Criteria {
    private ArrayList<Double> weight;

    public Criteria() {

    }

    public ArrayList<Double> getWeight() {
        return weight;
    }

    public void setWeight(ArrayList<Double> weight) {
        this.weight = weight;
    }

    public boolean validateWeight() {
        // return true if valid, return false if invalid
        return true;
    }
}

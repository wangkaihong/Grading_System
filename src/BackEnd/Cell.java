package BackEnd;

/**
 * Created by wangkaihong on 2019/4/9.
 */
public class Cell implements Reportable {
    private Note note;
    private double score;

    public Cell() {

    }

    public Cell(Note n, double s){
        note = n;
        score = s;
    }

    public Note getNote() {
        return note;
    }

    public int setNote(Note note) {
        this.note = note;
        return 1;
    }

    public double getScore() {
        return score;
    }

    public int setScore(double score) {
        this.score = score;
        return 1;
    }
    public Report getReport() {
        return null;
    }
}

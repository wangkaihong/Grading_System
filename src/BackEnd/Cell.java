package BackEnd;

/**
 * Created by wangkaihong on 2019/4/9.
 */
public class Cell implements Reportable {
    private Note note;
    private double score;

    public Cell() {
        note = new Note();
        score = 0;
    }

    public Cell(Note n, double s){
        note = n;
        score = s;
    }

    public Note getNote() {
        return note;
    }
    public String[] getNoteContent() {
        return note.getNoteContent();
    }

    public void setNote(String note) {
        this.note.setInformation(note);
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
    public Report getReport() {
        return null;
    }
}

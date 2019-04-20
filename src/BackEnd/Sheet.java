package BackEnd;

import java.util.ArrayList;

/**
 * Created by wangkaihong on 2019/4/9.
 */
public class Sheet {
    private ArrayList<ArrayList<Cell>> cells;

    public Sheet() {
    }

    public Sheet(ArrayList<ArrayList<Cell>> c){
        cells = c;
    }

    public ArrayList<ArrayList<Cell>> getAllCell() {
        return cells;
    }
    public double getCellScore(int cor1, int cor2) {
        return cells.get(cor1).get(cor2).getScore();
    }
    public Note getCellNote(int cor1, int cor2) {
        return cells.get(cor1).get(cor2).getNote();
    }
    public int getHeight() {
        return cells.size();
    }
    public int getWidth() {
        if(cells.size() > 0) {
            return cells.get(0).size();
        }
        return 0;
    }

    public void setCell(ArrayList<ArrayList<Cell>> cell) {
        this.cells = cell;
    }
    public void setScore(int cor1,int cor2, double score) {
        cells.get(cor1).get(cor2).setScore(score);
    }
    public void setNote(int cor1,int cor2, String note) {
        cells.get(cor1).get(cor2).setNote(note);
    }

}

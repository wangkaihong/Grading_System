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
    public Cell getCell(int cor1, int cor2) {
        return cells.get(cor1).get(cor2);
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
    public int setScore(int cor1,int cor2, double score) {
        return cells.get(cor1).get(cor2).setScore(score);
    }
    public int setnote(int cor1,int cor2) {
        return cells.get(cor1).get(cor2).setNote(null);
    }

}

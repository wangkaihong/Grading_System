package BackEnd;

import java.util.ArrayList;

/**
 * Created by wangkaihong on 2019/4/9.
 */
public class Sheet {
    private ArrayList<ArrayList<Cell>> cells;

    public Sheet() {
    }

    public ArrayList<ArrayList<Cell>> getCell() {
        return cells;
    }

    public void setCell(ArrayList<ArrayList<Cell>> cell) {
        this.cells = cell;
    }
    public int setScore(int cor1,int cor2) {
        return cells.get(cor1).get(cor2).setScore(100);
    }
    public int setnote(int cor1,int cor2) {
        return cells.get(cor1).get(cor2).setNote(null);
    }

}

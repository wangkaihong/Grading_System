package BackEnd;

import java.util.ArrayList;

/**
 * Created by wangkaihong on 2019/4/9.
 */
public class Sheet {
    private ArrayList<ArrayList<Cell>> cell;

    public Sheet() {
    }

    public ArrayList<ArrayList<Cell>> getCell() {
        return cell;
    }

    public void setCell(ArrayList<ArrayList<Cell>> cell) {
        this.cell = cell;
    }
    public int setScore(int cor1,int cor2) {
        return cell.get(cor1).get(cor2).setScore(100);
    }
    public int setnote(int cor1,int cor2) {
        return cell.get(cor1).get(cor2).setNote(null);
    }

}

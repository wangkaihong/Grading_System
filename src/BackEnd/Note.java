package BackEnd;

import java.util.Date;

/**
 * Created by wangkaihong on 2019/4/9.
 */
public class Note {
    private String information;
    private Date time;

    public Note() {

    }

    public Note(String info, Date d){
        information = info;
        time = d;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}

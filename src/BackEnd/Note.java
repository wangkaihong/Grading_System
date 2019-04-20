package BackEnd;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

/**
 * Created by wangkaihong on 2019/4/9.
 */
public class Note {
    private String information;
    private String time;

    public Note() {
        time = Calendar.getInstance().getTime().toString();
        information = "";
    }
    public Note(String info) {
        time = Calendar.getInstance().getTime().toString();
        information = info;
    }
    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}

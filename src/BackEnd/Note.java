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
    private Date time;

    public Note() {
        time = Calendar.getInstance().getTime();
        information = "";
    }
    public Note(String info) {
        time = Calendar.getInstance().getTime();
        information = info;
    }
    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String[] getReadableTime() {
        // return array of String consisting of day, month, date, time, time zone, year
        return time.toString().split(" ");
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

}

package BackEnd;

import java.util.ArrayList;

/**
 * Created by wangkaihong on 2019/4/9.
 */
public class System {
    private ArrayList<Course> courses;

    public System() { // Constructor: read from IO and construct system instance

    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public int addCourse() {
        // addCourse(): input
        // return 1 if succeeded, return 2 if have course name conflict, return 3 if unknown error
        return 1;
    }
    public int deleteCourse() {
        //deleteCourse(): input
        // return 1 if succeeded, return 2 if course not found, return 3 if unknown error
        return 1;
    }
}

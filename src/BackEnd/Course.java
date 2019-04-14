package BackEnd;

import com.sun.org.apache.regexp.internal.RE;

import java.util.ArrayList;

/**
 * Created by wangkaihong on 2019/4/9.
 */
public class Course implements Reportable {
    //Course name cannot be changed
    private String courseName;
    private String lecturerName;
    private String semester;
    private Sheet sheet;
    private ArrayList<Student> students;
    private ArrayList<Assignment> assignments;
    private ArrayList<Criteria> criteria;

    public Course() {
        //Constructor

    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public Sheet getSheet() {
        return sheet;
    }

    public void setSheet(Sheet sheet) {
        this.sheet = sheet;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public ArrayList<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(ArrayList<Assignment> assignments) {
        this.assignments = assignments;
    }

    public ArrayList<Criteria> getCriteria() {
        return criteria;
    }

    public void setCriteria(ArrayList<Criteria> criteria) {
        this.criteria = criteria;
    }

    public int addStudent() {
        // return 1 if succeeded, return 2 if failed
        return 1;
    }
    public int removeStudent() {
        //return 1 if succeeded , return 2 if student not found, return 3 if unknown error
        return 1;
    }
    public int addAssignment() {
        // return 1 if succeeded, return 2 if failed
        return 1;
    }
    public int changeAssignment() {
        //return 1 if succeeded , return 2 if assignment not found, return 3 if unknown error
        return 1;
    }
    public Report getReport() {
        // tbd
        return null;
    }
    public int endCourse() {
        //return 1 if succeeded , return 2 if course not found, return 3 if unknown error
        return 1;
    }
    public double calTotal() {
        //return double score if succeeded , return -1 if unknown error
        return 1;
    }

}

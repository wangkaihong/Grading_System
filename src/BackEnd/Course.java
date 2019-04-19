package BackEnd;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.File;

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

    }
    public Course(String courseName, String lecturerName, String semester, String student_file_dir, Course previous) {
        this.courseName = courseName;
        this.lecturerName = lecturerName;
        this.semester = semester;
        this.sheet = null;
        this.students = getStudentsFromFile(student_file_dir);
        if(previous == null) {
            this.assignments = null;
            this.criteria = null;
        }
        else {
            this.assignments = previous.assignments;
            this.criteria = previous.criteria;
        }
    }
    public ArrayList<Student> getStudentsFromFile(String student_file_dir) {
        ArrayList<Student> stduent_list = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(student_file_dir));
            scanner.useDelimiter("\n");
            while (scanner.hasNext()) {
                String temp = scanner.next();
                String[] arr = temp.split(",");
                stduent_list.add(new Undergraduate(arr[0],arr[1],arr[2],arr[3],arr[4]));
            }
            scanner.close();
        }
        catch (FileNotFoundException e) {
            System.out.print("File not found!");
            return null;
        }
        catch (Exception e) {
            System.out.print("Something went wrong in importing Student information!");
            return null;
        }
        return stduent_list;
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
    public static void main(String[] args) {
        Course c = new Course("a","b","c","students.csv",null);
    }
}

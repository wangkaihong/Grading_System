package BackEnd;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

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
    private ArrayList<Criteria> criterias;
    private boolean end;

    public Course() {
        this.courseName = null;
        this.lecturerName = null;
        this.semester = null;
        this.sheet = null;
        this.students = null;
        this.assignments = null;
        this.criterias = null;
        this.end = false;
    }
    public Course(String cN, String lN, String s, Sheet sh, ArrayList<Student> stu, ArrayList<Assignment> assign, ArrayList<Criteria> cri){
        courseName = cN;
        lecturerName = lN;
        semester = s;
        sheet = sh;
        students = stu;
        assignments = assign;
        criterias = cri;
    }


    public Course(String courseName, String lecturerName, String semester, ArrayList<Student> student_list, Course previous) {
        this.courseName = courseName;
        this.lecturerName = lecturerName;
        this.semester = semester;
        this.sheet = null;
        this.students = student_list;
        if(previous == null) {
            this.assignments = null;
            this.criterias = null;
        }
        else {
            this.assignments = previous.assignments;
            this.criterias = previous.criterias;
        }
        this.end = false;
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
        return criterias;
    }

    public void setCriteria(ArrayList<Criteria> criteria) {
        this.criterias = criteria;
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

    public int addStudent(String firstName, String middleInitial, String lastName, String studentId, String emailAddress, String studentType) {
        // parameter:
        // firstName: String: First name of student
        // middleInitial: String: middleInitial of student
        // lastName: String: Last name of student
        // studentId: String: ID of student
        // emailAddress: String: Email address of student
        // studentType: String: Undergraduate or graduate
        //
        // return 1 if succeeded, return 2 if invalid firstName, return 3 if invalid middleInitial, return 4 if invalid lastName,
        // return 5 if invalid studentId, return 6 if invalid emailAddress, return 7 if invalid studentType, return 8 if unknown error
        if(firstName == null || firstName.equals("")) {
            return 2;
        }
        if(middleInitial == null || middleInitial.equals("")) {
            return 3;
        }
        if(lastName == null || lastName.equals("")) {
            return 4;
        }
        if(studentId == null || studentId.equals("")) {
            return 5;
        }
        if(emailAddress == null || emailAddress.equals("")) {
            return 6;
        }
        if(studentType == null || studentType.equals("")) {
            return 7;
        }
        try {
            if(studentType.equals("undergraduate")) {
                Student student = new Undergraduate(firstName, middleInitial, lastName, studentId, emailAddress);
                students.add(student);
                return 1;
            }
            if(studentType.equals("graduate")) {
                Student student = new Graduate(firstName, middleInitial, lastName, studentId, emailAddress);
                students.add(student);
                return 1;
            }
            else {
                return 7;
            }

        }
        catch (Exception e) {
            return 8;
        }
    }
    public int removeStudent(int index) {
        // parameters:
        // index: int: index of student in the arraylist you would like to remove
        //
        // return 1 if succeeded , return 2 if student not found, return 3 if unknown error
        try {
            if (index >= students.size() || index < 0) {
                return 2;
            } else {
                students.remove(index);
                return 1;
            }
        }
        catch (Exception e) {
            return 3;
        }
    }
    public int addAssignment(String name, double total, String scoring_method) {
        // parameters:
        // name: String: name of the assignment
        // total: double: total score of the assignment
        // scoring_method: String: scoring method of the assignment
        //
        // return 1 if succeeded, return 2 if invalid name, return 3 if invalid total, return 4 if invalid scoring_method, return 5 if unknown error

        try {
            if (name == null || name.equals("")) {
                return 2;
            }

            if (total <= 0) {
                return 3;
            }
            if (!scoring_method.equals("deduction") && !scoring_method.equals("raw") && !scoring_method.equals("percentage")) {
                return 4;
            }
            Assignment assignment = new Assignment(name, total, scoring_method);
            assignments.add(assignment);
            return 1;
        }
        catch (Exception e) {
            return 5;
        }

    }
    public int changeAssignment(int index, String name, double total, String scoring_method) {
        //parameters:
        // index: int: index of assigment you would like to change in the arraylist of assignments
        // name: String: name of the assignment
        // total: double: total score of the assignment
        // scoring_method: String: scoring method of the assignment
        //
        //return 1 if succeeded , return 2 if assignment not found, return 3 if invalid name, return 4 if invalid total, return 5 if invalid scoring_method, return 6 if unknown error
        try {
            if (index >= assignments.size() || index < 0) {
                return 2;
            }
            if (name == null || name.equals("")) {
                return 3;
            }
            if (total <= 0) {
                return 4;
            }
            if (!scoring_method.equals("deduction") && !scoring_method.equals("raw") && !scoring_method.equals("percentage")) {
                return 5;
            }
            Assignment assignment = assignments.get(index);
            assignment.changeAssignment(name, total, scoring_method);
            return 1;
        }
        catch (Exception e) {
            return 6;
        }
    }
    public int addCriteria(double[] weights) {
        //parameters:
        // weights: double[]: Array of double representing weights
        //
        //return 1 if succeeded , return 2 if sum of weights not equals to 1, return 3 if unknown error
        try {
            double sum = 0;
            for (int i = 0; i < weights.length; i++) {
                sum += weights[i];
            }
            if (sum != 1) {
                return 2;
            }
            Criteria criteria = new Criteria(weights);
            criterias.add(criteria);
            return 1;
        }
        catch (Exception e) {
            return 3;
        }
    }
    public int changeCriteria(int index, double[] weights) {
        //parameters:
        // weights: double[]: Array of double representing weights
        //
        //return 1 if succeeded , return 2 if sum of weights not equals to 1, return 3 if criteria not found, return 4 if unknown error
        try {
            double sum = 0;

            for (int i = 0; i < weights.length; i++) {
                sum += weights[i];
            }
            if (sum != 1) {
                return 2;
            }
            if (index >= criterias.size() || index < 0) {
                return 3;
            }
            Criteria criteria = criterias.get(index);
            criteria.changeCriteria(weights);
            return 1;
        }
        catch (Exception e) {
            return 4;
        }
    }
    public String[][] getTable() { //
        // parameters:
        // None
        // return String[][] of content of sheet, null of unknown error
        try {
            int height = sheet.getHeight();
            int width = sheet.getWidth();
            String[][] table = new String[height][width];
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    table[i][j] = String.valueOf(sheet.getCellScore(i, j));
                }
            }
            return table;
        }
        catch (Exception e) {
            return null;
        }
    }
    public int setScore(int cor1,int cor2, String score) { //
        // parameters:
        // cor1: int: coordinate of row of cell to modify
        // cor2: int: coordinate of column of cell to modify
        // score: String: score to be modified
        //
        // return 1 if succeeded, return 2 if invalid score type, return 3 if unknown error
        try {
            double input = Double.valueOf(score);
            sheet.setScore(cor1, cor2, input);
            return 1;
        }
        catch (NumberFormatException e) {
            return 2;
        }
        catch (Exception e) {
            return 3;
        }
    }
    public String[] getNote(int cor1,int cor2) { //
        // parameters:
        // cor1: int: coordinate of row of cell to check
        // cor2: int: coordinate of column of cell to check
        //
        // return String[] of content of Note including content of note and time of last change, null of unknown error
        try {
            return sheet.getCellNote(cor1,cor2).getNoteContent();
        }
        catch (Exception e) {
            return null;
        }
    }

    public int setNote(int cor1,int cor2, String note) { //
        // parameters:
        // cor1: int: coordinate of row of cell to modify
        // cor2: int: coordinate of column of cell to modify
        // note: String: note to be modified
        //
        // return 1 if succeeded, return 2 if unknown error
        try {
            sheet.setNote(cor1, cor2, note);
            return 1;
        }
        catch (Exception e) {
            return 2;
        }
    }

    public Report getReport() {
        // tbd
        return null;
    }
    public int endCourse() {
        //return 1 if succeeded , return 2 if course is already ended, return 3 if unknown error
        try {
            if (end) {
                return 2;
            }
            end = true;
            return 1;
        }
        catch (Exception e) {
            return 3;
        }
    }
    public double calTotal() {
        //return double score if succeeded , return -1 if unknown error
        return 1;
    }
    public static void main(String[] args) {
        String a = "1a";
        double b = Double.valueOf(a);
        System.out.print(b);
    }
}

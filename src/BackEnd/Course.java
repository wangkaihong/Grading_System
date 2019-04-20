package BackEnd;

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
        this.courseName = null;
        this.lecturerName = null;
        this.semester = null;
        this.sheet = null;
        this.students = null;
        this.assignments = null;
        this.criteria = null;
    }

    public Course(String cN, String lN, String s, Sheet sh, ArrayList<Student> stu, ArrayList<Assignment> assign, ArrayList<Criteria> cri){
        courseName = cN;
        lecturerName = lN;
        semester = s;
        sheet = sh;
        students = stu;
        assignments = assign;
        criteria = cri;
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

    public int addStudent(String firstName, String middleInitial, String lastName, String studentId, String emailAddress) {
        // parameter:
        // firstName: String: First name of student
        // middleInitial: String: middleInitial of student
        // lastName: String: Last name of student
        // studentId: String: ID of student
        // emailAddress: String: Email address of student
        //
        // return 1 if succeeded, return 2 if failed
        Student student = new Undergraduate(firstName,middleInitial,lastName,studentId,emailAddress);
        if(students.add(student)) {
            return 1;
        }
        else {
            return 2;
        }
    }
    public int removeStudent(int index) {
        // parameters:
        // index: int: index of student in the arraylist you would like to remove
        //
        // return 1 if succeeded , return 2 if student not found, return 3 if unknown error
        if(index >= students.size()) {
            return 2;
        }
        else {
            if(students.remove(index) == null) {
                return 3;
            }
            else {
                return 1;
            }
        }
    }
    public int addAssignment(String name, double total, String scoring_method) {
        // parameters:
        // name: String: name of the assignment
        // total: double: total score of the assignment
        // scoring_method: String: scoring method of the assignment
        //
        // return 1 if succeeded, return 2 if invalid name, return 3 if invalid total, return 4 if invalid scoring_method, return 5 if unknown error
        if(name == null || name.equals("")) {
            return 2;
        }
        if(total <= 0) {
            return 3;
        }
        if(!scoring_method.equals("deduction") && !scoring_method.equals("raw") && !scoring_method.equals("percentage")) {
            return 4;
        }
        Assignment assignment = new Assignment(name,total,scoring_method);
        if(assignments.add(assignment)) {
            return 1;
        }
        else {
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
        if(index >= assignments.size()) {
            return 2;
        }
        if(name == null || name.equals("")) {
            return 3;
        }
        if(total <= 0) {
            return 4;
        }
        if(!scoring_method.equals("deduction") && !scoring_method.equals("raw") && !scoring_method.equals("percentage")) {
            return 5;
        }
        Assignment assignment = assignments.get(index);
        if(assignment == null) {
            return 6;
        }
        assignment.changeAssignment(name,total,scoring_method);
        return 1;
    }
    public int addCriteria(double[] weights) {
        return 1;
    }
    public int changeCriteria(int index, double[] weights) {
        return 1;
    }
    public Report getReport() {
        // tbd
        return null;
    }
    public int endCourse() {
        //return 1 if succeeded , return 2 if course is already ended, return 3 if unknown error
        return 1;
    }
    public double calTotal() {
        //return double score if succeeded , return -1 if unknown error
        return 1;
    }

}

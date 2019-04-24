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
    private Criteria criteria_UG;
    private Criteria criteria_G;
    private boolean end;
    private ArrayList<Double> extra_credits;

    public Course() {
        this.courseName = "";
        this.lecturerName = "";
        this.semester = "";
        this.sheet = new Sheet();
        this.students = new ArrayList<>();
        this.assignments = new ArrayList<>();
        this.criteria_UG = null;
        this.criteria_G = null;
        this.end = false;
        this.extra_credits = null;
    }
    public Course(String cN, String lN, String s, Sheet sh, ArrayList<Student> stu, ArrayList<Assignment> assign, Criteria c_ug, Criteria c_g, boolean end, ArrayList<Double> extra){
        courseName = cN;
        lecturerName = lN;
        semester = s;
        sheet = sh;
        students = stu;
        assignments = assign;
        criteria_UG = c_ug;
        criteria_G = c_g;
        this.end = end;
        this.extra_credits = extra;
    }


    public Course(String courseName, String lecturerName, String semester, ArrayList<Student> student_list, Course previous) {
        this.courseName = courseName;
        this.lecturerName = lecturerName;
        this.semester = semester;
        this.sheet = new Sheet();
        this.students = student_list;
        this.sheet.addRows(student_list.size());
        if(previous == null) {
            this.assignments = new ArrayList<Assignment>();
            this.criteria_UG = new Criteria();
            this.criteria_G = new Criteria();
        }
        else {
            this.assignments = previous.assignments;
            this.criteria_UG = previous.criteria_UG;
            this.criteria_G = previous.criteria_G;
        }
        this.end = false;
        this.extra_credits = null;
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

    public Criteria getCriteria_UG() {
        return criteria_UG;
    }

    public Criteria getCriteria_G() {
        return criteria_G;
    }

    public void setCriteria_UG(Criteria criteria_UG) {
        this.criteria_UG = criteria_UG;
    }

    public void setCriteria_G(Criteria criteria_G) {
        this.criteria_G = criteria_G;
    }
    //Add get extra_credit
    public ArrayList<Double> getExtra_credits() {
        return extra_credits;
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
        // return 5 if invalid studentId, return 6 if invalid emailAddress, return 7 if invalid studentType,
        // return 8 if course is ended, return 9 if unknown error
        if(end) {
            return 8;
        }
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
                //System.out.println(student.toString());
                students.add(student);
                //System.out.println(student.toString()+"2");
                if(extra_credits != null) {
                    extra_credits.add(0.0);
                }
                sheet.addRows(1);
                // todo: update sheet when adding student

                return 1;
            }
            if(studentType.equals("graduate")) {
                Student student = new Graduate(firstName, middleInitial, lastName, studentId, emailAddress);
                students.add(student);
                if(extra_credits != null) {
                    extra_credits.add(0.0);
                }
                return 1;
            }
            else {
                return 7;
            }

        }
        catch (Exception e) {
            return 9;
        }
    }
    public int removeStudent(int index) {
        // parameters:
        // index: int: index of student in the arraylist you would like to remove
        //
        // return 1 if succeeded , return 2 if student not found, return 3 if course is ended, return 4 if unknown error
        if(end) {
            return 3;
        }
        try {
            if (index >= students.size() || index < 0) {
                return 2;
            } else {
                students.remove(index);
                if(extra_credits != null) {
                    extra_credits.remove(index);
                }
                // todo: update sheet when removing assignments
                return 1;
            }
        }
        catch (Exception e) {
            return 4;
        }
    }
    public int addAssignment(String name, double total, String scoring_method) {
        // parameters:
        // name: String: name of the assignment
        // total: double: total score of the assignment
        // scoring_method: String: scoring method of the assignment
        //
        // return 1 if succeeded, return 2 if invalid name, return 3 if invalid total,
        // return 4 if invalid scoring_method, return 5 if course is ended, return 6 if unknown error
        if(end) {
            return 5;
        }
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
            sheet.addColumns(1);
            // todo: update sheet when adding assignments
            return 1;
        }
        catch (Exception e) {
            return 6;
        }

    }
    public int changeAssignment(int index, String name, double total, String scoring_method) {
        //parameters:
        // index: int: index of assigment you would like to change in the arraylist of assignments
        // name: String: name of the assignment
        // total: double: total score of the assignment
        // scoring_method: String: scoring method of the assignment
        //
        //return 1 if succeeded , return 2 if assignment not found, return 3 if invalid name,
        // return 4 if invalid total, return 5 if invalid scoring_method,
        // return 6 if course is ended, return 7 is unknown error
        if(end) {
            return 6;
        }
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
            return 7;
        }
    }
//    public int addCriteria_UG(double[] weights) {
//        //parameters:
//        // weights: double[]: Array of double representing weights
//        //
//        //return 1 if succeeded , return 2 if sum of weights is greater than 1, return 3 if course is ended, return 4 if unknown error
//        if(end) {
//            return 3;
//        }
//        try {
//            double sum = 0;
//            for (int i = 0; i < weights.length; i++) {
//                sum += weights[i];
//            }
//            if (sum > 1) {
//                return 2;
//            }
//            this.criteria_UG = new Criteria(weights);
//            return 1;
//        }
//        catch (Exception e) {
//            return 4;
//        }
//    }
//    public int addCriteria_G(double[] weights) {
//        //parameters:
//        // weights: double[]: Array of double representing weights
//        //
//        //return 1 if succeeded , return 2 if sum of weights is greater than 1, return 3 if course is ended, return 4 if unknown error
//        if(end) {
//            return 3;
//        }
//        try {
//            double sum = 0;
//            for (int i = 0; i < weights.length; i++) {
//                sum += weights[i];
//            }
//            if (sum > 1) {
//                return 2;
//            }
//            this.criteria_G = new Criteria(weights);
//            return 1;
//        }
//        catch (Exception e) {
//            return 4;
//        }
//    }
    public int changeCriteria_UG(double[] weights) {
        //parameters:
        // weights: double[]: Array of double representing weights
        //
        // return 1 if succeeded , return 2 if sum of weights is greater than 1,
        // return 3 if course is ended, return 4 if unknown error
        if(end) {
            return 3;
        }
        try {
            double sum = 0;

            for (int i = 0; i < weights.length; i++) {
                sum += weights[i];
            }
            if (sum > 1) {
                return 2;
            }
            criteria_UG.changeCriteria(weights);
            return 1;
        }
        catch (Exception e) {
            return 4;
        }
    }
    public int changeCriteria_G(double[] weights) {
        //parameters:
        // weights: double[]: Array of double representing weights
        //
        // return 1 if succeeded , return 2 if sum of weights is greater than 1,
        // return 3 if course is ended, return 4 if unknown error
        if(end) {
            return 3;
        }
        try {
            double sum = 0;
            for (int i = 0; i < weights.length; i++) {
                sum += weights[i];
            }
            if (sum > 1) {
                return 2;
            }
            System.out.println(criteria_G);
            criteria_G.changeCriteria(weights);
            return 1;
        }
        catch (Exception e) {
            return 4;
        }
    }
    public String[][] getTable() { //
        // parameters:
        // None
        // return String[][] of content of sheet, first two columns: ID, Name, null of unknown error
        try {
            if(extra_credits != null) {
                int column = 2;
                int height = sheet.getHeight();
                int width = sheet.getWidth();

                String[][] table = new String[height][width + column + 1];
                for (int i = 0; i < height; i++) { // todo
                    table[i][0] = students.get(i).getStudentId();
                    table[i][1] = students.get(i).getFirstName() + " " + students.get(i).getLastName();
                    for (int j = column; j < width + column; j++) {
                        table[i][j] = String.valueOf(sheet.getCellScore(i, j) * assignments.get(j).getTotal());
                    }
                    table[i][width + column] = "";
                }
                return table;
            }
            else {
                int column = 2;
                int height = sheet.getHeight();
                int width = sheet.getWidth();
                String[][] table = new String[height][width];
                for (int i = 0; i < height - 1; i++) {
                    table[i][0] = students.get(i).getStudentId();
                    table[i][1] = students.get(i).getFirstName() + " " + students.get(i).getLastName();
                    for (int j = column; j < width; j++) {
                        table[i][j] = String.valueOf(sheet.getCellScore(i, j) * assignments.get(j).getTotal());
                    }
                }
                return table;
            }
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
        // return 1 if succeeded, return 2 if invalid score type, return 3 if course is ended, return 4 if unknown error
        if(end) {
            return 3;
        }
        try {
            int real_cor2 = cor2 - 2; // offset first two columns
            double input = Double.valueOf(score)/assignments.get(real_cor2).getTotal(); // todo calculate portion to the total point
            sheet.setScore(cor1, cor2, input);
            return 1;
        }
        catch (NumberFormatException e) {
            return 2;
        }
        catch (Exception e) {
            return 4;
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
        // return 1 if succeeded, return 2 if course is ended, return 3 if unknown error
        if(end) {
            return 2;
        }
        try {
            sheet.setNote(cor1, cor2, note);
            return 1;
        }
        catch (Exception e) {
            return 3;
        }
    }

    public String[][] getAssignmentInformation() {
        // parameters:
        // none
        //
        // return: 2d String array of information about name, total score, weight for UG, weight for G, scoring method
        String[][] table = new String[assignments.size()][5];
        for(int i = 0; i < assignments.size();i++) {
            table[i][0] = assignments.get(i).getName();
            table[i][1] = String.valueOf(assignments.get(i).getTotal());
            table[i][2] = String.valueOf(criteria_UG.getWeight().get(i));
            table[i][3] = String.valueOf(criteria_G.getWeight().get(i)); //todo
            table[i][4] = assignments.get(i).getScoring_method();
        }
        return table;
    }
    public int changeTotal(int index, double new_total) {
        // parameters:
        // index: int: index of assignment about to change
        // new_total: double: new total score to change
        //
        // return: 1 if succeeded, return 2 if assignment not found, return 3 if course is ended, return 4 if unknown error
        if(end) {
            return 3;
        }
        try {
            if (index >= assignments.size() || index < 0) {
                return 2;
            } else {
                assignments.get(index).setTotal(new_total);
                return 1;
            }
        }
        catch (Exception e) {
            return 4;
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
    public int extra() {
        //return 1 if succeeded , return 2 if extra credit is already added, return 3 if unknown error
        try {
            if (extra_credits != null) {
                return 2;
            }
            extra_credits = new ArrayList<Double>();
            for(int i = 0; i < students.size();i++) {
                extra_credits.add(0.0);
            }
            return 1;
        }
        catch (Exception e) {
            return 3;
        }
    }

    public String[] calTotal() {
        //
        //return array of double score if succeeded , return null if unknown error
        String[] res = new String[students.size()];
        for(int i = 0; i < res.length;i++) {
            if(students.get(i) instanceof Undergraduate) {
                ArrayList<Double> row = sheet.getScoreRow(i);
                if(row.size() != criteria_UG.getWeight().size()) {
                    return null;
                }
                double score = 0;
                for(int j = 0; j < row.size(); j++) {
                    score += row.get(j) * criteria_UG.getWeight().get(j);
                }
                res[i] = String.valueOf(score);
            }
            else {
                ArrayList<Double> row = sheet.getScoreRow(i);
                if(row.size() != criteria_G.getWeight().size()) {
                    return null;
                }
                double score = 0;
                for(int j = 0; j < row.size(); j++) {
                    score += row.get(j) * criteria_G.getWeight().get(j);
                }
                res[i] = String.valueOf(score);
            }
        }
        return res;
    }
    public static void main(String[] args) {
        String a = "1a";
        double b = Double.valueOf(a);
        System.out.print(b);
    }
}

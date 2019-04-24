package BackEnd;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import BackEnd.FileIO;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by wangkaihong on 2019/4/9.
 */
public class Grading_System {
    private ArrayList<Course> courses;

    public Grading_System() {
        // Constructor: read from IO and construct system instance
        FileIO fileIO = new FileIO();
        this.courses = fileIO.readCourse();
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public String[] getCourseList() {
        String[] list = new String[courses.size()];
        for(int i = 0; i < list.length;i++) {
            list[i] = courses.get(i).getCourseName();
        }
        return list;
    }

    public int addCourse(String courseName, String lecturerName, String semester, String student_file_dir, int previous_Course_ind) {
        // parameters:
        // courseName: String :name of the course
        // lecturerName: String :name of the lecturer
        // semester: String :name of the semester
        // student_file_dir: String :directory to student file
        // previous_Course_ind: int :index of the course, -1 means not coping from previous courses.
        //
        // return 1 if succeeded, return 2 if have course name conflict, return 3 if invalid index,
        // return 4 if invalid course name, return 5 if invalid lecturer name, return 6 if invalid semester name,
        // return 7 if invalid path to student info file, return 8 if invalid format of student info file,
        // return 9 if file not found, return 10 if unknown error

        //testtest
        System.out.println("Call AddCourse");

        Course previous = null;
        if(previous_Course_ind != -1) {
            if (previous_Course_ind >= courses.size() || previous_Course_ind < 0) {
                return 3;
            }
             previous = courses.get(previous_Course_ind);
        }
        for(int i = 0;i < courses.size();i++) {
            if(courseName.equals(courses.get(i).getCourseName())) {
                System.out.println("Course name conflict!");
                return 2;
            }
        }
        if(courseName == null || courseName.length() == 0) {//|| lecturerName == null || semester == null || student_file_dir == null) {
            System.out.println("Course name cannot be empty!");
            return 4;
        }
        else {
            if(lecturerName == null || lecturerName.length() == 0) {
                System.out.println("Lecturer name cannot be empty!");
                return 5;
            }
            else {
                if(semester == null || semester.length() == 0) {
                    System.out.println("Semester cannot be empty!");
                    return 6;
                }
                else {
                    if(student_file_dir == null || student_file_dir.length() == 0) {
                        System.out.println("Student information file name cannot be empty!");
                        return 7;
                    }
                    else {
                        try {
                            System.out.print(student_file_dir);//testtest
                            ArrayList<Student> student_list = getStudentsFromFile(student_file_dir);
                            System.out.print("Try92");//testtest
                            courses.add(new Course(courseName,lecturerName,semester,student_list,previous));
                            FileIO fileIO = new FileIO();
                            System.out.print("Write Course");//testtest
                            fileIO.writeCourse(courses);
                            return 1;
                        }
                        catch (FileNotFoundException e) {
                            return 9;
                        }
                        catch (InvalidStudentFileException e) {
                            return 8;
                        }
                        catch (Exception e) {
                            return 10;
                        }
                    }
                }
            }
        }
    }

    public ArrayList<Student> getStudentsFromFile(String student_file_dir) throws FileNotFoundException, InvalidStudentFileException, Exception{
        ArrayList<Student> stduent_list = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(student_file_dir));
            scanner.useDelimiter("\n");
            while (scanner.hasNext()) {
                String temp = scanner.next();
                String[] arr = temp.split(",");
                if(arr.length != 6) {
                    throw new InvalidStudentFileException();
                }
                if(arr[5].equals("U")) {
                    stduent_list.add(new Undergraduate(arr[0], arr[1], arr[2], arr[3], arr[4]));
                }
                else {
                    if(arr[5].equals("G")) {
                        stduent_list.add(new Graduate(arr[0], arr[1], arr[2], arr[3], arr[4]));
                    }
                    else {
                        throw new InvalidStudentFileException();
                    }
                }
            }
            scanner.close();
        }
        catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        }
        catch (InvalidStudentFileException e) {
            throw new InvalidStudentFileException();
        }
        catch (Exception e) {
            throw new Exception();
        }
        return stduent_list;
    }

    public int deleteCourse(int previous_Course_ind) {
        // parameters:
        // previous_Course_ind: int :index of the course
        // return 1 if succeeded, return 2 if course not found, return 3 if unknown error
        if(previous_Course_ind >= courses.size()) {
            return 2;
        }
        if(courses.remove(previous_Course_ind)==null) {
            return 3;
        }
        return 1;
    }

    public static void main(String[] args){
        System.out.println("Hello_World!");
        /*
        Test File I/O
        Assume 2 students, one is Undergrad, the other is Grad,
        1 course: CS591 Spring2019 Christine
        2 assignments: hw1, hw2
        hw1: full points: 100; hw2: full points: 120
        Undergrad student fName: Jack, sName: St, tName: Martin, ID:12345, email: jacksl@ggmail.com
        score: hw1 -10, hw2 0.8
        Grad student fName: Alice, sName: Von, tName: Ludwika, ID:67890, email: alicevl@ggmail.com
        score: hw1 -12, hw2 0.9
        2 notes: Jack's hw1: "Good", Alice's hw2: "Well done"
        */
        //set Student

        Student stu1 = new Undergraduate("Jack","St","Martin","12345","jacksl@ggmail.com");
        Student stu2 = new Graduate("Alice","Von","Ludwika","67890","alicevl@ggmail.com");
        ArrayList<Student> listStu = new ArrayList<Student>();
        listStu.add(stu1);
        listStu.add(stu2);
        //set Assignments
        Assignment assign1 = new Assignment("hw1",100,"Deduction");
        Assignment assign2 = new Assignment("hw2",120,"Percentage");
        ArrayList<Assignment> listAssign = new ArrayList<Assignment>();
        listAssign.add(assign1);
        listAssign.add(assign2);
        //set Criteria

        ArrayList<Double> weights1 = new ArrayList<Double>();
        weights1.add(0.4);
        weights1.add(0.6);
        ArrayList<Double> weights2 = new ArrayList<Double>();
        weights2.add(0.3);
        weights2.add(0.7);


        Criteria criteria1 = new Criteria(weights1);
        Criteria criteria2 = new Criteria(weights2);
        //set Notes
        Date time1 = new Date();
        String strDate = "04/19/2019";
        try{DateFormat dateFmt = new SimpleDateFormat("dd/MM/yyyy");
            time1 = dateFmt.parse(strDate);}
        catch (java.text.ParseException e){
            e.printStackTrace();
        }
        Note note1 = new Note("Good");
        Note note2 = new Note("Well done");
        Note noteBlank = new Note("");
        //set Cells
        Cell cell1 = new Cell(note1,-10);
        Cell cell2 = new Cell(noteBlank,0.8);
        Cell cell3 = new Cell(noteBlank,-12);
        Cell cell4 = new Cell(note2,0.9);
        //set list Cell
        ArrayList<Cell> listCell1 = new ArrayList<Cell>();
        listCell1.add(cell1);
        listCell1.add(cell2);
        ArrayList<Cell> listCell2 = new ArrayList<Cell>();
        listCell2.add(cell3);
        listCell2.add(cell4);
        //set matrix Cell
        ArrayList<ArrayList<Cell>> matrixCell = new ArrayList<ArrayList<Cell>>();
        matrixCell.add(listCell1);
        matrixCell.add(listCell2);
        //set Sheet
        Sheet sheet1 = new Sheet(matrixCell);
        //set Course
        Course course1 = new Course("CS591","Christine","Spring2019",sheet1,listStu,listAssign,criteria1,criteria2,false,weights1);
        //set Grading_System
        Grading_System gradSys = new Grading_System();
        ArrayList<Course> listCourse = new ArrayList<Course>();
        listCourse.add(course1);
        gradSys.setCourses(listCourse);

        //write file
        FileIO fileIO1 = new FileIO();
        fileIO1.writeCourse(listCourse);


        //read file
        ArrayList<Course> listCourseRead = new ArrayList<Course>();
        Criteria criUG = new Criteria();
        Criteria criG = new Criteria();
        ArrayList<Student> listStudentRead = new ArrayList<Student>();
        ArrayList<Assignment> listAssignRead = new ArrayList<Assignment>();
        ArrayList<ArrayList<Cell>> matrixCellRead = new ArrayList<ArrayList<Cell>>();
        listCourseRead = fileIO1.readCourse();
        for(Course tempc : listCourseRead){
            System.out.println(tempc.getCourseName());
            System.out.println(tempc.getLecturerName());
            System.out.println(tempc.getSemester());
            System.out.println(tempc.isEnd());
            System.out.println(tempc.getExtra_credits());
            //listCriRead = tempc.getCriteria();
            criG = tempc.getCriteria_G();
            criUG = tempc.getCriteria_UG();
            listStudentRead = tempc.getStudents();
            listAssignRead = tempc.getAssignments();
            matrixCellRead = tempc.getSheet().getAllCell();
            //System.out.println(tempc.getAssignments());
            //System.out.println(tempc.getCriteria());
            //System.out.println(tempc.getSheet());
            //System.out.println(tempc.getStudents());
        }

        //read Criteria
        Criteria criTemp = fileIO1.readCriteria("CS591G");
        //System.out.println(criTemp.getWeight());
        ArrayList<Double> listWeights = criUG.getWeight();
        System.out.println(listWeights);
        listWeights = criG.getWeight();
        System.out.println(listWeights);
        //read Student
        //ArrayList<Student> listStudentRead = fileIO1.readStudentInfo("CS591");
        for(Student temp : listStudentRead) {

            System.out.println(temp.getFirstName());
            System.out.println(temp.getMiddleInitial());
            System.out.println(temp.getLastName());
            System.out.println(temp.getStudentId());
            System.out.println(temp.getEmailAddress());
            if (temp instanceof Undergraduate) {
                System.out.println("Undergrad");
            }
            else if (temp instanceof Graduate) {
                System.out.println("Grad");
            }
            else {
                System.out.println("TypeError");
            }

        }
        //read Assignments
        //ArrayList<Assignment> listAssignRead = fileIO1.readAssignment("CS591");
        for(Assignment temp : listAssignRead){

            System.out.println(temp.getName());
            System.out.println(temp.getTotal());
            System.out.println(temp.getScoring_method());

        }
        //read Cell
        //ArrayList<ArrayList<Cell>> matrixCellRead = fileIO1.readCell("CS591");
        for(ArrayList<Cell> tempListCell : matrixCellRead){
            for(Cell temp : tempListCell){

                System.out.println(temp.getScore());
                System.out.println(temp.getNote().getInformation());
                System.out.println(temp.getNote().getTime());

            }
        }

        //end test
        System.out.println("Test Complete");
    }
}

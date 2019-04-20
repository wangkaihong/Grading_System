package BackEnd;

import com.sun.media.sound.InvalidFormatException;

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
        courses = new ArrayList<>();
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public int addCourse(String courseName, String lecturerName, String semester, String student_file_dir, int previous_Course_ind) {
        // parameters:
        // courseName: String :name of the course
        // lecturerName: String :name of the lecturer
        // semester: String :name of the semester
        // student_file_dir: String :directory to student file
        // previous_Course_ind: int :index of the course
        //
        // return 1 if succeeded, return 2 if have course name conflict, return 3 if have course out of index,
        // return 4 if invalid course name, return 5 if invalid lecturer name, return 6 if invalid semester name,
        // return 7 if invalid path to student info file, return 8 if invalid format of student info file,
        // return 9 if file not found, return 10 if unknown error
        if(previous_Course_ind >= courses.size()) {
            return 3;
        }
        Course previous = courses.get(previous_Course_ind);
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
                            ArrayList<Student> student_list = getStudentsFromFile(student_file_dir);
                            courses.add(new Course(courseName,lecturerName,semester,student_list,previous));
                            return 1;
                        }
                        catch (FileNotFoundException e) {
                            return 9;
                        }
                        catch (InvalidFormatException e) {
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

    public ArrayList<Student> getStudentsFromFile(String student_file_dir) throws FileNotFoundException, InvalidFormatException, Exception{
        ArrayList<Student> stduent_list = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(student_file_dir));
            scanner.useDelimiter("\n");
            while (scanner.hasNext()) {
                String temp = scanner.next();
                String[] arr = temp.split(",");
                if(arr[5].equals("U")) {
                    stduent_list.add(new Undergraduate(arr[0], arr[1], arr[2], arr[3], arr[4]));
                }
                else {
                    if(arr[5].equals("G")) {
                        stduent_list.add(new Graduate(arr[0], arr[1], arr[2], arr[3], arr[4]));
                    }
                    else {
                        throw new InvalidFormatException();
                    }
                }
            }
            scanner.close();
        }
        catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        }
        catch (InvalidFormatException e) {
            throw new InvalidFormatException();
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

    public static void main(String[] args) {
        Grading_System s = new Grading_System();
    }
}

package BackEnd;

import java.util.ArrayList;

/**
 * Created by wangkaihong on 2019/4/9.
 */
public class Grading_System {
    private ArrayList<Course> courses;

    public Grading_System() { // Constructor: read from IO and construct system instance

    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public int addCourse(String courseName, String lecturerName, String semester, String student_file_dir, Course previous) {
        // addCourse(String courseName, String lecturerName, String semester, String student_file_dir, Course previous):
        // parameters:
        // courseName: String :name of the course
        // lecturerName: String :name of the lecturer
        // semester: String :name of the course
        // student_file_dir: String :name of the course
        // previous: String :name of the course
        // return 1 if succeeded, return 2 if have course name conflict, return 3 if unknown error
        for(int i = 0;i < courses.size();i++) {
            if(courseName.equals(courses.get(i).getCourseName())) {
                System.out.println("Course name conflict!");
                return 2;
            }
        }
        if(courseName == null || courseName.length() == 0) {//|| lecturerName == null || semester == null || student_file_dir == null) {
            System.out.println("Course name cannot be empty!");
            return 3;
        }
        else {
            if(lecturerName == null || lecturerName.length() == 0) {
                System.out.println("Lecturer name cannot be empty!");
                return 3;
            }
            else {
                if(semester == null || semester.length() == 0) {
                    System.out.println("Semester cannot be empty!");
                    return 3;
                }
                else {
                    if(student_file_dir == null || student_file_dir.length() == 0) {
                        System.out.println("Student information file name cannot be empty!");
                        return 3;
                    }
                    else {
                        courses.add(new Course(courseName,lecturerName,semester,student_file_dir,previous));
                        return 1;
                    }
                }
            }
        }
    }
    public int deleteCourse() {
        //deleteCourse(): input
        // return 1 if succeeded, return 2 if course not found, return 3 if unknown error
        return 1;
    }

    public static void main(String[] args) {
        Grading_System s = new Grading_System();
    }
}

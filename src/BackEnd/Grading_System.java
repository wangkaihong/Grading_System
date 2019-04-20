package BackEnd;

import java.util.ArrayList;

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
        // return 1 if succeeded, return 2 if have course name conflict, return 3 if have course out of index, return 4 if unknown error
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
                return 4;
            }
            else {
                if(semester == null || semester.length() == 0) {
                    System.out.println("Semester cannot be empty!");
                    return 4;
                }
                else {
                    if(student_file_dir == null || student_file_dir.length() == 0) {
                        System.out.println("Student information file name cannot be empty!");
                        return 4;
                    }
                    else {
                        courses.add(new Course(courseName,lecturerName,semester,student_file_dir,previous));
                        return 1;
                    }
                }
            }
        }
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

package BackEnd;

/**
 * Created by wangkaihong on 2019/4/9.
 */
public abstract class Student implements Reportable{
    private String firstName;
    private String middleInitial;
    private String lastName;
    private String studentId;
    private String emailAddress;
    
    public Student() {
        
    }
    
    public Student(String firstName, String middleInitial, String lastName, String studentId, String emailAddress) {
        this.firstName = firstName;
        this.middleInitial = middleInitial;
        this.lastName = lastName;
        this.studentId = studentId;
        this.emailAddress = emailAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleInitial() {
        return middleInitial;
    }

    public void setMiddleInitial(String middleInitial) {
        this.middleInitial = middleInitial;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    public Report getReport() {
        return null;
    }

}

package BackEnd;

/**
 * Created by wangkaihong on 2019/4/9.
 */
public abstract class Student implements Reportable{

    protected String firstName;
    protected String middleInitial;
    protected String lastName;
    protected String studentId;
    protected String emailAddress;
    
    public Student() {
        this.firstName = null;
        this.middleInitial = null;
        this.lastName = null;
        this.studentId = null;
        this.emailAddress = null;
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

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
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

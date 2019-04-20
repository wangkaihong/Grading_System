package BackEnd;

/**
 * Created by wangkaihong on 2019/4/9.
 */
public class Undergraduate extends Student {

    public Undergraduate(String fName, String sName, String tName, String id, String email){
        firstName = fName;
        secondName = sName;
        thirdName = tName;
        studentId = id;
        emailAddress = email;
    }
    public Undergraduate(String firstName, String middleInitial, String lastName, String studentId, String emailAddress) {
        super(firstName, middleInitial, lastName, studentId, emailAddress);
    }
}

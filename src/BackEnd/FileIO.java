package BackEnd;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Created by wuhn on 2019/4/14.
 */
public class FileIO {
    public void writeCriteria(Criteria criteria1, String filename){
        JSONObject obj1 = new JSONObject();
        obj1.put("jOut",criteria1.getWeight());
        try(FileWriter fw1 = new FileWriter(filename+"Criteria.json")){
            fw1.write(obj1.toJSONString());
            fw1.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Criteria readCriteria(String filename){

        JSONParser parser1 = new JSONParser();
        ArrayList<Double> listCriteria = new ArrayList<Double>();
        try (FileReader reader = new FileReader(filename+"Criteria.json"))
        {
            //Read JSON file
            Object obj = parser1.parse(reader);

            JSONObject readListCriteria = (JSONObject) obj;
            //System.out.println(testIn);
            listCriteria = (ArrayList<Double>) readListCriteria.get("jOut");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Criteria(listCriteria);
    }

    public void writeStudentInfo(Student student1, String filename){
        JSONObject obj1 = new JSONObject();
        JSONObject out1 = new JSONObject();
        obj1.put("firstName",student1.getFirstName());
        obj1.put("secondName",student1.getSecondName());
        obj1.put("thirdName",student1.getThirdName());
        obj1.put("studentId",student1.getStudentId());
        obj1.put("emailAddress",student1.getEmailAddress());
        out1.put("jOut",obj1);
        if(student1 instanceof Undergraduate){
            out1.put("Type","U");
        } else if (student1 instanceof Graduate){
            out1.put("Type","G");
        }

        try(FileWriter fw1 = new FileWriter(filename+"Student.json")){
            fw1.write(out1.toJSONString());
            fw1.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Student> readStudentInfo(String filename){
        JSONParser parser1 = new JSONParser();
        ArrayList<Student> listStudent = new ArrayList<Student>();
        String fName;
        String sName;
        String tName;
        String email;
        String id;
        try (FileReader reader = new FileReader(filename+"Student.json"))
        {
            //Read JSON file
            Object obj = parser1.parse(reader);

            JSONObject readStudent = (JSONObject) obj;
            JSONObject objInfo = (JSONObject) readStudent.get("jOut");
            fName = (String) objInfo.get("firstName");
            sName = (String) objInfo.get("secondName");
            tName = (String) objInfo.get("thirdName");
            id = (String) objInfo.get("studentId");
            email = (String) objInfo.get("emailAddress");
            if ((String) readStudent.get("Type") == "U"){
                Undergraduate s = new Undergraduate(fName,sName,tName,id,email);
                listStudent.add(s);
            } else if ((String) readStudent.get("Type") == "G"){
                Graduate s = new Graduate(fName,sName,tName,id,email);
                listStudent.add(s);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return listStudent;
    }
}

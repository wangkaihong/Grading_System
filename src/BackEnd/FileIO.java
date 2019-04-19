package BackEnd;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 * Created by wuhn on 2019/4/14.
 */
public class FileIO {
    /*
    Each file's name is content type with an additional filename from high level class

    There're 2 methods for each data type, write/read
    In writeData method, firstly we put metadata of each instance into a JSONObject,
    then put these instances into the other JSONObject and finally write it to file.

    In readData method, on the contrary, we decode the total JSONObject above all,
    then read instances iteratively and construct an arraylist as output.
    */

    public void writeCriteria(ArrayList<Criteria> listCriteria, String filename){
        //Input a list of Criteria instances, write it to JSON file
        JSONObject obj1 = new JSONObject();
        JSONObject out1 = new JSONObject();
        int count1 = 0;

        for(Criteria cri : listCriteria){
            obj1.put("Weights",cri.getWeight());
            out1.put("jOut"+Integer.toString(count1),obj1);
        }
        try(FileWriter fw1 = new FileWriter(filename+"Criteria.json")){
            fw1.write(out1.toJSONString());
            fw1.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Criteria> readCriteria(String filename){
        //Input the additional JSON file name, output a list of Criteria instances, whose data are read
        //from JSON file name
        JSONParser parser1 = new JSONParser();
        ArrayList<Double> listWeights = new ArrayList<Double>();
        ArrayList<Criteria> listCriteria = new ArrayList<Criteria>();
        try (FileReader reader = new FileReader(filename+"Criteria.json"))
        {
            //Read JSON file
            Object obj = parser1.parse(reader);
            JSONObject readListCriteria = (JSONObject) obj;
            Iterator ite = readListCriteria.keySet().iterator();
            while(ite.hasNext()){
                String sKey = (String) ite.next();
                JSONObject tempRead = (JSONObject) readListCriteria.get(sKey);
                listWeights = (ArrayList<Double>) tempRead.get("Weights");
                Criteria c = new Criteria(listWeights);
                listCriteria.add(c);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return listCriteria;
    }

    public void writeStudentInfo(ArrayList<Student> listStu, String filename){
        JSONObject obj1 = new JSONObject();
        JSONObject out1 = new JSONObject();
        int count1 = 0;

        for(Student stu : listStu){
            obj1.put("firstName",stu.getFirstName());
            obj1.put("secondName",stu.getSecondName());
            obj1.put("thirdName",stu.getThirdName());
            obj1.put("studentId",stu.getStudentId());
            obj1.put("emailAddress",stu.getEmailAddress());
            if(stu instanceof Undergraduate){
                obj1.put("Type","U");
            } else if (stu instanceof Graduate){
                obj1.put("Type","G");
            }
            out1.put("jOut"+Integer.toString(count1),obj1);

            count1 = count1 + 1;
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

            Iterator ite = readStudent.keySet().iterator();
            while(ite.hasNext()){
                String sKey = (String) ite.next();
                JSONObject tempRead = (JSONObject) readStudent.get(sKey);
                fName = (String) tempRead.get("firstName");
                sName = (String) tempRead.get("secondName");
                tName = (String) tempRead.get("thirdName");
                id = (String) tempRead.get("studentId");
                email = (String) tempRead.get("emailAddress");
                if (((String) tempRead.get("Type")) == "U"){
                    Undergraduate s = new Undergraduate(fName,sName,tName,id,email);
                    listStudent.add(s);
                } else if (((String) tempRead.get("Type")) == "G"){
                    Graduate s = new Graduate(fName,sName,tName,id,email);
                    listStudent.add(s);
                } else {
                    System.out.println("Student Type Error");
                }
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

    public void writeAssignment(ArrayList<Assignment> listAssign, String filename){
        JSONObject obj1 = new JSONObject();
        JSONObject out1 = new JSONObject();
        int count1 = 0;

        for( Assignment assign : listAssign){
            obj1.put("name",assign.getName());
            obj1.put("total",assign.getTotal());
            obj1.put("scoring_method",assign.isScoring_method());

            out1.put("jOut"+Integer.toString(count1),obj1);

            count1 = count1 + 1;
        }

        try(FileWriter fw1 = new FileWriter(filename+"Assignment.json")){
            fw1.write(out1.toJSONString());
            fw1.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Assignment> readAssignment(String filename){
        JSONParser parser1 = new JSONParser();
        ArrayList<Assignment> listAssign = new ArrayList<Assignment>();
        String name;
        double total;
        boolean scoring_method;
        try (FileReader reader = new FileReader(filename+"Assignment.json"))
        {
            //Read JSON file
            Object obj = parser1.parse(reader);
            JSONObject readAssign = (JSONObject) obj;

            Iterator ite = readAssign.keySet().iterator();
            while(ite.hasNext()){
                String sKey = (String) ite.next();
                JSONObject tempRead = (JSONObject) readAssign.get(sKey);
                name = (String) tempRead.get("name");
                total = Double.valueOf((String)tempRead.get("total"));
                scoring_method = Boolean.valueOf((String)tempRead.get("scoring_method"));
                Assignment assign = new Assignment(name, total, scoring_method);
                listAssign.add(assign);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return listAssign;
    }

    public void writeCell(ArrayList<ArrayList<Cell>> listCell, String filename){
        JSONObject obj1 = new JSONObject();
        JSONObject out1 = new JSONObject();
        JSONObject out2 = new JSONObject();
        int count1 = 0;
        int count2 = 0;

        for(ArrayList<Cell> listC : listCell){
            for(Cell c : listC){
                obj1.put("noteInfo",c.getNote().getInformation());
                obj1.put("noteDate",c.getNote().getTime());
                obj1.put("score",c.getScore());

                out1.put("jOut1"+Integer.toString(count1),obj1);
                count2 = count2 + 1;
            }
            out2.put("jOut2"+Integer.toString(count1),out1);
            count1 = count1 + 1;
        }
        try(FileWriter fw1 = new FileWriter(filename+"Cell.json")){
            fw1.write(out2.toJSONString());
            fw1.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ArrayList<Cell>> readCell(String filename){
        JSONParser parser1 = new JSONParser();
        //ArrayList<Cell> listCell = new ArrayList<Cell>();
        ArrayList<ArrayList<Cell>> matrixCell = new ArrayList<ArrayList<Cell>>();
        String noteInfo;
        Date noteDate = new Date(); //noteDate should be initialized, inappropriate?
        double score;

        try (FileReader reader = new FileReader(filename+"Cell.json")){
            //Read JSON file
            Object obj = parser1.parse(reader);
            JSONObject readCellMatrix = (JSONObject) obj;
            Iterator ite = readCellMatrix.keySet().iterator();
            while(ite.hasNext()){
                String sKey = (String) ite.next();
                JSONObject readCellRow = (JSONObject) readCellMatrix.get(sKey);
                Iterator iteRow = readCellRow.keySet().iterator();
                ArrayList<Cell> listCell = new ArrayList<Cell>();
                while(iteRow.hasNext()){
                    String sKeyRow = (String) iteRow.next();
                    JSONObject tempRead = (JSONObject) readCellRow.get(sKeyRow);
                    noteInfo = (String) tempRead.get("noteInfo");
                    String strDate = (String) tempRead.get("noteDate");
                    try{DateFormat dateFmt = new SimpleDateFormat("dd/MM/yyyy");
                            noteDate = dateFmt.parse(strDate);}
                    catch (java.text.ParseException e){
                        e.printStackTrace();
                    }
                    score = Double.valueOf((String)tempRead.get("score"));
                    Note tempNote = new Note(noteInfo, noteDate);
                    Cell tempCell = new Cell(tempNote, score);
                    listCell.add(tempCell);
                }
                matrixCell.add(listCell);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return matrixCell;
    }

    public void writeCourse(ArrayList<Course> listCourse, String filename){
        JSONObject obj1 = new JSONObject();
        JSONObject out1 = new JSONObject();
        int count1 = 0;

        for( Course course : listCourse){
            obj1.put("courseName",course.getCourseName());
            obj1.put("lecturerName",course.getLecturerName());
            obj1.put("semester",course.getSemester());
            //write cell matrix instead of sheet
            writeCell(course.getSheet().getCell(), course.getCourseName());
            writeStudentInfo(course.getStudents(), course.getCourseName());
            writeAssignment(course.getAssignments(), course.getCourseName());
            writeCriteria(course.getCriteria(), course.getCourseName());

            out1.put("jOut"+Integer.toString(count1),obj1);

            count1 = count1 + 1;
        }

        try(FileWriter fw1 = new FileWriter(filename+"Course.json")){
            fw1.write(out1.toJSONString());
            fw1.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Course> readCourse(String filename){
        JSONParser parser1 = new JSONParser();
        ArrayList<Course> listCourse = new ArrayList<Course>();
        String courseName;
        String lecturerName;
        String semester;
        Sheet sheet;
        ArrayList<Student> students;
        ArrayList<Assignment> assignments;
        ArrayList<Criteria> criteria;
        try (FileReader reader = new FileReader(filename+"Course.json")){
            //Read JSON file
            Object obj = parser1.parse(reader);
            JSONObject readCourse = (JSONObject) obj;

            Iterator ite = readCourse.keySet().iterator();
            while(ite.hasNext()){
                String sKey = (String) ite.next();
                JSONObject tempRead = (JSONObject) readCourse.get(sKey);
                courseName = (String) tempRead.get("courseName");
                lecturerName = (String) tempRead.get("lecturerName");
                semester = (String) tempRead.get("semester");
                //read cell matrix and use it to construct a sheet
                sheet = new Sheet(readCell(courseName));
                students = readStudentInfo(courseName);
                assignments = readAssignment(courseName);
                criteria = readCriteria(courseName);
                Course course = new Course(courseName,lecturerName,semester,sheet,students,assignments,criteria);
                listCourse.add(course);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return listCourse;
    }
}

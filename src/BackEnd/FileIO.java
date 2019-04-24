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

/*
Bugs:
1. Note.time String --> Date error
2. Cell.score is raw data (deduction or percentage type), should be processed
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

    public void writeCriteria(Criteria cri, String filename){
        //Input a list of Criteria instances, write it to JSON file
        JSONObject obj1 = new JSONObject();
        obj1.put("Weights",cri.getWeight());

        try(FileWriter fw1 = new FileWriter(filename+"Criteria.json")){
            fw1.write(obj1.toJSONString());
            fw1.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Criteria readCriteria(String filename){
        //Input the additional JSON file name, output a list of Criteria instances, whose data are read
        //from JSON file name
        JSONParser parser1 = new JSONParser();
        ArrayList<Double> listWeights = new ArrayList<Double>();
        try (FileReader reader = new FileReader(filename+"Criteria.json")){
            //Read JSON file
            Object obj = parser1.parse(reader);
            JSONObject readCriteria = (JSONObject) obj;
            listWeights = (ArrayList<Double>) readCriteria.get("Weights");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Criteria(listWeights);
    }

    public void writeStudentInfo(ArrayList<Student> listStu, String filename){
        JSONObject out1 = new JSONObject();
        int count1 = 0;

        for(Student stu : listStu){
            JSONObject obj1 = new JSONObject();
            obj1.put("firstName",stu.getFirstName());
            obj1.put("secondName",stu.getMiddleInitial());
            obj1.put("thirdName",stu.getLastName());
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
            int countRead = 0;
            int i = 0;
            while(ite.hasNext()){
                countRead = countRead + 1;
                ite.next();
            }
            while(i < countRead){
                JSONObject tempRead = (JSONObject) readStudent.get("jOut"+Integer.toString(i));
                fName = (String) tempRead.get("firstName");
                sName = (String) tempRead.get("secondName");
                tName = (String) tempRead.get("thirdName");
                id = (String) tempRead.get("studentId");
                email = (String) tempRead.get("emailAddress");
                String stuType = (String) tempRead.get("Type");
                if (stuType.equals("U")){
                    Undergraduate s = new Undergraduate(fName,sName,tName,id,email);
                    listStudent.add(s);
                } else if (stuType.equals("G")){
                    Graduate s = new Graduate(fName,sName,tName,id,email);
                    listStudent.add(s);
                } else {
                    System.out.println(stuType);
                    System.out.println("Student Type Error");
                }
                i = i + 1;
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
        JSONObject out1 = new JSONObject();
        int count1 = 0;

        for( Assignment assign : listAssign){
            JSONObject obj1 = new JSONObject();
            obj1.put("name",assign.getName());
            obj1.put("total",assign.getTotal());
            obj1.put("scoring_method",assign.getScoring_method());

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
        String scoring_method;
        try (FileReader reader = new FileReader(filename+"Assignment.json"))
        {
            //Read JSON file
            Object obj = parser1.parse(reader);
            JSONObject readAssign = (JSONObject) obj;

            Iterator ite = readAssign.keySet().iterator();
            int countRead = 0;
            int i = 0;
            while(ite.hasNext()){
                countRead = countRead + 1;
                ite.next();
            }
            while(i < countRead){
                JSONObject tempRead = (JSONObject) readAssign.get("jOut"+Integer.toString(i));
                name = (String) tempRead.get("name");
                total = (double)tempRead.get("total");
                scoring_method = (String) tempRead.get("scoring_method");
                Assignment assign = new Assignment(name, total, scoring_method);
                listAssign.add(assign);
                i = i + 1;
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
        //JSONObject out1 = new JSONObject();
        JSONObject out2 = new JSONObject();
        int count1 = 0;
        int count2 = 0;

        for(ArrayList<Cell> listC : listCell){
            JSONObject out1 = new JSONObject();
            count2 = 0;
            for(Cell c : listC){
                JSONObject obj1 = new JSONObject();
                obj1.put("noteInfo",c.getNote().getInformation());
                //obj1.put("noteDate",c.getNote().getTime());
                obj1.put("noteDate",c.getNote().getTime());//fix it later with real time
                obj1.put("score",c.getScore());

                out1.put("jOut1"+Integer.toString(count2),obj1);
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
        String noteDate;
        double score;

        try (FileReader reader = new FileReader(filename+"Cell.json")){
            //Read JSON file
            Object obj = parser1.parse(reader);
            JSONObject readCellMatrix = (JSONObject) obj;
            Iterator ite = readCellMatrix.keySet().iterator();
            int countRead = 0;
            int i = 0;
            while(ite.hasNext()){
                countRead = countRead + 1;
                ite.next();
            }
            while(i < countRead){
                JSONObject readCellRow = (JSONObject) readCellMatrix.get("jOut2"+Integer.toString(i));
                Iterator iteRow = readCellRow.keySet().iterator();
                ArrayList<Cell> listCell = new ArrayList<Cell>();
                while(iteRow.hasNext()){
                    String sKeyRow = (String) iteRow.next();
                    JSONObject tempRead = (JSONObject) readCellRow.get(sKeyRow);
                    noteInfo = (String) tempRead.get("noteInfo");
                    noteDate = (String) tempRead.get("noteDate");
                    /*
                    try{DateFormat dateFmt = new SimpleDateFormat("dd/MM/yyyy");
                            noteDate = dateFmt.parse(strDate);}
                    catch (java.text.ParseException e){
                        e.printStackTrace();
                    }
                    */
                    //score = Double.valueOf((String)tempRead.get("score"));
                    score = (double) tempRead.get("score");
                    Note tempNote = new Note(noteInfo, noteDate);
                    Cell tempCell = new Cell(tempNote, score);
                    listCell.add(tempCell);
                }
                matrixCell.add(listCell);
                i = i + 1;
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

    public void writeCourse(ArrayList<Course> listCourse){
        JSONObject out1 = new JSONObject();
        int count1 = 0;
        //testtest
        System.out.println("HEre");
        System.out.println(listCourse);

        for( Course course : listCourse){
            JSONObject obj1 = new JSONObject();
            obj1.put("courseName",course.getCourseName());
            obj1.put("lecturerName",course.getLecturerName());
            obj1.put("semester",course.getSemester());
            obj1.put("end",course.isEnd());
            obj1.put("extra_credits",course.getExtra_credits());
            //testtest
            System.out.println(course.getCourseName());
            System.out.println(course.getLecturerName());
            System.out.println(course.getSemester());
            System.out.println(course.isEnd());
            System.out.println(course.getExtra_credits());
            //write cell matrix instead of sheet
            writeCell(course.getSheet().getAllCell(), course.getCourseName()+course.getSemester());
            writeStudentInfo(course.getStudents(), course.getCourseName()+course.getSemester());
            writeAssignment(course.getAssignments(), course.getCourseName()+course.getSemester());
            writeCriteria(course.getCriteria_UG(), course.getCourseName()+course.getSemester()+"UG");
            writeCriteria(course.getCriteria_G(), course.getCourseName()+course.getSemester()+"G");

            out1.put("jOut"+Integer.toString(count1),obj1);

            count1 = count1 + 1;
        }

        try(FileWriter fw1 = new FileWriter("CourseList.json")){
            fw1.write(out1.toJSONString());
            fw1.flush();
            System.out.print("Write CourseList JSON");//testtest
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Course> readCourse(){
        JSONParser parser1 = new JSONParser();
        ArrayList<Course> listCourse = new ArrayList<Course>();
        String courseName;
        String lecturerName;
        String semester;
        Sheet sheet;
        ArrayList<Student> students;
        ArrayList<Assignment> assignments;
        boolean end;
        ArrayList<Double> extra_credit;
        //ArrayList<Criteria> criteria;
        Criteria criUG;
        Criteria criG;
        try (FileReader reader = new FileReader("CourseList.json")){
            //Read JSON file
            Object obj = parser1.parse(reader);
            JSONObject readCourse = (JSONObject) obj;
            Iterator ite = readCourse.keySet().iterator();
            int countRead = 0;
            int i = 0;
            while(ite.hasNext()){
                countRead = countRead + 1;
                ite.next();
            }
            while(i < countRead){
                JSONObject tempRead = (JSONObject) readCourse.get("jOut"+Integer.toString(i));
                courseName = (String) tempRead.get("courseName");
                lecturerName = (String) tempRead.get("lecturerName");
                semester = (String) tempRead.get("semester");
                end = (boolean) tempRead.get("end");
                extra_credit = (ArrayList<Double>) tempRead.get("extra_credits");
                //read cell matrix and use it to construct a sheet
                sheet = new Sheet(readCell(courseName+semester));
                students = readStudentInfo(courseName+semester);
                assignments = readAssignment(courseName+semester);
                criUG = readCriteria(courseName+semester+"UG");
                criG = readCriteria(courseName+semester+"G");
                Course course = new Course(courseName,lecturerName,semester,sheet,students,assignments,criUG,criG,end,extra_credit);
                listCourse.add(course);
                i = i + 1;
            }
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            try(FileWriter fw1 = new FileWriter("CourseList.json")){
                //fw1.write(out1.toJSONString());
                fw1.flush();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return listCourse;
    }
}

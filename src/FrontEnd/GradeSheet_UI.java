package FrontEnd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import BackEnd.*;



public class GradeSheet_UI extends JFrame implements ActionListener, MouseListener{
    JPanel pSheet = new JPanel();
    //JScrollPane spSheet;
    JButton addStudent = new JButton("+ Add Student");
    JButton removeStudent = new JButton("- Remove Student");
    JButton addColumn = new JButton("Alter Assignment");
    JButton back = new JButton("Return Class List");
    JButton grade = new JButton("Total Score");
    JButton complete = new JButton("End Course");
    JButton report = new JButton("BackEnd.Report");
    JButton exCredit = new JButton("Extra Credit");
    JButton addNote = new JButton("Save Note");
    JLabel note = new JLabel("Notes");
    JPanel pB6 = new JPanel();
    JPanel pB2 = new JPanel();
    JPanel pFunc6 = new JPanel();
    JPanel pFunc2 = new JPanel();
    JTextArea noteText = new JTextArea();
    static DefaultTableModel mSheet;
    JTable tSheet;
    static DefaultTableModel wSheet;
    JTable titleSheet;
    Course course;
    Grading_System grading_system;
    //ArrayList<String> starter = new ArrayList<>();

    //String[] columnNames = { "ID", "FirstName","LastName"};
    //
    //JLabel lSheet1 = new JLabel("Select Students Info：");
    //String[] listInfo = new String[]{"FirstName", "ID", "Email"};

    public GradeSheet_UI(Grading_System grading_system, Course course) {
        this.grading_system = grading_system;
        this.course = course;

        setTitle("Grade Sheet");
        Container contentPane = this.getContentPane();
        contentPane.setLayout(null);
        /*assignment list*/
        ArrayList<Assignment> ass = course.getAssignments();

        String[][] rowData = course.getTable();
        System.out.println(rowData + " --- loading getTable");
        int length = rowData[0].length;

        String[] columnNamesW = new String[length];
        System.out.println(course.extra() + " --- had extra?");
        System.out.println(length + " --- how many Column");

        columnNamesW[0] = "Item";
        columnNamesW[1] = "    ";
        int i = 2;
        for(Assignment a: ass){
            columnNamesW[i] = a.getName();
            i++;
        }

        String[] columnNames = new String[length];
        columnNames[0] = "ID";
        columnNames[1] = "Name";
        int j = 2;
        for(Assignment a: ass){
            columnNames[j] = a.getName();
            j++;
        }
        // student data！！！！
        int colSize = ass.size()+2;
        String[][] rowDataW = new String[3][colSize];
        rowDataW[0][0] = "Weight_Undergraduate";
        rowDataW[1][0] = "Weight_Graduate";
        rowDataW[2][0] = "Total";
        ArrayList<Double> weight_ug = course.getCriteria_UG().getWeight();
        ArrayList<Double> weight_g = course.getCriteria_G().getWeight();
        for(int c = 2; c < colSize; c++){
            rowDataW[0][c] = weight_ug.get(c-2).toString();
            rowDataW[1][c] = weight_g.get(c-2).toString();
            Double temp = ass.get(c-2).getTotal();
            rowDataW[2][c] = temp.toString();
        }
                //course.getAssignmentInformation();
        wSheet = new DefaultTableModel(rowDataW, columnNamesW) {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return (columnIndex != 0) & (columnIndex != 1);
            }
        };
        wSheet.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if(e.getColumn() <  columnNames.length && e.getColumn() >=0){
                    int row = e.getFirstRow();
                    int col = e.getColumn();
                    //Object value = mSheet.getValueAt(row,col);
                    String value = (String)  wSheet.getValueAt(row,col);
                    if(row == 0){
                        ArrayList creteria_ug = course.getCriteria_UG().getWeight();
                        creteria_ug.set(col-2, Double.parseDouble(value));
                        System.out.println("change the UG weight");
                    }else if(row ==1){
                        ArrayList creteria_g = course.getCriteria_G().getWeight();
                        creteria_g.set(col-2, Double.parseDouble(value));

                        System.out.println("change the G weight");
                    }else{
                        ArrayList assignTotal = course.getAssignments();
                        assignTotal.set(col, Double.parseDouble(value));
                    }


                    System.out.println(value + "changing in weight table");

                }
            }
        });

        titleSheet = new JTable(wSheet);
        // Set the size of scroll panel window
        titleSheet.setPreferredScrollableViewportSize(new Dimension(400, 300));
        //spSheet = new JScrollPane(tSheet);
        JScrollPane wp = new JScrollPane(titleSheet);
        mSheet = new DefaultTableModel(rowData, columnNames) {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return (columnIndex != 0) & (columnIndex != 1) & (rowIndex != 0);
            }
        };
        mSheet.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if(e.getColumn() <  columnNames.length && e.getColumn() >=0){
                    int row = e.getFirstRow();
                    int col = e.getColumn();
                    //Object value = mSheet.getValueAt(row,col);
                    String value = (String) mSheet.getValueAt(row,col);
                    //save changed score to sheet
                    course.setScore(row, col,value);
                    System.out.println(value +" ----change or add score");
                }
            }

        });

        tSheet = new JTable(mSheet);
        // Set the size of scroll panel window
        tSheet.setPreferredScrollableViewportSize(new Dimension(400, 300));
        //spSheet = new JScrollPane(tSheet);
        JScrollPane jp = new JScrollPane(tSheet);

        pFunc6.setLayout(new FlowLayout());
        pFunc2.setLayout(new FlowLayout());
        pB6.setLayout(new GridLayout(3,2));
        pB2.setLayout(new GridLayout(3,1));
        //set color of button
        complete.setForeground(Color.RED);
        grade.setForeground(Color.BLUE);
        noteText.setLineWrap(true);
        JScrollPane notePane = new JScrollPane(noteText);

        pB6.add(addStudent);
        pB6.add(removeStudent);
        pB6.add(addColumn);
        pB6.add(report);
        pB6.add(exCredit);
        pB6.add(grade);
        pB2.add(addNote);
        pB2.add(complete);
        pB2.add(back);

        pFunc6.add(pB6);
        pFunc2.add(pB2);
        wp.setBounds(50,50,600,75);
        jp.setBounds(50,125,600,400);
        note.setBounds(700,125,200,50);
        notePane.setBounds(670,175,280,350);
        pFunc6.setBounds(100,550,400,100);
        pFunc2.setBounds(700,550,200, 100);

        contentPane.add(jp);
        contentPane.add(wp);
        contentPane.add(notePane);
        //contentPane.add(noteText);
        contentPane.add(note);
        contentPane.add(pFunc6);
        contentPane.add(pFunc2);
        addStudent.addActionListener(this);
        removeStudent.addActionListener(this);
        back.addActionListener(this);
        addColumn.addActionListener(this);
        grade.addActionListener(this);
        complete.addActionListener(this);
        report.addActionListener(this);
        exCredit.addActionListener(this);
        addNote.addActionListener(this);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 700);
        setResizable(false);
        setVisible(true);
        tSheet.addMouseListener(this);
//    {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                noteText.setText(" ");
//                int row = tSheet.getSelectedRow();
//                int col = tSheet.getSelectedColumn();
//                String pullNote = course.getNote(row, col)[0]+"\n Last modification:"+course.getNote(row, col)[1];
//                System.out.println(course.getNote(row, col) +" ---- note info");
//                noteText.setText(pullNote);
//                //System.out.println(mSheet.getValueAt(row,col));
//
//            }
//        });

        //System.out.println(course.getStudents().get(0) + "  student");
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == back){
            dispose();
            new Select_Course_UI(grading_system);
        }
        else if(e.getSource() == addColumn){
//            mSheet.addColumn("New Column");
            dispose();
            new ModifyCol_UI(grading_system,course);
        }
        else if(e.getSource() == grade){
            int input = JOptionPane.showConfirmDialog(null, "Are you sure to get final grade?");
            //todo
        }
        else if(e.getSource() == complete){
            int input = JOptionPane.showConfirmDialog(null, "Are you sure to end this course?");
            if(input == 0){
                tSheet.setEnabled(false);
                titleSheet.setEnabled(false);
                System.out.println(course.endCourse() +" ---- end course");
            }
        }
        else if(e.getSource() == report){
            new GetReport_UI(grading_system,course);
        }
        else if(e.getSource() == exCredit){
            System.out.println(course.extra()+ "--- button");
            int input = JOptionPane.showConfirmDialog(null, "Are you sure to add extra credit?");
            if(input == 0 && course.extra() != 2){

                mSheet.addColumn("ExtraCredit");
                wSheet.addColumn("ExtraCredit");
                course.setExtra_credits(new ArrayList<>());

            }
        }
        else if(e.getSource() == addStudent){
            new Add_Student_single_UI(grading_system,course);
            dispose();
        }
        else if(e.getSource() == removeStudent){
            int select = tSheet.getSelectedRow();
            if(select == -1){
                JOptionPane.showMessageDialog(null,"Please select a student");
            }else{
                mSheet.removeRow(select);
                System.out.println(course.removeStudent(select-1)+" --- RemoveStudent");
            }
        }
        else if(e.getSource() == addNote){
            int col = tSheet.getSelectedColumn();
            int row = tSheet.getSelectedRow();
            String noteS = noteText.getText();
            if(col != -1 && row != -1) {
                System.out.println(course.setNote(row, col, noteS));
            }
        }
        else{
            int selectR = tSheet.getSelectedRow();
            int selectC = tSheet.getSelectedColumn();
            System.out.println(mSheet.getValueAt(selectR,selectC));
        }
    }

    public static void addRows(String id, String name){
        mSheet.addRow(new Object[]{id,name});

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        noteText.setText(" ");
        int row = tSheet.getSelectedRow();
        int col = tSheet.getSelectedColumn();
        String pullNote = course.getNote(row, col)[0]+"\n Last modification:"+course.getNote(row, col)[1];
        System.out.println(course.getNote(row, col) +" ---- note info");
        noteText.setText(pullNote);
        //System.out.println(mSheet.getValueAt(row,col));

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
//    public static void main(String[] args) {
//        new GradeSheet_UI();
//    }
}

package FrontEnd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import BackEnd.*;



public class GradeSheet_UI extends JFrame implements ActionListener{
    Course course = new Course();
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
    JLabel note = new JLabel("Notes");
    JPanel pB6 = new JPanel();
    JPanel pB2 = new JPanel();
    JPanel pFunc6 = new JPanel();
    JPanel pFunc2 = new JPanel();
    JTextField jt = new JTextField();
    static DefaultTableModel mSheet;
    JTable tSheet;
    //ArrayList<String> starter = new ArrayList<>();

    String[] columnNames = { "ID", "FirstName","LastName"};
    //
    //JLabel lSheet1 = new JLabel("Select Students Info：");
    //String[] listInfo = new String[]{"FirstName", "ID", "Email"};

    public GradeSheet_UI() {
        setTitle("Grade Sheet");
        Container contentPane = this.getContentPane();
        contentPane.setLayout(null);

        ArrayList<Assignment> ass= course.getAssignments();
        int length = ass.size() + 3;
        String[] columnNames = new String[length];
        columnNames[0] = "ID";
        columnNames[1] = "Name";
        columnNames[2] = "Name";
        int i = 3;
        for(Assignment a: ass){
            columnNames[i] = a.getName();
            i++;
        }


        // student data
        String[][] rowData = course.getTable();
//                {
//                {"Weights_UG","", "80", "80", "80", ""},
//                {"Weights_Graduate","", "80", "80", "80", ""},
//                {"Full Points","", "80", "80", "80", ""},
//                {},
//                {"U12345","Jack", "LJack","80", "80", "80", ""},
//                {"U1245","Jack","LJack", "80", "80", "80",  ""},
//                {"U1345","Jack","LJack", "80", "80", "80",  ""}
//        };
        //JTable tSheet = new JTable(rowData, columnNames);
        mSheet = new DefaultTableModel(rowData, columnNames) {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return (columnIndex != 0) & (columnIndex != 1);
            }
//            @Override
//            public String getValueAt(int rowIndex, int columnIndex) {
//                return rowData[rowIndex][columnIndex];
//            }

        };
        mSheet.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int row = e.getFirstRow();
                int col = e.getColumn();

                //Object value = mSheet.getValueAt(row,col);
                String value = (String)  mSheet.getValueAt(row,col);
                System.out.println(value);
            }
        });

        tSheet = new JTable(mSheet);
        // Set the size of scroll panel window
        tSheet.setPreferredScrollableViewportSize(new Dimension(400, 300));
        //spSheet = new JScrollPane(tSheet);
        JScrollPane jp = new JScrollPane(tSheet);
        jp.setBounds(50,50,600,400);
        contentPane.add(jp);
        note.setBounds(700,50,200,50);
        jt.setBounds(670,100,280,350);
        pFunc6.setLayout(new FlowLayout());
        pFunc2.setLayout(new FlowLayout());
        pB6.setLayout(new GridLayout(3,2));
        pB2.setLayout(new GridLayout(2,1));
        //set color of button
        complete.setForeground(Color.RED);
        grade.setForeground(Color.BLUE);
//        complete.setOpaque(true);
//        complete.setBorderPainted(false);
//        grade.setOpaque(true);
//        grade.setBorderPainted(false);
        pB6.add(addStudent);
        pB6.add(removeStudent);
        pB6.add(addColumn);
        pB6.add(report);
        pB6.add(exCredit);
        pB6.add(grade);
        pB2.add(complete);
        pB2.add(back);

        pFunc6.add(pB6);
        pFunc2.add(pB2);
        pFunc6.setBounds(100,500,400,100);
        pFunc2.setBounds(700,500,200, 100);
        contentPane.add(jt);
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
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 700);
        setResizable(false);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == back){
            dispose();
            new Select_Course_UI();
        }
        else if(e.getSource() == addColumn){
//            mSheet.addColumn("New Column");
            dispose();
            new ModifyCol_UI();
        }
        else if(e.getSource() == grade){
            int input = JOptionPane.showConfirmDialog(null, "Are you sure to get final grade?");
        }
        else if(e.getSource() == complete){
            int input = JOptionPane.showConfirmDialog(null, "Are you sure to end this course?");
        }
        else if(e.getSource() == report){
            new GetReport_UI();
        }
        else if(e.getSource() == exCredit){
            int input = JOptionPane.showConfirmDialog(null, "Are you sure to add extra credit?");
        }
        else if(e.getSource() == addStudent){
            new Add_Student_single_UI();
        }
        else if(e.getSource() == removeStudent){
            //??
//            int selectR = tSheet.getSelectedRow();
//            int selectC = tSheet.getSelectedColumn();
            //System.out.println("Row-- "+ selectR + "Col--  "+ selectC);

            int select = tSheet.getSelectedRow();
            //System.out.println(select);
            mSheet.removeRow(select);

        }
        else{
            int selectR = tSheet.getSelectedRow();
            int selectC = tSheet.getSelectedColumn();
            System.out.println(mSheet.getValueAt(selectR,selectC));
        }
    }

    public static void addRows(String id, String first, String last){
        mSheet.addRow(new Object[]{id,first,last});

}
}

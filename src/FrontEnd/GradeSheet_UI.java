package FrontEnd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;


public class GradeSheet_UI extends JFrame implements ActionListener{
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
    DefaultTableModel mSheet;
    //JLabel lSheet1 = new JLabel("Select Students Info：");
    //String[] listInfo = new String[]{"FirstName", "ID", "Email"};

    public GradeSheet_UI() {
        setTitle("Grade Sheet");
        Container contentPane = this.getContentPane();
        contentPane.setLayout(null);
        Object[] columnNames = {"Name", "HW1", "HW2", "Exam1", "Total"};
        // student data
        Object[][] rowData = {
                {"Jack", 80, 80, 80, ""},
                {"John", 70, 80, 90, ""},
                {"Sue", 70, 70, 70, ""},
                {"Jane", 80, 70, 60, ""},
                {"Joe", 80, 70, 60, ""}
        };
        //JTable tSheet = new JTable(rowData, columnNames);
        mSheet = new DefaultTableModel(rowData, columnNames) {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return (columnIndex != 0) & (columnIndex != 4);
            }
        };
        JTable tSheet = new JTable(mSheet);
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
            dispose();
            new GetReport();
        }
        else if(e.getSource() == exCredit){
            int input = JOptionPane.showConfirmDialog(null, "Are you sure to add extra credit?");
        }
    }
    public static void main(String[] args){
        new GradeSheet_UI();
    }
}

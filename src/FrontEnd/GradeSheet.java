package FrontEnd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;


public class GradeSheet extends JFrame implements ActionListener{
    JPanel pSheet = new JPanel();
    //JScrollPane spSheet;
    JButton back = new JButton("Return");
    JButton addColumn = new JButton("Alter Section");
    JButton grade = new JButton("Grade");
    JButton complete = new JButton("End Course");
    JButton report = new JButton("BackEnd.Report");
    JButton exCredit = new JButton("Extra Credit");
    DefaultTableModel mSheet;
    JLabel lSheet1 = new JLabel("Select Students Info：");
    String[] listInfo = new String[]{"FirstName", "ID", "Email"};

    public GradeSheet() {
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
        JTextField jt = new JTextField();
        jt.setBounds(670,50,280,400);
        contentPane.add(jt);
        //pSheet.add(tSheet.getTableHeader(), BorderLayout.NORTH);
        //pSheet.add(tSheet, BorderLayout.CENTER);
        JPanel p = new JPanel();
        JPanel p2 = new JPanel();
        p2.setLayout(new FlowLayout());
        p.setLayout(new GridLayout(1,0));
        p.add(back);
        p.add(addColumn);
        p.add(grade);
        p.add(complete);
        p.add(report);
        p.add(exCredit);
        p2.add(p);
        p2.setBounds(0,500,1000,100);
        contentPane.add(p2);
        back.addActionListener(this);
        addColumn.addActionListener(this);
        grade.addActionListener(this);
        complete.addActionListener(this);
        report.addActionListener(this);
        exCredit.addActionListener(this);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 600);
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
}

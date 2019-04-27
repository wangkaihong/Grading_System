package FrontEnd;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import BackEnd.*;

public class ModifyCol_UI extends JFrame implements ActionListener, MouseListener {
    JPanel pTable = new JPanel(new BorderLayout());
    JPanel pFuncs = new JPanel(new GridLayout(1,5));
    JPanel pWaring = new JPanel();
    ImageIcon icon = new ImageIcon("exclamation.jpg");
    JLabel img = new JLabel(icon);
    JLabel warn = new JLabel("Total Weight is over 100% !");
    JButton addRow = new JButton("Add");
    JButton back = new JButton("Back");
    JButton confirm = new JButton("Confirm");
    JTable tSheet;
    static DefaultTableModel table;
    Course course;
    Grading_System grading_system;


    public ModifyCol_UI(Grading_System grading_system, Course course){
        this.grading_system = grading_system;
        this.course = course;
        Container contentPane = this.getContentPane();
        contentPane.setLayout(null);

        FileIO fileIO = new FileIO();
        ArrayList<Assignment> tempNullList = new ArrayList<Assignment>();
        fileIO.writeTempAddAssign(tempNullList,course.getCourseName()+course.getSemester());

        String[] columnNames =  {"Name", "Total", "Weighted_UG", "Weighted_G","Scoring Way"};
        String[][] rowData = course.getAssignmentInformation();
        //item data
        table = new DefaultTableModel(rowData, columnNames) {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return (columnIndex != 4);
            }

        };
        table.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if(e.getColumn() <  columnNames.length && e.getColumn() >= 0){
                    int row = e.getFirstRow();
                    int col = e.getColumn();
                    //Object value = mSheet.getValueAt(row,col);
                    if(table.getValueAt(row,col) == null){
                        table.setValueAt(0,row,col);
                    }else {
                        String name = (String) table.getValueAt(row,0);
                        String totalS = (String)table.getValueAt(row,1);
                        String way = (String) table.getValueAt(row,4);
                        double total = Double.parseDouble(totalS);
                        System.out.println(course.changeAssignment(row,name,total,way) + "---- change assign");
                    }

                }
            }
        });
        tSheet = new JTable(table);
        JScrollPane scrollPane = new JScrollPane(tSheet);
        pTable.add(scrollPane);
        pFuncs.add(confirm);
        pFuncs.add(addRow);
        pFuncs.add(back);
        pWaring.add(img);
        pWaring.add(warn);
        back.addActionListener(this);
        confirm.addActionListener(this);
        addRow.addActionListener(this);
        tSheet.addMouseListener(this);
        pTable.setBounds(50,50,600,500);
        pFuncs.setBounds(675,400,300,50);
        pWaring.setBounds(650,200,300,200);
        //pWaring.setVisible(false);
        contentPane.add(pTable);
        contentPane.add(pFuncs);
        contentPane.add(pWaring);
        pWaring.setVisible(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(1000, 600);
        this.setTitle("Modify Column");
        this.setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            dispose();
            new GradeSheet_UI(grading_system,course);
        }else if(e.getSource() == addRow){
            //String selection = this.G1.getSelection().getActionCommand();
            //addRows(selection);
            new AddAssignment_UI(grading_system,course);
        }else if(e.getSource() ==confirm){
            int size = table.getRowCount();
            double[] weightGpush= new double[size];
            double[] weightUpush= new double[size];
            for(int i = 0; i < size; i++){
                Object wsu =  table.getValueAt(i,2);
                Object wsg = table.getValueAt(i,3);
                if(wsu instanceof String) {
                    weightUpush[i] = Double.valueOf((String) wsu);
                }
                if(wsu instanceof Double) {
                    weightUpush[i] = (double)wsu;
                }
                if(wsg instanceof String) {
                    weightGpush[i] =  Double.valueOf((String) wsg);
                }
                if(wsu instanceof Double) {
                    weightGpush[i] = (double) wsg;
                }

            }
            if(sum(weightGpush) <= 1 && sum(weightUpush) <= 1) {
                FileIO fileIO = new FileIO();
                ArrayList<Assignment> tempAddAssignList = fileIO.readTempAddAssign(course.getCourseName()+course.getSemester());
                if(tempAddAssignList.size() != 0){
                    for(Assignment assign : tempAddAssignList){
                        course.addAssignment(assign.getName(), assign.getTotal(), assign.getScoring_method());
                    }
                } else {
                    System.out.println("Invalid Assignments");
                }
                System.out.println(course.changeCriteria_G(weightGpush) + "add Gweight");
                System.out.println(course.changeCriteria_UG(weightUpush) + "add Uweight");
                dispose();
                new GradeSheet_UI(grading_system, course);
            }else{
                JOptionPane.showMessageDialog(null,"Total weights is invalid!");
            }
        }
    }
    public static void addRows(String name, String total, String weight, String weightG, String scoring){
        table.addRow(new Object[]{name,total,weight,weightG,scoring});

    }
    private double sum(double[] arr){
        double res = 0;
        for(int i = 0; i < arr.length; i++){
            res += arr[i];
        }
        return res;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        double totalWeight = 0;
        double totalWeightG = 0;

        for (int i = 0; i < tSheet.getRowCount(); i++){
            double amount = Double.parseDouble((String) tSheet.getValueAt(i, 2));
            double amountG = Double.parseDouble((String) tSheet.getValueAt(i, 3));
            totalWeight += amount;
            totalWeightG += amountG;

        }
        System.out.println(totalWeight + "--total weight");
        if(totalWeight > 1 || totalWeightG > 1){
            pWaring.setVisible(true);
        }else{
            pWaring.setVisible(false);
        }
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
}

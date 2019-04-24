package FrontEnd;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import BackEnd.*;

public class ModifyCol_UI extends JFrame implements ActionListener {
    JPanel pTable = new JPanel(new BorderLayout());
    JPanel pFuncs = new JPanel(new GridLayout(1,5));
    JPanel pWaring = new JPanel();
    ImageIcon icon = new ImageIcon("exclamation.jpg");
    JLabel img = new JLabel(icon);
    JLabel warn = new JLabel("Total Weight is over 100% !");
    JButton addRow = new JButton("Add");
    JButton back = new JButton("Back");
    JButton confirm = new JButton("Confirm");
    static DefaultTableModel table;
    static double totalWeight;
    Course course;
    Grading_System grading_system;


    public ModifyCol_UI(Grading_System grading_system, Course course){
        this.grading_system = grading_system;
        this.course = course;
        Container contentPane = this.getContentPane();
        contentPane.setLayout(null);

        String[] columnNames =  {"Name", "Total", "Weighted_UG", "Weighted_G","Scoring Way"};
        String[][] rowData = course.getAssignmentInformation();
        //item data
        table = new DefaultTableModel(rowData, columnNames) {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return (columnIndex != 5);
            }

        };
        table.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if(e.getColumn() >  columnNames.length && e.getFirstRow() != 0 ){
                    int row = e.getFirstRow();
                    int col = e.getColumn();
                    //Object value = mSheet.getValueAt(row,col);
                    String value = (String)  table.getValueAt(row,col);
                    System.out.println(value);

                }
            }
        });
        JTable tSheet = new JTable(table);
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
        pTable.setBounds(50,50,600,500);
        pFuncs.setBounds(675,400,300,50);
        pWaring.setBounds(650,200,300,200);
        //pWaring.setVisible(false);
        contentPane.add(pTable);
        contentPane.add(pFuncs);
        contentPane.add(pWaring);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(1000, 600);
        this.setTitle("Modify Column");
        this.setVisible(true);

        for (int i = 0; i < tSheet.getRowCount(); i++){
            double amount = Double.parseDouble((String) tSheet.getValueAt(i, 2));
            totalWeight += amount;
        }
        System.out.println(totalWeight + "aaa");
        if(totalWeight > 1){
            pWaring.setVisible(true);
        }


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
            dispose();
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
                    weightGpush[i] =  Double.valueOf((String)wsg);
                }
                if(wsu instanceof Double) {
                    weightGpush[i] = (double)wsg;
                }
            }
            System.out.println(course.changeCriteria_G(weightGpush) + "add Gweight");
            System.out.println(course.changeCriteria_UG(weightUpush)+ "add Uweight");
            new GradeSheet_UI(grading_system,course);
        }
    }
    public static void addRows(String name, double total, double weight,double weightG, String scoring){
        table.addRow(new Object[]{name,total,weight,weightG,scoring});

    }


}

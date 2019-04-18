package FrontEnd;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ModifyCol_UI extends JFrame implements ActionListener {
    JPanel pTable = new JPanel(new BorderLayout());
    JPanel pFuncs = new JPanel(new GridLayout(1,5));
    JRadioButton deduction = new JRadioButton("Deduction");
    JRadioButton percentage = new JRadioButton("Percentage");
    ButtonGroup G1 = new ButtonGroup();
    JButton addRow = new JButton("Add");
    JButton back = new JButton("Back");
    JButton confirm = new JButton("Confirm");
    static DefaultTableModel table;


    public ModifyCol_UI(){
        Container contentPane = this.getContentPane();
        contentPane.setLayout(null);

        String[] columnNames =  {"Name", "Total", "Weighted", "Scoring Way"};
        //item data
        Object[][] rowData = {{"hw1",100, 20,"deduction"},{"hw2",160, 10, "percentage"}};
        table = new DefaultTableModel(rowData, columnNames) {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return (columnIndex != 4);
            }
        };
        JTable tSheet = new JTable(table);
        JScrollPane scrollPane = new JScrollPane(tSheet);
        pTable.add(scrollPane);
        pFuncs.add(confirm);
        pFuncs.add(addRow);
        pFuncs.add(back);
        back.addActionListener(this);
        confirm.addActionListener(this);
        addRow.addActionListener(this);

        pTable.setBounds(50,50,500,500);
        pFuncs.setBounds(600,400,300,50);
        contentPane.add(pTable);
        contentPane.add(pFuncs);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(1000, 600);
        this.setTitle("Modify Column");
        this.setVisible(true);


    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            dispose();
            new GradeSheet();
        }else if(e.getSource() == addRow){
            //String selection = this.G1.getSelection().getActionCommand();
            //addRows(selection);
            new AddAssignment_UI();
        }else if(e.getSource() ==confirm){
            dispose();
            new GradeSheet();
        }
    }
    public static void addRows(String name, double total, double weight, String scoring){
        table.addRow(new Object[]{name,total,weight, scoring});

    }

}

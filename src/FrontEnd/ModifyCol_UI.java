package FrontEnd;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


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
        pWaring.add(img);
        pWaring.add(warn);
        back.addActionListener(this);
        confirm.addActionListener(this);
        addRow.addActionListener(this);
        pTable.setBounds(50,50,500,500);
        pFuncs.setBounds(600,400,300,50);
        pWaring.setBounds(600,200,300,200);
        if(totalWeight > 1){
            pWaring.setVisible(true);
        }
        pWaring.setVisible(false);
        contentPane.add(pTable);
        contentPane.add(pFuncs);
        contentPane.add(pWaring);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(1000, 600);
        this.setTitle("Modify Column");
        this.setVisible(true);


    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            dispose();
            new GradeSheet_UI();
        }else if(e.getSource() == addRow){
            //String selection = this.G1.getSelection().getActionCommand();
            //addRows(selection);
            new AddAssignment_UI();
        }else if(e.getSource() ==confirm){
            dispose();
            new GradeSheet_UI();
        }
    }
    public static void addRows(String name, double total, double weight, String scoring){
        table.addRow(new Object[]{name,total,weight, scoring});

    }


}

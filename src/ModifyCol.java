import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ModifyCol extends JFrame implements ActionListener {
    JPanel pTable = new JPanel(new BorderLayout());
    JPanel pFuncs = new JPanel(new GridLayout(1,5));
    JRadioButton deduction = new JRadioButton("Deduction");
    JRadioButton percentage = new JRadioButton("Percentage");
    ButtonGroup G1 = new ButtonGroup();
    JButton addRow = new JButton("Add");
    JButton back = new JButton("Back");
    JButton confirm = new JButton("Confirm");
    DefaultTableModel table;


    public ModifyCol(){
        Container contentPane = this.getContentPane();
        contentPane.setLayout(null);

        String[] columnNames =  {"Name", "Weighted", "Scoring Way"};
        //item data
        Object[][] rowData = {{"hw1",20,"deduction"},{"hw2", 10, "percentage"}};

        table = new DefaultTableModel(rowData, columnNames) {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return (columnIndex != 2);
            }
        };
        JTable tSheet = new JTable(table);
        JScrollPane scrollPane = new JScrollPane(tSheet);
        pTable.add(scrollPane);
        pFuncs.add(deduction);
        pFuncs.add(percentage);
        pFuncs.add(confirm);
        pFuncs.add(addRow);
        pFuncs.add(back);
        G1.add(deduction);
        G1.add(percentage);
        back.addActionListener(this);
        confirm.addActionListener(this);
        addRow.addActionListener(this);
        this.deduction.setActionCommand("Deduction");
        this.percentage.setActionCommand("Percentage");
        pTable.setBounds(50,50,900,300);
        pFuncs.setBounds(50,400,900,150);
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
            String selection = this.G1.getSelection().getActionCommand();
            addRows(selection);
        }else if(e.getSource() ==confirm){
            dispose();
            new GradeSheet();
        }
    }
    public void addRows(String scoring){
        table.addRow(new Object[]{"new", 0, scoring});

    }

}

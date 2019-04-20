package FrontEnd;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GetReport_UI extends JFrame implements ActionListener{
    JPanel selectReport = new JPanel();
    JPanel confirmOrNot = new JPanel();
    JLabel select = new JLabel("Select one assignment to view report:");
    String assignmentList[] = {"HW1", "HW2", "HW3", "Exam", "Attendance", "Extra", "Total"};
    JComboBox assignment = new JComboBox(assignmentList);
    JButton confirm = new JButton("Confirm");
    JButton returnBack = new JButton("Return");

    static String getSelect;

    public GetReport_UI(){
        Container contentPane = this.getContentPane();

        selectReport.add(select);
        selectReport.add(assignment);
        confirmOrNot.add(confirm);
        confirmOrNot.add(returnBack);

        confirm.addActionListener(this);
        returnBack.addActionListener(this);
        getSelect = "HW1";
        assignment.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    getSelect = assignment.getSelectedItem().toString();
                }
            }
        });
        assignment.setSelectedIndex(0);

        selectReport.setLayout(new GridLayout(2,1));
        confirmOrNot.setLayout(new GridLayout(1,2));
        selectReport.setBounds(10,10,600,50);


        confirmOrNot.setLayout(new GridLayout(2,1));
        confirmOrNot.add(confirm);
        confirmOrNot.add(returnBack);
        confirmOrNot.setBounds(420,250,120,60);


        contentPane.setLayout(null);;
        contentPane.add(selectReport);
        contentPane.add(confirmOrNot);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(640, 360);
        setTitle("Get reports");
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirm){
            new Report_UI();
            dispose();
        }
        if (e.getSource() == returnBack){
            dispose();
            new Report_UI();
        }

    }


}
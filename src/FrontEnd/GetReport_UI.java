package FrontEnd;

import BackEnd.Assignment;
import BackEnd.Course;
import BackEnd.Grading_System;

import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import javax.swing.*;

public class GetReport_UI extends JFrame implements ActionListener{
    JPanel selectReport = new JPanel();
    JPanel confirmOrNot = new JPanel();
    JLabel select = new JLabel("Select one assignment to view report:");
    JButton confirm = new JButton("Confirm");
    JButton returnBack = new JButton("Return");

    static String getSelect;
    public Course courseBE;
    public Grading_System grading_system;
    public GetReport_UI(Grading_System grading_system, Course course){
        courseBE = course;
        this.grading_system = grading_system;
        Container contentPane = this.getContentPane();
        //String assignmentList[] = (String[]) courseBE.getAssignments().toArray();
        ArrayList<Assignment> assignments = courseBE.getAssignments();
        String assignmentList[] = new String[assignments.size()];
        for( int i =0; i < assignmentList.length;i++) {
            assignmentList[i] = assignments.get(i).getName();
        }

        JComboBox assignment = new JComboBox(assignmentList);

        selectReport.add(select);
        selectReport.add(assignment);
        confirmOrNot.add(confirm);
        confirmOrNot.add(returnBack);

        confirm.addActionListener(this);
        returnBack.addActionListener(this);
        assignment.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    getSelect = assignment.getSelectedItem().toString();
                }
            }
        });

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
            new Report_UI(grading_system,courseBE);
            dispose();
        }
        if (e.getSource() == returnBack){
            dispose();

        }

    }


}
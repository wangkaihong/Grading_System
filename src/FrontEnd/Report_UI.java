package FrontEnd;

import BackEnd.Assignment;
import BackEnd.Course;
import BackEnd.Grading_System;
import BackEnd.Report;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Report_UI extends JFrame implements ActionListener {
    JPanel assignmentLabel = new JPanel();
    JPanel analysisTable = new JPanel();
    JPanel returnBack = new JPanel();
    JLabel title;
    JButton returnToTable = new JButton("return to main table");
    String[] analysisNames = { "Analysis", "Result"};
    DefaultTableModel analysis;

    Grading_System grading_system;
    Course course;

    public Report_UI(Grading_System grading_system, Course course) {
        this.grading_system = grading_system;
        this.course = course;

        Container contentPane = this.getContentPane();
        contentPane.setLayout(null);

        ArrayList<Assignment> assignments = course.getAssignments();
        String assignmentList[] = new String[assignments.size()];
        for( int i =0; i < assignmentList.length;i++) {
            assignmentList[i] = assignments.get(i).getName();
        }

        title = new JLabel(assignmentList[GetReport_UI.getSelect]);
        assignmentLabel.setLayout(new GridLayout(1,1));
        title.setFont(new Font("Serif",Font.PLAIN,30));
        assignmentLabel.add(title);
        assignmentLabel.setBounds(30,40,100,40);
        contentPane.add(assignmentLabel);

//        Object[][] analysisData = {
//                {"Student1", "-10"},
//                {"Student1", "-60"},
//                {"Student1", "-30"},
//                {"Student1", "-59"},
//        };
//        report = new DefaultTableModel(reportData, reportNames);
//        JTable reportSheet = new JTable(report);
//        reportSheet.setPreferredScrollableViewportSize(new Dimension(300, 300));
//        reportTable.add(reportSheet);
//        reportTable.setBounds(30,100,150,250);
//        contentPane.add(reportTable);

        String[][] analysisData = new String[4][2];
        for (int i = 0; i < 4;i ++) {
            analysisData[i][1] =course.reportAssignToUI(GetReport_UI.getSelect)[0][i];
        }
        analysisData[0][0] = "min";
        analysisData[1][0] = "max";
        analysisData[2][0] = "average";
        analysisData[3][0] = "middle";

        System.out.println(analysisData[0].length);

        analysis = new DefaultTableModel(analysisData, analysisNames);
        JTable analysisSheet = new JTable(analysis);
        analysisSheet.setPreferredScrollableViewportSize(new Dimension(600, 400));
        analysisTable.add(analysisSheet);
        analysisTable.setBounds(0,100,600,400);
        contentPane.add(analysisTable);

        returnToTable.addActionListener(this);
        returnBack.add(returnToTable);
        returnBack.setBounds(420,250,150,30);
        contentPane.add(returnBack);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(640, 360);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == returnToTable){
            this.setVisible(false);
        }
    }
}
package FrontEnd;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Report_UI extends JFrame implements ActionListener {
    JPanel assignmentLabel = new JPanel();
    JPanel reportTable = new JPanel();
    JPanel analysisTable = new JPanel();
    JPanel returnBack = new JPanel();
    JLabel title = new JLabel(GetReport_UI.getSelect);
    JButton returnToTable = new JButton("return to main table");
    Object[] reportNames = { "Students", "Grades"};
    Object[] analysisNames = { "Analysis", "Result"};
    DefaultTableModel report;
    DefaultTableModel analysis;


    public Report_UI() {
        Container contentPane = this.getContentPane();
        contentPane.setLayout(null);

        assignmentLabel.setLayout(new GridLayout(1,1));
        title.setFont(new Font("Serif",Font.PLAIN,30));
        assignmentLabel.add(title);
        assignmentLabel.setBounds(30,40,100,40);
        contentPane.add(assignmentLabel);

        Object[][] reportData = {
                {"Student1", "-10"},
                {"Student1", "-60"},
                {"Student1", "-30"},
                {"Student1", "-59"},
        };
        report = new DefaultTableModel(reportData, reportNames);
        JTable reportSheet = new JTable(report);
        reportSheet.setPreferredScrollableViewportSize(new Dimension(300, 300));
        reportTable.add(reportSheet);
        reportTable.setBounds(30,100,150,250);
        contentPane.add(reportTable);

        Object[][] analysisData = {
                {"Min", "40"},
                {"Max", "90"},
                {"Average", "62.5"},
                {"Median", "60"},
        };


        analysis = new DefaultTableModel(analysisData, analysisNames);
        JTable analysisSheet = new JTable(analysis);
        analysisSheet.setPreferredScrollableViewportSize(new Dimension(300, 300));
        analysisTable.add(analysisSheet);
        analysisTable.setBounds(400,100,200,200);
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
            new GradeSheet_UI();
            this.setVisible(false);
        }
    }
}
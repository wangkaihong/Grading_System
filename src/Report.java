import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Report extends JFrame implements ActionListener {
    JPanel report = new JPanel();
    JLabel title = new JLabel(GetReport.getSelect);
    JButton returnToTable = new JButton("return to main table");


    public Report() {
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new GridLayout(2, 2));

        report.setLayout(new GridLayout(2, 1));
        report.add(title);
        report.add(returnToTable);
        contentPane.add(report);

        returnToTable.addActionListener(this);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 600);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == returnToTable){
            new GradeSheet();
            this.setVisible(false);
        }
    }
}
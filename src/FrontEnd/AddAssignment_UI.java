package FrontEnd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddAssignment_UI extends JFrame implements ActionListener {
    JPanel p = new JPanel();
    JPanel func = new JPanel();
    JPanel radioPanel = new JPanel();
    JLabel name = new JLabel("Assignment Name: ");
    JTextField assignmentname = new JTextField(20);
    JLabel total = new JLabel("Total Point: ");
    JTextField totalPoint = new JTextField(20);
    JLabel weightU = new JLabel("Weight For Undergraduate: ");
    JTextField weightedU = new JTextField(20);
    JLabel weightG = new JLabel("Weight For Grad: ");
    JTextField weightedG = new JTextField(20);
    JRadioButton deduction = new JRadioButton("Deduction");
    JRadioButton percentage = new JRadioButton("Percentage");
    JRadioButton raw = new JRadioButton("Raw Point");
    ButtonGroup G1 = new ButtonGroup();
    JButton confirm = new JButton("Confirm");
    JButton ret = new JButton("Cancel");
    String scoring = "" ;

    public AddAssignment_UI(){
        Container contentPane = this.getContentPane();
        FlowLayout layout = new FlowLayout();
        layout.setHgap(100);
        layout.setVgap(30);
        contentPane.setLayout(layout);
        p.setLayout(new GridLayout(4,2));
        radioPanel.setLayout(new GridLayout(1, 3));
        radioPanel.add(raw);
        radioPanel.add(deduction);
        radioPanel.add(percentage);
        G1.add(raw);
        G1.add(deduction);
        G1.add(percentage);
        radioPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Scoring Method"));
        p.add(name);
        p.add(assignmentname);
        p.add(total);
        p.add(totalPoint);
        p.add(weightU);
        p.add(weightedU);
        p.add(weightG);
        p.add(weightedG);
        func.add(confirm);
        this.deduction.setActionCommand("Deduction");
        this.percentage.setActionCommand("Percentage");
        this.raw.setActionCommand("raw");
        confirm.addActionListener(this);
        func.add(ret);
        ret.addActionListener(this);
        p.setBounds(100,100, 475,100);
        //contentPane.setSize(450,160);
        contentPane.add(p);
        contentPane.add(radioPanel);
        contentPane.add(func);
        //contentPane.add(p);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(640, 360);
        setTitle("Add New Assignment");
        setResizable(false);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == ret) {
            dispose();
            //new ModifyCol();
        }
        if(e.getSource() == confirm) {
            scoring = this.G1.getSelection().getActionCommand();
            String name = assignmentname.getText();
            double total = Double.parseDouble(totalPoint.getText());
            double weightU = Double.parseDouble(weightedU.getText());
            double weightG = Double.parseDouble(weightedG.getText());
            ModifyCol_UI.addRows(name, total, weightU, weightG, scoring);
            dispose();
            System.out.print("scoring way is :" + scoring);
            // add course info to course
        }
    }


}

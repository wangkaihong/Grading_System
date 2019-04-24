package FrontEnd;

import BackEnd.Course;

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
    JLabel sameWeight = new JLabel("Please check if Weights are indifferent: ");
    JCheckBox copy = new JCheckBox();
    Course course = ModifyCol_UI.course;

    public AddAssignment_UI(){
        Container contentPane = this.getContentPane();
        FlowLayout layout = new FlowLayout();
        layout.setHgap(100);
        layout.setVgap(30);
        contentPane.setLayout(layout);
        p.setLayout(new GridLayout(5,2));
        radioPanel.setLayout(new GridLayout(1, 3));
        radioPanel.add(raw);
        radioPanel.add(deduction);
        radioPanel.add(percentage);
        G1.add(raw);
        G1.add(deduction);
        G1.add(percentage);
        raw.setSelected(true);
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
        p.add(sameWeight);
        p.add(copy);
        func.add(confirm);
        copy.addActionListener(this);
        this.deduction.setActionCommand("deduction");
        this.percentage.setActionCommand("percentage");
        this.raw.setActionCommand("raw");
        confirm.addActionListener(this);
        func.add(ret);
        ret.addActionListener(this);
        p.setBounds(100,100, 475,100);
        //contentPane.setSize(450,160);
        contentPane.add(p);
        //contentPane.add(copy);
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
        if(copy.isSelected()){
            if(weightedU.getText().equals("")){
                weightedU.setText(weightedG.getText());
            }else if(weightedG.getText().equals("")){
                weightedG.setText(weightedU.getText());
            }
        }
        //if(e.getSource() == copy)
        if(e.getSource() == ret) {
            dispose();
            //new ModifyCol();
        }
        else if(e.getSource() == confirm) {

            String name = assignmentname.getText();
            String totalS = totalPoint.getText();
            String us = weightedU.getText();
            String gs = weightedG.getText();
            double total = Double.parseDouble(totalPoint.getText());
            double weightU = Double.parseDouble(weightedU.getText());
            double weightG = Double.parseDouble(weightedG.getText());
            String scoring  = this.G1.getSelection().getActionCommand();
            if(scoring.isBlank() || name.isBlank()|| totalS.isBlank() ||(us.isBlank() || us.isBlank())){
                JOptionPane.showMessageDialog(null,"Please fill all necessary blank");
            }
            else if (us.isBlank() && gs.isBlank() != true){
                weightG = Double.parseDouble(gs);
                weightU = weightG;
            }else if(gs.isBlank() && us.isBlank() != true){
                weightU = Double.parseDouble(us);
                weightG = weightU;
            }
            //System.out.println(name + total+ weightU +weightG+scoring);
            System.out.println(course.addAssignment(name, total,scoring));

            /*add criteria??? */
            ModifyCol_UI.addRows(name, total, weightU, weightG, scoring);
            dispose();
            System.out.print("scoring way is :" + scoring);
        }
    }


}

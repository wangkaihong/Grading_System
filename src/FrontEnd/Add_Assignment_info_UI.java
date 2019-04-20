package FrontEnd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Add_Assignment_info_UI extends JFrame implements ActionListener {
    JPanel importFrom = new JPanel();
    JPanel addAssignment = new JPanel();
    JPanel buttons = new JPanel();
    JPanel weights = new JPanel();

    JComboBox courses = new JComboBox(Select_Course_UI.course);
    JTextField assignmentName = new JTextField();
    JTextField weight = new JTextField();
    JButton next = new JButton("Next");
    JButton returnBack = new JButton("Return");
    JButton cancel = new JButton("Cancel");


    public Add_Assignment_info_UI(){
        Container contentPane = this.getContentPane();
        contentPane.setLayout(null);

        importFrom.setLayout(new GridLayout(2,2));
        importFrom.add(new JLabel("Import From"));
        importFrom.add(courses);
        importFrom.add(new JLabel("Or"));
        importFrom.add(new JLabel());
        importFrom.setBounds(20,20,600,100);
        contentPane.add(importFrom);

        addAssignment.setLayout(new GridLayout(1,2));
        weights.setLayout(new GridLayout(1,2));
        addAssignment.add(new JLabel("One initial Assignment name: "));
        addAssignment.add(assignmentName);
        weights.add(new JLabel("Weight"));
        weights.add(weight);
        addAssignment.setBounds(20,130,400,30);
        weights.setBounds(420,130,200,30);
        contentPane.add(addAssignment);
        contentPane.add(weights);



        next.addActionListener(this);
        returnBack.addActionListener(this);
        cancel.addActionListener(this);
        buttons.setLayout(new GridLayout(1,3));
        buttons.add(next);
        buttons.add(returnBack);
        buttons.add(cancel);
        buttons.setBounds(300,250,300,30);
        contentPane.add(buttons);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(640, 360);
        setTitle("Add section information");
        setResizable(false);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == next){
            dispose();
            new Add_Student_info_UI();
        } else if (e.getSource() == cancel){
            dispose();
            new Select_Course_UI();
        } else if (e.getSource() == returnBack){
            dispose();
            new Add_Class_UI();
        }

    }

}

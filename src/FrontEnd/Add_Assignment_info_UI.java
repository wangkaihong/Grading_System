package FrontEnd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by wangkaihong on 2019/3/30.
 */
public class Add_Assignment_info_UI extends JFrame implements ActionListener {
    JPanel importFrom = new JPanel();
    JPanel addAssignment = new JPanel();
    JPanel buttons = new JPanel();
    JPanel weights = new JPanel();

    JComboBox courses = new JComboBox(Select_Course_UI.course);
    JTextField assignmentName = new JTextField();
    JTextField weight = new JTextField();
    JButton next = new JButton("Next");
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
        cancel.addActionListener(this);
        buttons.setLayout(new GridLayout(2,1));
        buttons.add(next);
        buttons.add(cancel);
        buttons.setBounds(420,250,120,60);
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
        }

    }

}

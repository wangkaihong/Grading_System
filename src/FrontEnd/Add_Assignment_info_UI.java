package FrontEnd;

import BackEnd.Course;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class Add_Assignment_info_UI extends JFrame implements ActionListener {
    JPanel importFrom = new JPanel();
    JPanel addAssignment = new JPanel();
    JPanel buttons = new JPanel();
    JPanel weights = new JPanel();
    JPanel addButton = new JPanel();

    JComboBox courses = new JComboBox(Select_Course_UI.course);
    JTextField assignmentName = new JTextField();
    JTextField weight = new JTextField();
    JButton next = new JButton("Next");
    JButton returnBack = new JButton("Return");
    JButton cancel = new JButton("Cancel");

    JButton addSection = new JButton("Add more");

    public static ArrayList<JTextField> name_fields = new ArrayList<>();
    public static ArrayList<JTextField> weight_fields = new ArrayList<>();

    private Course course = new Course();

    public Add_Assignment_info_UI(String name, String lecturerName, String semesterName){
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
        addAssignment.add(new JLabel("Input Assignment name: "));
        addAssignment.add(assignmentName);
        weights.add(new JLabel("Weight"));
        weights.add(weight);
        addAssignment.setBounds(20,130,400,30);
        weights.setBounds(420,130,200,30);
        contentPane.add(addAssignment);
        contentPane.add(weights);

        addButton.setBounds(500,200,50,30);
        addSection.addActionListener(this);
        addButton.add(addSection);
        contentPane.add(addButton);


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

    public void add_section() {
        setVisible(false);
        JPanel p2 = new JPanel();

        JLabel name_label = new JLabel("Name of the section:");
        JTextField name = new JTextField(20);
        p2.add(name_label);
        name_fields.add(name);
        p2.add(name);

        JLabel weight_label = new JLabel("Weight:");
        JTextField weight = new JTextField(20);
        weight_fields.add(weight);
        p2.add(weight_label);
        p2.add(weight);
        Container contentPane = this.getContentPane();
        contentPane.add(p2);

        setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == next){
            dispose();
            name_fields.add(assignmentName);
            weight_fields.add(weight);


            new Add_Student_info_UI();
        } else if (e.getSource() == cancel){
            dispose();
            new Select_Course_UI(gradingSystem, courseList);
        } else if (e.getSource() == returnBack){
            dispose();
            new Add_Class_UI();
        } else if (e.getSource() == addSection){
//            add_section();
        }

    }

}

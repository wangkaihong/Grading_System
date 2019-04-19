package FrontEnd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


public class Select_Course_UI extends JFrame implements ActionListener {
    JPanel courseList = new JPanel();
    JPanel buttons = new JPanel();

    String[] course = new String[10];
    JLabel courseLabel = new JLabel("Course: ");


    JButton enter = new JButton("Enter Course");
    JButton add = new JButton("Create New Course");
    JButton logout = new JButton("Log out");

    static String getSelect;

    public Select_Course_UI() {
        course[0] = "CS 591 P1: Object Oriented Design";
        JComboBox courses = new JComboBox(course);
        Container contentPane = this.getContentPane();
        getSelect = "CS 591 P1: Object Oriented Design";

        contentPane.setLayout(null);

        courses.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    getSelect = courses.getSelectedItem().toString();
                }
            }
        });


        courseList.setLayout(new GridLayout(1, 2));
        courseList.add(courseLabel);
        courseList.add(courses);
        courseList.setBounds(50, 100, 500, 30);
        contentPane.add(courseList);

        add.addActionListener(this);
        enter.addActionListener(this);
        logout.addActionListener(this);
        buttons.setLayout(new GridLayout(3, 1));
        buttons.add(enter);
        buttons.add(add);
        buttons.add(logout);
        buttons.setBounds(400, 200, 200, 100);
        contentPane.add(buttons);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(640, 360);
        setTitle("Select Course");
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            dispose();
            new Add_Class_UI();
        } else if (e.getSource() == enter) {
            for (int i = 0; i < course.length; i++) {
                if (getSelect.equals(course[i])) {
                    dispose();
                    new GradeSheet_UI();
                }
            }
        } else if (e.getSource() == logout) {
            dispose();
            new Grading_System_UI();
        }
    }
}
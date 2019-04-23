package FrontEnd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Add_Class_UI extends JFrame implements ActionListener {
    JPanel labels = new JPanel();
    JPanel inputs = new JPanel();
    JPanel buttons = new JPanel();

    JLabel courseName = new JLabel("Course Name: ");
    JLabel lecturer = new JLabel("lecturer: ");
    JLabel semester = new JLabel("Semester: ");

    JTextField nameInput = new JTextField();
    JTextField lecturerInput = new JTextField();
    JTextField semesterInput = new JTextField();

    JButton next = new JButton("Next");
    JButton cancel = new JButton("Cancel");

    public static String name;
    public static String lecturerName;
    public static String semesterName;

    public Add_Class_UI(){
        Container contentPane = this.getContentPane();
        contentPane.setLayout(null);

        labels.setLayout(new GridLayout(3, 1));
        labels.add(courseName);
        labels.add(lecturer);
        labels.add(semester);
        labels.setBounds(50,40,100,180);
        contentPane.add(labels);

        inputs.setLayout(new GridLayout(7, 1));
        inputs.add(new JLabel());
        inputs.add(nameInput);
        inputs.add(new JLabel());
        inputs.add(lecturerInput);
        inputs.add(new JLabel());
        inputs.add(semesterInput);
        inputs.add(new JLabel());
        inputs.setBounds(170,30,350,205);
        contentPane.add(inputs);

        next.addActionListener(this);
        cancel.addActionListener(this);
        buttons.setLayout(new GridLayout(2,1));
        buttons.add(next);
        buttons.add(cancel);
        buttons.setBounds(420,250,120,60);
        contentPane.add(buttons);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(640, 360);
        setTitle("Add Course");
        setResizable(false);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == next){
            dispose();
            name = nameInput.getText();
            lecturerName = lecturerInput.getText();
            semesterName = semesterInput.getText();
            for (int i = 0; i < Select_Course_UI.course.length; i++){
                if (Select_Course_UI.course[i] == null){
                    Select_Course_UI.course[i] = name;
                    break;
                }
            }
            new Add_Assignment_info_UI(name, lecturerName, semesterName);
        } else if(e.getSource() == cancel){
            dispose();
            new Select_Course_UI(gradingSystem, courseList);
        }
    }

}

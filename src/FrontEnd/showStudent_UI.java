package FrontEnd;

import BackEnd.Course;
import BackEnd.Grading_System;
import BackEnd.Graduate;
import BackEnd.Undergraduate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class showStudent_UI extends JFrame implements ActionListener {
    JPanel infoP = new JPanel();
    JPanel bntP = new JPanel();
    JPanel radioPanel = new JPanel();
    JLabel firstName = new JLabel("First Name: ");
    JLabel fname = new JLabel();
    JLabel midName = new JLabel("Middle Initial(optional): ");
    JLabel mname = new JLabel();
    JLabel lastName = new JLabel("Last Name: ");
    JLabel lname = new JLabel();
    JLabel studentID = new JLabel("Student ID: ");
    JLabel id = new JLabel();
    JLabel buEmail = new JLabel("Email: ");
    JLabel email = new JLabel();
    ButtonGroup G2 = new ButtonGroup();
    JRadioButton grad = new JRadioButton("Graduate");
    JRadioButton under = new JRadioButton("Undergraduate");
    JButton ret = new JButton("OK");
    Course course;
    Grading_System grading_system;

    public showStudent_UI(Grading_System grading_system, Course course,int index){
        this.grading_system = grading_system;
        this.course = course;

        Container contentPane = this.getContentPane();
        contentPane.setLayout(null);
        infoP.setLayout(new GridLayout(5,2));
        infoP.add(firstName);
        infoP.add(fname);
        infoP.add(midName);
        infoP.add(mname);
        infoP.add(lastName);
        infoP.add(lname);
        infoP.add(studentID);
        infoP.add(id);
        infoP.add(buEmail);
        infoP.add(email);

        fname.setText(course.getStudents().get(index-1).getFirstName());
        mname.setText(course.getStudents().get(index-1).getMiddleInitial());
        lname.setText(course.getStudents().get(index-1).getLastName());
        id.setText(course.getStudents().get(index-1).getStudentId());
        email.setText(course.getStudents().get(index-1).getEmailAddress());

        radioPanel.setLayout(new GridLayout(1,2));
        radioPanel.add(grad);
        radioPanel.add(under);
        grad.enableInputMethods(false);
        under.enableInputMethods(false);

        G2.add(grad);
        G2.add(under);
        boolean g = course.getStudents().get(index-1) instanceof Graduate;
        boolean ug = course.getStudents().get(index-1) instanceof Undergraduate;
        if(g == true){
            grad.setSelected(true);
        }else if(ug == true){
            under.setSelected(true);
        }


        radioPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Student Type"));


        bntP.add(ret);
        ret.addActionListener(this);


        infoP.setBounds(100,50, 450,150 );
        radioPanel.setBounds(100,200,450,50);
        bntP.setBounds(115,275,400,50);
        contentPane.add(infoP);
        contentPane.add(radioPanel);
        contentPane.add(bntP);


        this.setTitle("Student Information");
        setSize(640, 360);
        setResizable(false);
        setVisible(true);


    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == ret) {
            dispose();
        }

    }
}

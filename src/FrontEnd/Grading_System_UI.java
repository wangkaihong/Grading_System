package FrontEnd;


import BackEnd.Grading_System;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Grading_System_UI extends JFrame implements ActionListener{
    JPanel loginButton = new JPanel();
    JPanel loginLabel = new JPanel();
    JPanel inputArea = new JPanel();
    JButton login = new JButton("Login");
    JLabel log = new JLabel("Login");

    JLabel userName = new JLabel("username：");
    JLabel passWord = new JLabel("password: ");
    JTextField inputUserName = new JTextField();
    JPasswordField inputPassWord = new JPasswordField();

    String[] courseList;
    Grading_System grading_system;


    public Grading_System_UI(Grading_System gradingSystem){
        grading_system = gradingSystem;
        Container contentPane = this.getContentPane();
        contentPane.setLayout(null);

        //login button
        loginButton.setLayout(new GridLayout(1, 1));
        loginButton.add(login);
        login.addActionListener(this);
        loginButton.setBounds(270,285,100,30);
        contentPane.add(loginButton);

        //login label
        loginLabel.setLayout(new GridLayout(1,1));
        log.setFont(new Font("Serif",Font.PLAIN,40));
        loginLabel.add(log);
        loginLabel.setBounds(30,40,100,40);
        contentPane.add(loginLabel);

        //username&password
        inputArea.setLayout(new GridLayout(2,2));
        inputArea.add(userName);
        inputArea.add(inputUserName);
        inputArea.add(passWord);
        inputArea.add(inputPassWord);
        inputArea.setBounds(170,120,300,100);
        contentPane.add(inputArea);

        courseList = gradingSystem.getCourseList();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(640, 360);
        setTitle("Welcome");
        setResizable(false);
        setVisible(true);
    }

    public Grading_System getGradingSystem(){
        return this.grading_system;
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == login){
            dispose();

            new Select_Course_UI(grading_system,courseList);
        }
    }
    public static void main(String[] args) {
        Grading_System_UI gradingSystemUI = new Grading_System_UI(new Grading_System());
    }
}
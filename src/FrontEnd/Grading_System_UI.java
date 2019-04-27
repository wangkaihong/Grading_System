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

    JSeparator jSeparator = new JSeparator();


    public Grading_System_UI(Grading_System gradingSystem){
        grading_system = gradingSystem;
        Container contentPane = this.getContentPane();
        contentPane.setLayout(null);

        login.setBackground(new java.awt.Color(255, 255, 255));
        login.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        login.setLabel("Log in");
        login.addActionListener(this);


        log.setFont(new java.awt.Font("Lucida Grande", 1, 36)); // NOI18N
        log.setText("Login");

        jSeparator.setBackground(new java.awt.Color(0, 0, 0));

        userName.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        userName.setText("Username: ");

        passWord.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        passWord.setText("Password: ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(userName)
                                        .addComponent(passWord))
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(inputUserName, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                                        .addComponent(inputPassWord))
                                .addGap(105, 105, 105))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(237, 237, 237)
                                .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(246, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(log, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(log, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(userName)
                                        .addComponent(inputUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(passWord)
                                        .addComponent(inputPassWord, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                                .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26))
        );
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

            new Select_Course_UI(grading_system);
        }
    }
    public static void main(String[] args) {
        Grading_System_UI gradingSystemUI = new Grading_System_UI(new Grading_System());
    }
}
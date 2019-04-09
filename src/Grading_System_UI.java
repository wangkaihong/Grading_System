/**
 * Created by wangkaihong on 2019/3/30.
 */
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Grading_System_UI extends JFrame implements ActionListener{
    JPanel p = new JPanel();
    JButton login = new JButton("Login");

    public Grading_System_UI(){
        Container contentPane = this.getContentPane();
        contentPane.setLayout(null);
        p.setLayout(new GridLayout(1, 1));
        p.add(login);
        login.addActionListener(this);
        p.setBounds(450,285,100,30);
        contentPane.add(p);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 600);
        setTitle("Welcome");
        setResizable(false);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == login){
            dispose();
            new Select_Course_UI();
        }
    }
    public static void main(String[] args) {
        new Grading_System_UI();
    }
}
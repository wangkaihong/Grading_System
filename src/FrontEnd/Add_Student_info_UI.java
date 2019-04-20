package FrontEnd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by wangkaihong on 2019/3/30.
 */
public class Add_Student_info_UI extends JFrame implements ActionListener {
    JPanel p = new JPanel();
    JLabel lCheck = new JLabel("Directory to file: ");
    JTextField file_path = new JTextField(20);

    JPanel buttons = new JPanel();
    JButton ok = new JButton("OK");
    JButton cancel = new JButton("Cancel");

    public Add_Student_info_UI(){
        Container contentPane = this.getContentPane();
        contentPane.setLayout(null);
        p.add(lCheck);
        p.add(file_path);

        ok.addActionListener(this);
        cancel.addActionListener(this);
        buttons.setLayout(new GridLayout(2,1));
        buttons.add(ok);
        buttons.add(cancel);
        buttons.setBounds(420,250,120,60);
        contentPane.add(buttons);

        p.setBounds(20,100,680,50);
        contentPane.add(p);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(640, 360);
        setTitle("Add Student information");
        setResizable(false);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == cancel){
            dispose();
            new Select_Course_UI();
        }
        if(e.getSource() == ok){
            dispose();
            new Select_Course_UI();
        }
    }

}

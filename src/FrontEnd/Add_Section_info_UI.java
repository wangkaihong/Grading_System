package FrontEnd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by wangkaihong on 2019/3/30.
 */
public class Add_Section_info_UI extends JFrame implements ActionListener {
    JPanel p = new JPanel();
    JButton export = new JButton("Export from course");
    JButton input = new JButton("Manually input");
    JButton confirm = new JButton("Confirm");
    JButton ret = new JButton("Return");

    public Add_Section_info_UI(){
        Container contentPane = this.getContentPane();
        contentPane.setLayout(null);
        p.add(export);
        export.addActionListener(this);
        p.add(input);
        input.addActionListener(this);
        p.add(confirm);
        confirm.addActionListener(this);
        p.add(ret);
        ret.addActionListener(this);
        p.setBounds(100,250,800,100);
        contentPane.add(p);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 600);
        setTitle("Add section information");
        setResizable(false);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == export) {
            dispose();
            new Copy_Section_info_UI();
        }
        if(e.getSource() == input) {
            dispose();
            new Input_Section_info_UI();
        }
        if(e.getSource() == confirm) {
            dispose();
            //add section to course info
            new Add_Class_UI();
        }
        if(e.getSource() == ret) {
            dispose();
            new Add_Class_UI();
        }
    }
}

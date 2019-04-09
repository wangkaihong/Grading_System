import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by wangkaihong on 2019/3/30.
 */
public class Copy_Section_info_UI extends JFrame implements ActionListener {
    JPanel p = new JPanel();
    JComboBox<String> course = new JComboBox<>();
    JButton confirm = new JButton("Confirm");
    JButton ret = new JButton("Return");

    public Copy_Section_info_UI(){
        Container contentPane = this.getContentPane();
        contentPane.setLayout(null);
        course.addItem("course1"); // todo: add course
        p.add(course);
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
        if(e.getSource() == ret) {
            dispose();
            new Add_Class_UI();
        }
        if(e.getSource() == confirm) {
            dispose();
            // add course info to course
            // get course.getSelectedIndex();
            new Add_Class_UI();
        }

    }

}

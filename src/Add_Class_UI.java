import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by wangkaihong on 2019/3/30.
 */
public class Add_Class_UI extends JFrame implements ActionListener {
    JPanel p = new JPanel();
    JButton student_info = new JButton("Add Student");
    JButton section_info = new JButton("Add Section");
    JButton course_info = new JButton("Add Course info");
    JButton confirm = new JButton("confirm");
    JButton ret = new JButton("Return");

    public Add_Class_UI(){
        Container contentPane = this.getContentPane();
        contentPane.setLayout(null);
        p.setLayout(new GridLayout(0, 1));
        p.add(student_info);
        student_info.addActionListener(this);
        p.add(section_info);
        section_info.addActionListener(this);
        p.add(course_info);
        course_info.addActionListener(this);
        p.add(confirm);
        confirm.addActionListener(this);
        p.add(ret);
        ret.addActionListener(this);
        p.setBounds(425,225,150,150);
        contentPane.add(p);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 600);
        setTitle("Add Course");
        setResizable(false);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == student_info){
            dispose();
            new Add_Student_info_UI();
        }
        if(e.getSource() == section_info){
            dispose();
            new Add_Section_info_UI();
        }
        if(e.getSource() == course_info){
            dispose();
            new Add_Course_info_UI();
        }
        if(e.getSource() == confirm){
            dispose();
            // add new course in backend
            new Select_Course_UI();
        }
        if(e.getSource() == ret){
            dispose();
            new Select_Course_UI();
        }
    }

}

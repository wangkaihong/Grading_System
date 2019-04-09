import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by wangkaihong on 2019/3/30.
 */
public class Select_Course_UI extends JFrame implements ActionListener{
    JPanel p = new JPanel();
    JButton add = new JButton("Add Class");
    JButton[] course;

    public Select_Course_UI(){
        course = new JButton[1];
        course[0] = new JButton("Course");
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new FlowLayout());
        p.setLayout(new GridLayout(0,1));
        for(int i = 0;i < course.length;i++) {
            p.add(course[i]);
            course[i].addActionListener(this);
        }
        p.add(add);
        add.addActionListener(this);

        contentPane.add(p);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 600);
        setTitle("Select Course");
        setResizable(false);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == add){
            dispose();
            new Add_Class_UI();
        }
        else {
            for(int i = 0; i< course.length;i++) {
                if (e.getSource() == course[i]) {
//                    dispose();
//                    System.out.print(i);
                    // go to course i;
                    dispose();
                    new GradeSheet();
                }
            }
        }
    }

}

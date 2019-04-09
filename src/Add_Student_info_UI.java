import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by wangkaihong on 2019/3/30.
 */
public class Add_Student_info_UI extends JFrame implements ActionListener {
    JPanel p = new JPanel();
    JLabel lCheck = new JLabel("Please specify directory of student info file");
    JTextField file_path = new JTextField(20);
    JButton ret = new JButton("Return");
    JButton confirm = new JButton("Confirm");

    public Add_Student_info_UI(){
        Container contentPane = this.getContentPane();
        contentPane.setLayout(null);
        p.add(lCheck);
        p.add(file_path);
        p.add(ret);
        ret.addActionListener(this);
        p.add(confirm);
        confirm.addActionListener(this);
        p.setBounds(100,250,800,100);
        contentPane.add(p);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 600);
        setTitle("Add Student information");
        setResizable(false);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == ret){
            dispose();
            new Add_Class_UI();
        }
        if(e.getSource() == confirm){
            dispose();
            // add student info
            new Add_Class_UI();
        }
    }

}

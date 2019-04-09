package FrontEnd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by wangkaihong on 2019/3/30.
 */
public class Input_Section_info_UI extends JFrame implements ActionListener {
    JPanel p = new JPanel();
    JButton add = new JButton("Add Section");
    JButton confirm = new JButton("confirm");
    JButton ret = new JButton("Return");
    ArrayList<JTextField> name_fields = new ArrayList<>();
    ArrayList<JTextField> weight_fields = new ArrayList<>();

    public Input_Section_info_UI(){
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new GridLayout(0,1));
        p.setLayout(new FlowLayout());
        p.add(add);
        add.addActionListener(this);
        p.add(confirm);
        confirm.addActionListener(this);
        p.add(ret);
        ret.addActionListener(this);
        contentPane.add(p);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 600);
        setTitle("Add Course");
        setResizable(false);
        setVisible(true);
    }

    public void add_section() {
        setVisible(false);
        JPanel p2 = new JPanel();

        JLabel name_label = new JLabel("Name of the section:");
        JTextField name = new JTextField(20);
        p2.add(name_label);
        name_fields.add(name);
        p2.add(name);

        JLabel weight_label = new JLabel("Weight of the section:");
        JTextField weight = new JTextField(20);
        weight_fields.add(weight);
        p2.add(weight_label);
        p2.add(weight);
        Container contentPane = this.getContentPane();
        contentPane.add(p2);

        setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == add) {
            add_section();
        }
        if(e.getSource() == ret) {
            dispose();
            new Add_Class_UI();
        }
        if(e.getSource() == confirm) {
            dispose();
            // add section to course
            new Add_Class_UI();
        }
    }

}

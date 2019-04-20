package FrontEnd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Add_Student_single_UI extends JFrame implements ActionListener {
    JPanel infoP = new JPanel();
    JPanel bntP = new JPanel();
    JPanel radioPanel = new JPanel();
    JLabel firstName = new JLabel("First Name: ");
    JTextField fname = new JTextField(20);
    JLabel midName = new JLabel("Middle Initial: ");
    JTextField mname = new JTextField(20);
    JLabel lastName = new JLabel("Last Name: ");
    JTextField lname = new JTextField(20);
    JLabel studentID = new JLabel("Student ID: ");
    JTextField id = new JTextField(20);
    JLabel buEmail = new JLabel("Email: ");
    JTextField email = new JTextField(20);
    ButtonGroup G2 = new ButtonGroup();
    JRadioButton grad = new JRadioButton("Graduate");
    JRadioButton under = new JRadioButton("Undergraduate");
    JButton confirm = new JButton("Confirm");
    JButton ret = new JButton("Cancel");

    public Add_Student_single_UI(){
        Container contentPane = this.getContentPane();
        contentPane.setLayout(null);
        infoP.setLayout(new GridLayout(5,2));
        infoP.add(firstName);
        infoP.add(fname);
        infoP.add(midName);
        infoP.add(mname);
        infoP.add(lastName);
        infoP.add(lname);
        infoP.add(studentID);
        infoP.add(id);
        infoP.add(buEmail);
        infoP.add(email);

        radioPanel.setLayout(new GridLayout(1,2));
        radioPanel.add(grad);
        radioPanel.add(under);
        G2.add(grad);
        G2.add(under);
        radioPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Student Type"));

        bntP.add(confirm);
        bntP.add(ret);

        infoP.setBounds(100,50, 450,150 );
        radioPanel.setBounds(100,200,450,50);
        bntP.setBounds(115,275,400,50);
        contentPane.add(infoP);
        contentPane.add(radioPanel);
        contentPane.add(bntP);


        this.setTitle("New Student");
        setSize(640, 360);
        setResizable(false);
        setVisible(true);

    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == ret) {
            dispose();

        }else if(e.getSource() == confirm){
            String studentFName = fname.getText();
            String studentMName = mname.getText();
            String studentLName = lname.getText();
            String studentID = id.getText();
            String studentEmail = email.getText();
            String studentType = this.G2.getSelection().getActionCommand();

            //??
        }

    }

}
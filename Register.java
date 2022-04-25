package networksystem;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import javax.swing.*;

@SuppressWarnings("serial")
public class Register extends JFrame implements ActionListener {
    JLabel titlelbl;
    JLabel usernamelbl;
    JLabel rolelbl;
    JLabel addresslbl;
    JLabel phonelbl;
    JLabel emaillbl;
    JLabel passwordlbl;
    JLabel cpasswordlbl;

    JTextField txtname;
    JComboBox roletxt;
    JTextField txtaddress;
    JTextField txtphone;
    JTextField txtemail;
    JPasswordField txtpassword;
    JPasswordField txtcpassword;

    JButton registerbtn;
    JButton loginbtn;
    JButton clearbtn;

    Register() {
        setTitle("Register");
        setLayout(null);

        titlelbl = new JLabel("Register");
        usernamelbl = new JLabel("Username:");
        rolelbl = new JLabel("Role:");
        addresslbl = new JLabel("Address:");
        phonelbl = new JLabel("Phone:");
        emaillbl = new JLabel("Email:");
        passwordlbl = new JLabel("Password:");
        cpasswordlbl = new JLabel("Confirm Password:");

        txtname = new JTextField(10);
        String role[] = { "Admin" };
        roletxt = new JComboBox(role);
        txtaddress = new JTextField(10);
        txtphone = new JTextField(10);
        txtemail = new JTextField(10);
        txtpassword = new JPasswordField(10);
        txtcpassword = new JPasswordField(10);

        registerbtn = new JButton("Register");
        loginbtn = new JButton("Back to Login");
        clearbtn = new JButton("Clear");

        registerbtn.addActionListener(this);
        loginbtn.addActionListener(this);
        clearbtn.addActionListener(this);

        titlelbl.setBounds(110, 15, 100, 30);
        titlelbl.setFont(new Font("Serif", Font.BOLD, 25));

        usernamelbl.setBounds(50, 50, 75, 25);

        emaillbl.setBounds(50, 100, 75, 25);
        passwordlbl.setBounds(50, 150, 75, 25);
        cpasswordlbl.setBounds(50, 200, 75, 25);

        txtname.setBounds(170, 50, 200, 25);

        txtemail.setBounds(170, 100, 200, 25);
        txtpassword.setBounds(170, 150, 200, 25);
        txtcpassword.setBounds(170, 200, 200, 25);

        registerbtn.setBounds(50, 400, 90, 25);
        registerbtn.setBackground(new Color(60, 200, 113));
        clearbtn.setBounds(150, 400, 90, 25);
        clearbtn.setBackground(new Color(230, 59, 100));
        loginbtn.setBounds(250, 400, 120, 25);
        loginbtn.setBackground(new Color(250, 49, 99));

        add(titlelbl);
        add(usernamelbl);
        add(txtname);

        add(emaillbl);
        add(txtemail);
        add(passwordlbl);
        add(txtpassword);

        add(cpasswordlbl);
        add(txtcpassword);

        add(registerbtn);
        add(loginbtn);
        add(clearbtn);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 620);
        setVisible(true);
    }

    public static void main(String[] args) {
        Register f = new Register();
    }

    public void reset() {
        txtname.setText("");
        txtaddress.setText("");
        txtphone.setText("");
        txtemail.setText("");
        txtpassword.setText("");
        txtcpassword.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        
        if (source == registerbtn) {
            String username = txtname.getText();
            String address = txtaddress.getText();
            String phone = txtphone.getText();
            String email = txtemail.getText();
            String password = txtpassword.getText();
            String cpassword = txtcpassword.getText();

            if (username.isEmpty() == false && email.isEmpty() == false && password.equals(cpassword)) {

                String userdata = username + "," + email + "," + password;
                try {
                    File f = new File("fileHandeling_java/registerdetails.txt");
                    if (!f.exists()) {
                        f.createNewFile();
                    }
                    FileWriter fw = new FileWriter(f, true);
                    fw.write(userdata);
                    fw.write("\n");
                    fw.flush();
                    fw.close();
                } catch (Exception a) {
                    a.printStackTrace();
                }

                JOptionPane.showMessageDialog(null, "Register Success");
                reset();
                dispose();
                new Login();
            } else {
                JOptionPane.showMessageDialog(null, "Register Failed");
            }

        } else if (source == clearbtn) {
            txtname.setText("");
            txtaddress.setText("");
            txtphone.setText("");
            txtemail.setText("");
            txtpassword.setText("");
            txtcpassword.setText("");
        } else if (source == loginbtn) {
            Login f = new Login();
            dispose();
        }

    }
}
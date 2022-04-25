package networksystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.*;

//import coursework1.AdminPanel;

@SuppressWarnings("serial")
public class Login extends JFrame implements ActionListener {
	
    JLabel titlelbl;
    JLabel usernamelbl;
    JLabel passwordlbl;

    JTextField txtname;
    JPasswordField txtpassword;

    JButton loginbtn;
    JButton clearbtn;
    JButton registerbtn;

    Login() {
        setTitle("Login Form");
        setLayout(null);

        titlelbl = new JLabel("Login-Form");
        usernamelbl = new JLabel("Username:");
        passwordlbl = new JLabel("Password:");

        txtname = new JTextField(10);
        txtpassword = new JPasswordField(10);

        loginbtn = new JButton("Login");
        clearbtn = new JButton("Clear");
        registerbtn = new JButton("Register");

        loginbtn.addActionListener(this);
        clearbtn.addActionListener(this);
        registerbtn.addActionListener(this);

        titlelbl.setBounds(120, 100, 120, 50);
        titlelbl.setFont(new Font("Serif", Font.BOLD, 20));

        usernamelbl.setBounds(50, 200, 75, 25);
        passwordlbl.setBounds(50, 250, 75, 25);

        txtname.setBounds(170, 200, 200, 25);
        txtpassword.setBounds(170, 250, 200, 25);

        clearbtn.setBounds(250, 300, 85, 25);
        clearbtn.setBackground(new Color(255, 0, 0));
        loginbtn.setBounds(150, 300, 80, 25);
        loginbtn.setBackground(new Color (106, 90, 205));
        registerbtn.setBounds(50, 300, 80, 25);
        registerbtn.setBackground(new Color(60, 179, 113));

        add(titlelbl);
        add(usernamelbl);
        add(txtname);

        add(passwordlbl);
        add(txtpassword);

        add(loginbtn);
        add(clearbtn);
        add(registerbtn);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 520);
        setVisible(true);
    }

    public static void main(String[] args) {
        Login f = new Login();
    }

    public void reset() {
        txtname.setText("");
        txtpassword.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == loginbtn) {
            String name = txtname.getText();
            String password = txtpassword.getText();

            try {
                File f = new File("fileHandeling_java/registerdetails.txt");
                // FileReader fr =new FileReader(f);
                BufferedReader br = new BufferedReader(new FileReader(f));
                String line;

                while ((line = br.readLine()) != null) {
                    String data[] = line.split(",");
                    if (name.equals(data[0]) && password.equals(data[2])) {
                        JOptionPane.showMessageDialog(null, "Login Successful");
                        AdminDashboard admin = new AdminDashboard();
                        dispose();
                        break;
                    } else {
                        JOptionPane.showMessageDialog(null, "Login Failed");
                    }
                }
            }
            catch (Exception ee) {
                ee.printStackTrace();
            }
        }
        else if (source == clearbtn) {
            txtname.setText("");
            txtpassword.setText("");
        } else if (source == registerbtn) {
            Register register = new Register();
            dispose();
        }
    }
}

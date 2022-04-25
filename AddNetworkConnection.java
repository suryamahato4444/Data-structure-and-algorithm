package networksystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class AddNetworkConnection extends JFrame implements MouseListener, ActionListener {
    JLabel titlelbl;
    JLabel dealerIdlbl;
    JLabel subdealerIdlbl;
    
    
    JTextField txtdealerId;
    JTextField txtsubdealerId;
    
    
    JButton addbtn;
    JButton backbtn;
    
    JTable tbl;
    DefaultTableModel model;

    
    AddNetworkConnection() {
    	
    	
        setTitle("Add Network Connection");
        setLayout(null);

        titlelbl=new JLabel("Add Dealer Network");
        dealerIdlbl = new JLabel("Dealer Id:");
        subdealerIdlbl=new JLabel("Sub Dealer Id:");
       
        txtdealerId = new JTextField(10);
        txtsubdealerId=new JTextField(10);

        
        addbtn = new JButton("Add");
        backbtn = new JButton("Back");
        
        addbtn.addActionListener(this);
        backbtn.addActionListener(this);
        
	     titlelbl.setBounds(110, 15, 200, 30);
	     titlelbl.setFont(new Font("Serif", Font.BOLD, 25));
	    
	     dealerIdlbl.setBounds(50, 50, 120, 25);
	     subdealerIdlbl.setBounds(50, 100, 120, 25);
	     
	     
	     txtdealerId.setBounds(170, 50, 200, 25);
	     txtsubdealerId.setBounds(170, 100, 200, 25);
	     
	     
	     addbtn.setBounds(50, 150, 100, 25);
	     addbtn.setBackground(new Color(124,185,232));
	     backbtn.setBounds(170, 150, 100, 25);
	     backbtn.setBackground(new Color(135,232,193));
	     
	     String [] cols= {"Dealer Id","Name","Location","Type"};
	     model=new DefaultTableModel(cols,0);
	     tbl=new JTable(model);
	     
	     JScrollPane sp=new JScrollPane(tbl);
	     sp.setBounds(400, 50, 600, 370);
	     add(sp);
	     tbl.addMouseListener(this);
	     
	     add(titlelbl);
	     add(dealerIdlbl);
	     add(txtdealerId);
	     add(subdealerIdlbl);
	     add(txtsubdealerId);
	     
	     add(addbtn);
	     add(backbtn);
	     
	     
	    
	     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     setSize(1050,500);
	     setVisible(true);
         
	     displayTable();
             
    }
    
    public void displayTable() {
        try {
        	File f = new File("fileHandeling_java/DealerDetails.txt");
        	Scanner reader = new Scanner(f);
            
            while(reader.hasNextLine()) {
            	String line = reader.nextLine();
            	String data[] = line.split(",");
                
                String id=data[0];
                String name=data[1];
                String location=data[2];
                String type=data[3];
                model.addRow(new Object[] {id,name,location,type});
            }
            reader.close();       
        }
        catch(Exception ee) {
            ee.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
    	AddNetworkConnection f = new AddNetworkConnection();
    }
 
    public void reset() {
    	txtdealerId.setText("");
    	txtsubdealerId.setText("");
        
        }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source=e.getSource();
        
        if(source==addbtn) {
        	String dealerId = txtdealerId.getText();
//        	int int1 = Integer.parseInt(dealerId);
        	String subdealerId = txtsubdealerId.getText();
//        	int int2 = Integer.parseInt(subdealerId);

            if (dealerId.isEmpty()==false && subdealerId.isEmpty() == false) {

                String userdata = dealerId + "," + subdealerId ;
                try {
                    File f = new File("fileHandeling_java/networkDetails.txt");
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
                
                JOptionPane.showMessageDialog(null, "Network Added Successfully");
                reset();
            } else {
                JOptionPane.showMessageDialog(null, "Addition Failed");
            }
        }
        else if(source==backbtn) {
            AdminDashboard f = new AdminDashboard();
            dispose();
        }

    }

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
package networksystem;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class AdminDashboard extends JFrame implements MouseListener, ActionListener{
	   
	 JButton addDealerDetailsbtn;
	 JButton addNetworkbtn;
	 JButton viewNetworkbtn;
	 JButton logoutbtn;
	 Connection conn;
	 
	 JTable tbl;
	 DefaultTableModel model;

     AdminDashboard() {
	   setTitle("Admin");
       setLayout(null);
       
       addDealerDetailsbtn=new JButton("Add Dealer Details");
       addNetworkbtn=new JButton("Add Network");
       viewNetworkbtn=new JButton("View Network");

       logoutbtn=new JButton("Logout");
       
       addDealerDetailsbtn.addActionListener(this);
       addNetworkbtn.addActionListener(this);
       viewNetworkbtn.addActionListener(this);

       logoutbtn.addActionListener(this);

       addDealerDetailsbtn.setBounds(60, 50, 170, 50);
       addDealerDetailsbtn.setBackground(new Color(135,232,193));
       addNetworkbtn.setBounds(60, 120,  170, 50);
       addNetworkbtn.setBackground(new Color(124,185,232));

       viewNetworkbtn.setBounds(60, 190,  170, 50);
       viewNetworkbtn.setBackground(new Color(60, 179, 113));
       logoutbtn.setBounds(60, 260,  170, 50);
       logoutbtn.setBackground(new Color(250,0,0));
       


       add(addDealerDetailsbtn);
       add(addNetworkbtn);
       add(viewNetworkbtn);

       add(logoutbtn);


       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setSize(400,400);
       setVisible(true); 
       

       
   	}
   
  
   
   public static void main(String[] args) {
	   AdminDashboard f = new AdminDashboard();
   }

   @Override
   public void actionPerformed(ActionEvent e) {
       Object source=e.getSource();
       
       if(source==addDealerDetailsbtn) {
    	   AddDealerDetails f = new AddDealerDetails();
           dispose();
       }else if (source == logoutbtn) {
    	   Login f = new Login();
           dispose();
       }else if (source == addNetworkbtn) {
    	   AddNetworkConnection f = new AddNetworkConnection();
           dispose();
       } else if (source == viewNetworkbtn) {
    	   ViewNetworks f = new ViewNetworks();
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

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
import java.util.List;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class ViewNetworks extends JFrame implements MouseListener, ActionListener {
	
    JLabel titlelbl;
    JLabel DealerIdlbl;
    
    
    JTextField txtDealerId;
    
    JTextArea txtareaDetails;
    JScrollPane scroll;
    JScrollPane sp;
    
    
    JButton showbtn;
    JButton showVertexbtn;
    JButton backbtn;
    
    JTable tbl;
    DefaultTableModel model;
    
    JTable tbl1;
    DefaultTableModel model1;
    
    
    
    ViewNetworks() {
    	
        setTitle("Show Network Details");
        setLayout(null);

        titlelbl=new JLabel("Show Tracked Details");
        DealerIdlbl = new JLabel("Covid Dealer Id:");
       
        txtDealerId = new JTextField(10);
        
        txtareaDetails = new JTextArea();
        txtareaDetails.setBounds( 30, 200 ,400 ,600 );
//        txtareaDetails.setEditable(false);
        txtareaDetails.setVisible(true);
//        txtareaDetails.setLineWrap(true);
        
        scroll = new JScrollPane (txtareaDetails);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  

          
        showbtn = new JButton("Show Dealer Details");
        showVertexbtn = new JButton("Show Dealer Tree");
        backbtn = new JButton("Back");
        
        showbtn.addActionListener(this);
        showVertexbtn.addActionListener(this);
        backbtn.addActionListener(this);

        
	     titlelbl.setBounds(110, 15, 300, 30);
	     titlelbl.setFont(new Font("Serif", Font.BOLD, 25));
	    
	     DealerIdlbl.setBounds(50, 50, 120, 25);
	     
	     
	     txtDealerId.setBounds(170, 50, 200, 25);
	     
	     
	     showbtn.setBounds(10, 100, 170, 25);
	     showbtn.setBackground(new Color(135,232,193));
	     backbtn.setBounds(185, 100, 100, 25);
	     backbtn.setBackground(new Color(124,185,232));
	     showVertexbtn.setBounds(290, 100, 150, 25);
	     showVertexbtn.setBackground(new Color(60, 179, 113));
	     
	     String [] cols= {"Dealer Id","Name","Location","Type"};
	     model=new DefaultTableModel(cols,0);
	     tbl=new JTable(model);
	     
	     sp=new JScrollPane(tbl);
	     sp.setBounds(450, 50, 600, 350);
	     add(sp);
	     tbl.addMouseListener(this);
	     
	     
	     String [] cols1= {"Dealer Id","Name","Location","Type"};
	     model1=new DefaultTableModel(cols1,0);
	     tbl1=new JTable(model1);
	     
	     JScrollPane sp1=new JScrollPane(tbl1);
	     sp1.setBounds(450, 400, 600, 350);
	     add(sp1);
	     tbl1.addMouseListener(this);
	     
	     
//	     Scroll bar
	     
	     
	     add(titlelbl);
	     add(DealerIdlbl);
	     add(txtDealerId);
	     
	     add(txtareaDetails);
	     getContentPane().add(scroll);
//	     add(scroll);
	     
	     add(showbtn);
	     add(showVertexbtn);
	     add(backbtn);
	    
	     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     setSize(1100,850);
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
    
    
    public void displayHighTable() {
        	BuildNetwork ct = new BuildNetwork(100);
        	try {
	        	File f = new File("fileHandeling_java/DealerDetails.txt");
	        	Scanner reader = new Scanner(f);
	            while(reader.hasNextLine()) {
	            	String line = reader.nextLine();
	            	String data[] = line.split(",");
	            	int id = Integer.parseInt(data[0]);
	            	File f1 = new File("fileHandeling_java/networkDetails.txt");
		        	Scanner reader1 = new Scanner(f1);
	            	while(reader1.hasNextLine()) {
	            		String line1 = reader1.nextLine();
		            	String data1[] = line1.split(",");
		            	int id1 = Integer.parseInt(data1[1]);
		            	int id2 = Integer.parseInt(data1[0]);
	            		if(id==id1) {
		            		if(data[4].equals("Null") &&!ct.findDate(id2).equals("Null")) {
		            			 String ID=data[0];
		                         String name=data[1];
		                         String location=data[2];
		                         String type=data[3];
		                         model1.addRow(new Object[] {ID,name,location,type});
		            		}
		            	}
	            		
	            	}
	            	reader1.close();
	            	
	            }
	            reader.close();  
	            
	        }
	        catch(Exception ee) {
	            ee.printStackTrace();
	        }
    }
    
    
    public void displayModerateTable() {
        	BuildNetwork ct = new BuildNetwork(100);
        	try {
	        	File f = new File("fileHandeling_java/DealerDetails.txt");
	        	Scanner reader = new Scanner(f);
	        	
	        	
	            while(reader.hasNextLine()) {
	            	String line = reader.nextLine();
	            	String data[] = line.split(",");
	            	int id = Integer.parseInt(data[0]);
	            	File f1 = new File("fileHandeling_java/networkDetails.txt");
		        	Scanner reader1 = new Scanner(f1);
	            	while(reader1.hasNextLine()) {
	            		String line1 = reader1.nextLine();
		            	String data1[] = line1.split(",");
		            	int id1 = Integer.parseInt(data1[1]);
		            	int id2 = Integer.parseInt(data1[0]);
	            		if(id==id1) {
		            		if(data[4].equals("Null") &&ct.findDate(id2).equals("Null")) {
		            			 String ID=data[0];
		                         String name=data[1];
		                         String location=data[2];
		                         String type=data[3];
		                         model1.addRow(new Object[] {ID,name,location,type});
		            		}
		            	}
	            	}
	            	reader1.close();
	            	
	            }
	            reader.close();  
	        }
	        catch(Exception ee) {
	            ee.printStackTrace();
	        }
    }
    
    public void remove_table() {
        for(int i=model1.getRowCount()-1;i>=0;i--) {
            model1.removeRow(i);
        }
       
   }
    
    public static void main(String[] args) {
    	ViewNetworks f = new ViewNetworks();
    }
 
    public void reset_txtField() {
    	txtDealerId.setText("");
        }
    
    public void reset_txtArea() {
    	txtareaDetails.setText("");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source=e.getSource();
        
         if(source==showbtn) {
        	reset_txtArea();
        	String final_detail = "";
        	String DealerId = txtDealerId.getText();
        	int data1 = Integer.parseInt(DealerId);
            if (DealerId.isEmpty()==false ) {
                try {
                	BuildNetwork ct = new BuildNetwork(100);
                	try {
                    	File f = new File("fileHandeling_java/networkDetails.txt");
                    	Scanner reader = new Scanner(f);
                        
                        while(reader.hasNextLine()) {
                        	String line = reader.nextLine();
                        	String data[] = line.split(",");
                        	int int1 = Integer.parseInt(data[0]);
                        	int int2 = Integer.parseInt(data[1]);
                            ct.addEdge(int1,int2);
                        }
                        reader.close();       
                    }
                    catch(Exception ee) {
                        ee.printStackTrace();
                    }
            		String textArea = ct.printEdge(data1);
            		final_detail= final_detail + textArea;
            		List<Integer> list1 = ct.get_CovidContactList(data1);
            		for(int i=0; i<list1.size();i++) {
            			String textArea21 = ct.printEdge(list1.get(i));
            			final_detail =final_detail + textArea21;
            		}
            		
            		txtareaDetails.setText(final_detail);
            		
                	
                } catch (Exception a) {
                    a.printStackTrace();
                }
                reset_txtField();
            } else {
                JOptionPane.showMessageDialog(null, "Showing Failed");
            }
        }
        else if(source == showVertexbtn) {
        	String DealerId = txtDealerId.getText();
        	int data1 = Integer.parseInt(DealerId);
//        	
            try {
            ViewNodes k = new ViewNodes(data1);
            } catch(Exception ee) {
            	JOptionPane.showMessageDialog(null, "Select a Dealer ID");
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
		int row=tbl.rowAtPoint(e.getPoint());
        System.out.println("row_id= "+row);
        
        System.out.println(model.getValueAt(row, 0));
        txtDealerId.setText(model.getValueAt(row, 0).toString());
		
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

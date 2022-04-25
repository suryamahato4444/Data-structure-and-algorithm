package networksystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class AddDealerDetails extends JFrame implements MouseListener, ActionListener {
    JLabel titlelbl;
    JLabel idlbl;
    JLabel namelbl;
    JLabel Locationlbl;
    JLabel Typelbl;
    
    
    JTextField txtid;
    JTextField txtname;
    JTextField txtLocation;
    JTextField txtType;
    
    
    
    JButton backbtn;
    JButton updatebtn;
    JButton clearbtn;
    JButton addbtn;
    
    // For Table
    JTable tbl;
    DefaultTableModel model;
    
    JTable tbl1;
    DefaultTableModel model1;
    
    // For Ending Table
    
    
    AddDealerDetails() {
    	
    	
        setTitle("Add Dealer");
        setLayout(null);

        titlelbl=new JLabel("Add Dealer");
        idlbl = new JLabel("Dealer Id:");
        namelbl=new JLabel("Name:");
        Locationlbl=new JLabel("Location:");
        Typelbl=new JLabel("Type:");
        
        txtid = new JTextField(10);
        txtname=new JTextField(10);
        txtLocation=new JTextField(10);
        txtType=new JTextField(10);
        
        DateFormat dateFormat = new SimpleDateFormat("dd MMM YYYY");
        
        backbtn=new JButton("Back");
        updatebtn=new JButton("Update");
        clearbtn=new JButton("Clear");
        addbtn = new JButton("Add");
        
        backbtn.addActionListener(this);
        updatebtn.addActionListener(this);
        clearbtn.addActionListener(this);
        addbtn.addActionListener(this);
        
	     titlelbl.setBounds(110, 15, 200, 30);
	     titlelbl.setFont(new Font("Serif", Font.BOLD, 25));
	    
	     idlbl.setBounds(50, 50, 75, 25);
	     namelbl.setBounds(50, 100, 75, 25);
	     Locationlbl.setBounds(50, 150, 75, 25);
	     Typelbl.setBounds(50, 200, 75, 25);
	     
	     
	     txtid.setBounds(170, 50, 200, 25);
	     txtname.setBounds(170, 100, 200, 25);
	     txtLocation.setBounds(170, 150, 200, 25);
	     txtType.setBounds(170, 200, 200, 25);
	     
	     
	     backbtn.setBounds(30, 400, 100, 25);
	     backbtn.setBackground(new Color(135,232,193));
	     clearbtn.setBounds(130, 400, 100, 25);
	     clearbtn.setBackground(new Color(124,185,232));
	     addbtn.setBounds(230, 400, 100, 25);
	     addbtn.setBackground(new Color(60, 179, 113));
	     updatebtn.setBounds(330, 400, 80, 25);
	     updatebtn.setBackground(new Color(135,232,193));
	     
	     // Table
	     String [] cols= {"Dealer_Id","Name","Location","Type"};
	     model=new DefaultTableModel(cols,0);
	     tbl=new JTable(model);
	     
	     JScrollPane sp=new JScrollPane(tbl);
	     sp.setBounds(420, 50, 600, 370);
	     add(sp);
	     tbl.addMouseListener(this);
	     
	  // Table

	     
	     
	     add(titlelbl);
	     add(idlbl);
	     add(txtid);
	     add(namelbl);
	     add(txtname);
	     
	     add(Locationlbl);
	     add(txtLocation);
	     add(Typelbl);
	     add(txtType);
	     
	     
	     add(backbtn);
	     add(updatebtn);
	     add(clearbtn);
	     add(addbtn);
	     
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
                String Location=data[2];
                String Type=data[3];
                model.addRow(new Object[] {id,name,Location,Type});
            }
            reader.close();       
        }
        catch(Exception ee) {
            ee.printStackTrace();
        }
        

        
        
    }
    
    public void remove_table() {
        for(int i=model.getRowCount()-1;i>=0;i--) {
            model.removeRow(i);
        }
       
   }
    
    public static void main(String[] args) throws IOException {
    	AddDealerDetails f = new AddDealerDetails();
    }
 
    public void reset() {
    	txtid.setText("");
        txtname.setText("");
        txtLocation.setText("");
        txtType.setText("");
        }
    
    public String get_String(String i) throws IOException { 
    	
    	File f = new File("");
    	BufferedReader br = new BufferedReader(new FileReader(f));
    	String j = "";
    	String line;
    	while ((line = br.readLine()) != null) {
            String data[] = line.split(",");
            if (i.equals(data[0])) {
            	j = line;
            }
        }
    	br.close();
    	return j;
    	
    }
    
    public static boolean isIdPresent(String Id) throws IOException{
    	boolean bool = false;
    	ArrayList<String> list = new ArrayList<String>();
    	
    	File f = new File("fileHandeling_java/DealerDetails.txt");
    	BufferedReader br = new BufferedReader(new FileReader(f));
    	String line;
    	while ((line = br.readLine()) != null) {
            String data[] = line.split(",");
            list.add(data[0]);
            
        }
    	
    	br.close();
    	
    	for(int i=0; i<list.size();i++) {
//    		System.out.print(list.get(i));
    		if (Id.equals(list.get(i))) {
//    			System.out.println("Id is present "+ list.get(i));
    			bool = true;
    		}
    	}
    	return bool;
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source=e.getSource();
        try {
			System.out.println(isIdPresent(txtid.getText()));
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
        if(source==updatebtn) {
        	String id = txtid.getText();
        	String name = txtname.getText();
            String Location = txtLocation.getText();
            String Type = txtType.getText();
            
            try {
				if(isIdPresent(id)) {
					if (id.isEmpty()==false && name.isEmpty() == false && Location.isEmpty() == false && Type.isEmpty() == false) {

				    	String userdata = id + "," + name + "," + Location + "," + Type;
				        try {
				        	File f = new File("fileHandeling_java/DealerDetails.txt");
				        	File tempFile = new File("fileHandeling_java/DealerDetails.txt");
				            BufferedReader br = new BufferedReader(new FileReader(f));
				        	BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
				            String line;
				            ArrayList<String> coll = new ArrayList<String>();
				            
				            Scanner scanner = new Scanner(f);
				            if(scanner.hasNextLine()) {
				            	while (scanner.hasNextLine()) {
				                line = scanner.nextLine();
				                if (line.equals(get_String(id))) {
				                	continue;
				                }
				                coll.add(line);
				            	}
				            } 
				            coll.add(userdata);
				            
				            scanner.close();
				            
				            for (String s : coll) {
				                writer.write(s);
				                writer.write("\n");
				            }

				            writer.close();
				            br.close();
				            f.delete();
				        	boolean successful = tempFile.renameTo(new File("fileHandeling_java/DealerDetails.txt"));
				        	
				        	if (successful) {
				        		System.out.println("Success");
				        	}else {
				        		System.out.println("Failed");
				        	}
				            
				            
				        } catch (Exception a) {
				            a.printStackTrace();
				        }

				        JOptionPane.showMessageDialog(null, "Updated Successfully");
				        remove_table();
				        displayTable();
				        reset();
				    } else {
				        JOptionPane.showMessageDialog(null, "Updation Failed");
				    }    
				} else {
					JOptionPane.showMessageDialog(null, "The Dealer Id has not been added. Add Dealer to Update Details");
				}
			} catch (HeadlessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            
                                      
        }else if(source==addbtn) {
        	String id = txtid.getText();
        	String name = txtname.getText();
            String Location = txtLocation.getText();
            String Type = txtType.getText();
            
            try {
				if(!isIdPresent(id)) {
					if (id.isEmpty()==false && name.isEmpty() == false && Location.isEmpty() == false && Type.isEmpty() == false) {

				        String userdata = id + "," + name + "," + Location + "," + Type;
				        try {
				            File f = new File("fileHandeling_java/DealerDetails.txt");
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
				        
				        JOptionPane.showMessageDialog(null, "Added Successfully");
				        remove_table();
				        displayTable();
				        reset();
				    } else {
				        JOptionPane.showMessageDialog(null, "Addition Failed");
				    }
				} else {
					JOptionPane.showMessageDialog(null, "The Dealer Id has already been added. Add different Dealer id to Add Details");
				}
			} catch (HeadlessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
        else if(source==clearbtn) {
            reset();
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
        txtid.setText(model.getValueAt(row, 0).toString());
        txtname.setText(model.getValueAt(row, 1).toString());
        txtLocation.setText(model.getValueAt(row, 2).toString());
        txtType.setText(model.getValueAt(row, 3).toString());
        
		
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
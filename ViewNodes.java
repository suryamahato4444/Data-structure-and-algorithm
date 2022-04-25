package networksystem;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;


@SuppressWarnings("serial")
public class ViewNodes extends JFrame 
{
    private JTree tree;
    static int vertex;
    DefaultMutableTreeNode root;
    
    ViewNodes(int ver){
    	
    	vertex = ver;
    	
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
//    	ct.printMatrix();
    	if(ct.is_inFile(vertex)) {
    		root = new DefaultMutableTreeNode(vertex);
			for(int j=0;j<ct.vertices;j++) {
				if(ct.graph[vertex][j]!=0) {
					System.out.println(j);
					DefaultMutableTreeNode h = new DefaultMutableTreeNode(j);
					root.add(h);
					for(int i=0;i<ct.vertices;i++) {
						if(ct.graph[j][i]!=0) {
							DefaultMutableTreeNode g = new DefaultMutableTreeNode(i);
							h.add(g);
						} 
					}
				}
			}
		}
    	

        tree = new JTree(root);
        add(tree);
        
        setTitle("JTree Example");       
        setSize(400,400);
        setVisible(true);
    }
     

	      
}

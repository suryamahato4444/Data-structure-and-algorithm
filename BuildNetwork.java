package networksystem;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.table.DefaultTableModel;

public class BuildNetwork {
	int vertices;
	int graph[][];

	BuildNetwork(int V) {
		this.vertices = V;
		graph = new int[vertices][vertices];
	}

	public void addEdge(int source, int destination) {
		graph[source][destination] = 1;
	}

	public void printMatrix() {
		for (int i = 0; i < vertices; i++) {
			for (int j = 0; j < vertices; j++) {
				System.out.print(graph[i][j] + " ");
			}
			System.out.println("");
		}
	}

	public String findDate(int vertex) {
		String covidDate = "";

		try {
			File f = new File("fileHandeling_java/DealerDetails.txt");
			Scanner reader = new Scanner(f);

			while (reader.hasNextLine()) {
				String line = reader.nextLine();
				String data[] = line.split(",");
				int id = Integer.parseInt(data[0]);
				if (id == vertex) {
					covidDate = data[4];
				}

			}
			reader.close();
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		return covidDate;

	}

	public List<Integer> get_CovidContactList(int vertex) {
		List<Integer> list = new ArrayList<Integer>();
		for (int j = 0; j < vertices; j++) {
			if (graph[vertex][j] != 0) {
				list.add(j);
			}
		}
		return list;
	}

	public boolean is_inFile(int vertex) {
		boolean condition = false;
		try {
			File f = new File("fileHandeling_java/networkDetails.txt");
			Scanner reader = new Scanner(f);

			while (reader.hasNextLine()) {
				String line = reader.nextLine();
				String data[] = line.split(",");
				int id = Integer.parseInt(data[0]);
				if (id == vertex) {
					condition = true;
				}
			}
			reader.close();
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		return condition;
	}

	public String printEdge(int vertex) {
		String DealerDetail1 = "";
		if (is_inFile(vertex)) {
			String str1 = "\n Dealer " + vertex + " is in connected With: ";
			DealerDetail1 = DealerDetail1 + str1;
			for (int j = 0; j < vertices; j++) {
				if (graph[vertex][j] != 0) {
					try {
						File f = new File("fileHandeling_java/DealerDetails.txt");
						Scanner reader = new Scanner(f);
						while (reader.hasNextLine()) {
							String line = reader.nextLine();
							String data[] = line.split(",");
							int id = Integer.parseInt(data[0]);
							if (id == j) {
								String s1 = " \n Dealerid " + j + " which has Dealer Details as::: \n        name:"
										+ data[1] + " ,\n        Location: " + data[2]  + " ,\n     Type: " + data[3]  ;

									DealerDetail1 = DealerDetail1 + s1;
							}
						}
						reader.close();
					} catch (Exception ee) {
						ee.printStackTrace();
					}
				}
			}
		}
		return DealerDetail1;
	}

	public static void main(String[] args) {

	}

}
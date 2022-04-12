package data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;

import inco.Table;
import inco.Node;

public class DataReader {
	private LinkedList<Table> t;
	private LinkedList<Node> C;
	private HashMap<Node,LinkedList<Node>> E;
	private HashMap<String, Integer> connectorYearBirth;
	private HashMap<String, Integer> connectorIncome;
	private HashMap<String, Integer> connectorKidhome;
	private HashMap<String, Integer> connectorTeenhome;
	private HashMap<String, Integer> connectorRecency;
	private HashMap<String, Integer> connectorMntWines;

	private HashMap<String, LinkedList<String>> dependencyTree;
	
	public DataReader(int Qi) {
		this.connectorYearBirth = new HashMap<String, Integer>();
		this.connectorIncome = new HashMap<String, Integer>();
		this.connectorKidhome = new HashMap<String, Integer>();
		this.connectorTeenhome = new HashMap<String, Integer>();
		this.connectorRecency = new HashMap<String, Integer>();
		this.connectorMntWines = new HashMap<String, Integer>();

		this.dependencyTree = new HashMap<String, LinkedList<String>>();
		this.C = new LinkedList<Node>();
		this.E = new HashMap<Node, LinkedList<Node>>();
		this.t = readTable(Qi);
	}
	
	public HashMap<String, LinkedList<String>> getDependencyTree(){
		return dependencyTree;
	}
	
	public void generateDependencyTree(int Qi) {
		FileReader fl;
		String[] paths= {};
		if(Qi == 6) {
			String[] temp = {"yearBirth", "income", "kidhome", "teenhome", "recency", "mntWines"};
			paths = temp;
		}

		for(String path : paths) {
			try {
				fl = new FileReader(new File("src/data/MyData/"+path+".csv"));
				Scanner s = new Scanner(fl);
				while(s.hasNext()) {
					LinkedList<String> tempValues = new LinkedList<String>();
					String[] line = s.nextLine().split(",");
					for(int i = 1; i < line.length; i++) {
						tempValues.add(line[i]);
					}
					dependencyTree.put(line[0], tempValues);
				}
				s.close();
			}
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void generateNodesAndEdges(int Qi) {
		if(Qi == 6) {
			int i = 0;
			int bs = getMaxLv(connectorYearBirth);
			int zs = getMaxLv(connectorKidhome);
			int ss = getMaxLv(connectorIncome);
			int fs = getMaxLv(connectorTeenhome);
			int ts = getMaxLv(connectorRecency);
			int ps = getMaxLv(connectorMntWines);

			for(int b = 0; b <= bs; b++)
				for(int z = 0; z <= zs;z++)
					for(int s = 0; s <= ss; s++)
						for(int f = 0; f <= fs; f++)
							for(int t = 0; t <= ts; t++)
								for(int p = 0; p <= ps; p++)
								{
									Node node = new Node(i, b, s, z, f, t, p);
									i++;
									C.add(node);
								}
			for(Node n : C) {
				for(Node n2 : C) {
					if(		(n2.getYearBirth() 	== n.getYearBirth()			&&
							n2.getIncome()		== n.getIncome()			&&
							n2.getKidhome() 	== n.getKidhome()			&&
							n2.getTeenhome()	== n.getTeenhome()			&&
							n2.getMntWines() 	== n.getMntWines()			&&
							n2.getRecency()		== n.getRecency()	+1	)	||

						   (n2.getYearBirth() 	== n.getYearBirth()			&&
							n2.getIncome()		== n.getIncome()			&&
							n2.getKidhome() 	== n.getKidhome()			&&
							n2.getTeenhome()	== n.getTeenhome()			&&
							n2.getMntWines() 	== n.getMntWines()		+1	&&
							n2.getRecency()		== n.getRecency())   		||

						   (n2.getYearBirth() 	== n.getYearBirth()			&&
							n2.getIncome()		== n.getIncome()			&&
							n2.getKidhome() 	== n.getKidhome()			&&
							n2.getTeenhome()	== n.getTeenhome()	+1		&&
							n2.getMntWines() 	== n.getMntWines()			&&
							n2.getRecency()		== n.getRecency()	)   	||

						   (n2.getYearBirth() 	== n.getYearBirth()			&&
							n2.getIncome()		== n.getIncome()			&&
							n2.getKidhome() 	== n.getKidhome()		+1	&&
							n2.getTeenhome()	== n.getTeenhome()			&&
							n2.getMntWines() 	== n.getMntWines()			&&
							n2.getRecency()		== n.getRecency()	)   	||

						   (n2.getYearBirth() 	== n.getYearBirth()			&&
							n2.getIncome()		== n.getIncome()	+1		&&
							n2.getKidhome() 	== n.getKidhome()			&&
							n2.getTeenhome()	== n.getTeenhome()			&&
							n2.getMntWines() 	== n.getMntWines()			&&
							n2.getRecency()		== n.getRecency()		)   ||

						   (n2.getYearBirth() 	== n.getYearBirth()	+1		&&
							n2.getIncome()		== n.getIncome()			&&
							n2.getKidhome() 	== n.getKidhome()			&&
							n2.getTeenhome()	== n.getTeenhome()			&&
							n2.getMntWines() 	== n.getMntWines()			&&
							n2.getRecency()		== n.getRecency()		)
							){

						if(E.containsKey(n)) {
							if(!E.get(n).contains(n2))
								E.get(n).add(n2);
						}
						else {
							E.put(n, new LinkedList<Node>());
							E.get(n).add(n2);
						}
						//Edge e = new Edge(n, n2);
						//E.add(e);
					}
				}
			}
		}
	}
	
	private int getMaxLv(HashMap<String, Integer> myMap) {
		int max = -1;
		Set<String> keys = myMap.keySet();
		for(String key : keys) {
			if(myMap.get(key).intValue()>max)
				max = myMap.get(key).intValue();
		}
		return max;
	}
	
	public HashMap<Node, LinkedList<Node>> getEdges(){
		return E;
	}
	public LinkedList<Node> getNodes(){
		return C;
	}
	
	public LinkedList<Table> getTable(){
		return t;
	}
	public void generateTable(int Qi){
		t = readTable(Qi);
	}
	public static LinkedList<Table> getOriginalT(int Qi){
		return readTable(Qi);
	}
	
	private static LinkedList<Table> readTable(int Qi){
		LinkedList<Table> t = new LinkedList<Table>();
		FileReader fl;
		try {
			fl = new FileReader(new File("src/data/MyData/Marketing_campaign.csv"));
			Scanner scan = new Scanner(fl);
			while(scan.hasNext()) {
				String[] line = scan.nextLine().split(",");
				Table temp = null;
				if(Qi == 6)
					temp = new Table(line[0],line[1],line[2],line[3],line[4],line[5]);
				t.add(temp);
			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return t;
	}
	
	public void generateConvGraph(int Qi) {
		FileReader fl;
		String[] paths= {};
		if(Qi == 6) {
			String[] temp = {"yearBirth", "income", "kidhome", "teenhome", "recency", "mntWines"};
			paths = temp;
		}
		for(String path : paths) {
			try {
				fl = new FileReader(new File("src/data/MyData/"+path+".csv"));
				Scanner s = new Scanner(fl);
				while(s.hasNext()) {
					String[] line = s.nextLine().split(",");
					int lv = 0;
					for(String temp : line) {
						switch(path) {
							case "yearBirth":
								this.connectorYearBirth.put(temp, lv);
								break;
							case "income":
								this.connectorIncome.put(temp, lv);
								break;
							case "kidhome":
								this.connectorKidhome.put(temp, lv);
								break;
							case "teenhome":
								this.connectorTeenhome.put(temp, lv);
								break;
							case "recency":
								this.connectorRecency.put(temp, lv);
								break;
							case "mntWines":
								this.connectorMntWines.put(temp, lv);
								break;
						}
						lv++;
					}
				}
				s.close();
			}
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
}

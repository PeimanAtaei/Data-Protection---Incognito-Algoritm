package inco;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import data.DataReader;

public class IncognitoAlgoritm {

	// input
	LinkedList<Table> T = new LinkedList<Table>();
    LinkedList<Table> lockedT = new LinkedList<Table>();
	int Qi; // Qi numbers
	LinkedList<Node> Ci; 
	HashMap<Node,LinkedList<Node>> Ei;
	DataReader data;
	int k;
	
	// output
	LinkedList<Table> kanonymousedT = new LinkedList<Table>();
	
	public IncognitoAlgoritm(LinkedList<Table> T, HashMap<Node,LinkedList<Node>> Ei, LinkedList<Node> Ci, int k, int Qi, DataReader data) {
		this.Qi = Qi;
		this.T  = T;
		this.lockedT = new LinkedList<Table>(T);
		this.Ei = Ei;
		this.Ci = Ci;
		this.k = k;
		this.data = data;
	}

	public void anonymize(int Qi) {
		LinkedList<Node> goodNodes = new LinkedList<Node>();
		int frequencySet = -1;
		Queue<Node> queue;	
		long start = System.currentTimeMillis();

			LinkedList<Node> roots = calculateRootsNodes(Ei.keySet());
			queue = roots;
			queue = orderQueue(queue);
			while(!queue.isEmpty()) {
				Node node = queue.poll();

					if(node.isRoot()) {
						frequencySet = computeFrequencySetOfT();
					}
					else {
						frequencySet = computeFrequencySetOfT(node, Qi);
					}

					if(frequencySet >= k) {
						queue = markNodes(node, queue);

						orderQueue(queue);
						node.setIsKAnonyminzated(frequencySet);
						if(!goodNodes.contains(node))
							goodNodes.add(node);
					}
					else {
						queue.addAll(directGeneralizationOfNode(node, queue));
						orderQueue(queue);
					}
				//}
			}
		//}
		long end = System.currentTimeMillis();
		
		printResults(start, end, this.k, this.Qi, T.size(), goodNodes);
	}
	
	private Queue<Node> markNodes(Node node, Queue<Node> queue) {
		
		if(Ei.containsKey(node)) {
			for(Node n : Ei.get(node)) {
				Ei.remove(n);
			}
		}
		LinkedList<Node> temp = new LinkedList<Node>(queue);
		for(int i = 0; i < temp.size(); i++)
			if(node.getHeight()<temp.get(i).getHeight())
				temp.remove(i);
		return temp;
	}

	private void printResults(long start, long end, int k, int Qi, int nRecords, LinkedList<Node> goodNodes) {

		String output = "";
		double duration = (end - start);
		output = "----------Incognito----------\n\n";
		output += "++Informations++ \n";
		output += "DB size: "+nRecords+" elements.\nK-anonymize request: "+k+"\n";
		output += "Number of Q-Identifier attributs: "+Qi+"\n\n";
		output += "++Results++\n";
		output += "Processing time: "+(int) duration+"mins\n";
		output += "Better Node/s:\n";
		LinkedList<Node> ordered = new LinkedList<Node>();

		while(!goodNodes.isEmpty()) {
			int min=goodNodes.get(0).getHeight();
			Node nodeMin = goodNodes.get(0);
			for(Node n : goodNodes) {
				if(min > n.getHeight()) {
					nodeMin = n;
					min = nodeMin.getHeight();
				}
			}
			goodNodes.remove(nodeMin);
			ordered.add(nodeMin);
		}
		for(Node n : ordered) {
			output +=n.toString(Qi);
		}
		System.out.println(output);
	}
	


	private LinkedList<Node> calculateRootsNodes(Set<Node> Ci){
		LinkedList<Node> roots = new LinkedList<Node>();
		for(Node n : Ci) {
			if(n.isRoot())
				roots.add(n);
		}
		return roots;
	}
	

	private int computeFrequencySetOfT() {
		
		LinkedList<Table> tempT = new LinkedList<Table>(T);
		HashMap<Table,Integer> counter = new HashMap<Table, Integer>();
		for(Table record : tempT) {
			if(counter.containsKey(record)) {
				counter.replace(record, counter.get(record), counter.get(record)+1);
			}
			else {
				counter.put(record, 1);
			}
		}
		
		LinkedList<Integer> values = new LinkedList<Integer>(counter.values());
		int min = values.get(0);
		for(Integer i : values) {
			if(min > i) {
				min = i;
			}
		}
		return min;
	}
	

	private int computeFrequencySetOfT(Node node, int Qi) {

		LinkedList<Table> tempT = anonymizeT(node, Qi);
		HashMap<Table,Integer> counter = new HashMap<Table, Integer>();
		for(Table record : tempT) {
			if(counter.containsKey(record)) {
				counter.replace(record, counter.get(record), counter.get(record)+1);
			}
			else {
				counter.put(record, 1);
			}
		}
		LinkedList<Integer> values = new LinkedList<Integer>(counter.values());
		int min = values.get(0);
		for(Integer i : values) {
			if(min > i) {
				min = i;
			}
		}
		
		return min;
	}
	
	private LinkedList<Table> anonymizeT(Node node, int Qi){
		LinkedList<Table> tempT = new LinkedList<Table>(DataReader.getOriginalT(Qi));
		HashMap<String, LinkedList<String>> dependencyTree = new HashMap<String, LinkedList<String>>(data.getDependencyTree());
		LinkedList<Table> outputTable = new LinkedList<Table>();
		for(Table record : tempT) {
			if(node.getYearBirth() > 0) {
				String yearBirth = dependencyTree.get(record.getYearBirth()).get(node.getYearBirth()-1);
				record.setYearBirth(yearBirth);
			}
			if(node.getIncome()>0) {
				String income = dependencyTree.get(record.getIncome()).get(node.getIncome()-1);
				record.setIncome(income);
			}
			if(node.getKidhome()>0) {
				String kidhome = dependencyTree.get(record.getKidhome()).get(node.getKidhome()-1);
				record.setKidhome(kidhome);
			}
			if(node.getTeenhome()>0) {
				String teenhome = dependencyTree.get(record.getTeenhome()).get(node.getTeenhome()-1);
				record.setTeenhome(teenhome);
			}
			if(node.getRecency()>0) {
				String recency = dependencyTree.get(record.getRecency()).get(node.getRecency()-1);
				record.setRecency(recency);
			}
			if(node.getMntWines()>0) {
				String mntwines = dependencyTree.get(record.getMntWines()).get(node.getMntWines()-1);
				record.setMntWines(mntwines);
			}
			outputTable.add(record);
		}
		return outputTable;
	}
	
	/**
	 * order a queue by height
	 */
	private Queue<Node> orderQueue(Queue<Node> queue) {
		Node[] temp = new Node[queue.size()];
		queue.toArray(temp);
		for(int i = 0; i < temp.length - 1; i++ ) {
			int max = temp[i].getHeight();
			int posmax = i;
			for(int j = i+1; j < temp.length; j++) {
				if(temp[i].getHeight()> max) {
					max = temp[i].getHeight();
					posmax = j;
				}
			}
			if(posmax != i) {
				Node tempNode = temp[i];
				temp[i] =  temp[posmax];
				temp[posmax] = tempNode;
			}
		}
		LinkedList<Node> nodesTemp = new LinkedList<Node>();
		for(Node n : temp) {
			nodesTemp.add(n);
		}
		
		Queue<Node> q = nodesTemp; 
		return q;
	}

	private LinkedList<Node> directGeneralizationOfNode(Node node, Queue<Node> queue){
		LinkedList<Node> list = new LinkedList<Node>();
		if(Ei.containsKey(node)) {
			for(Node n : Ei.get(node))
				if(!queue.contains(n))
					list.add(n);
		}
		return list;
	}	
}

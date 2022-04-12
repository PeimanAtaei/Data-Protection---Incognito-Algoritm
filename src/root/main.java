package root;

import data.DataReader;
import inco.IncognitoAlgoritm;
import inco.Node;
import inco.Table;

import java.util.HashMap;
import java.util.LinkedList;

public class main {

	public static void main(String[] args) {

		int Qi = 6;
		int k = 5;
		DataReader data = new DataReader(Qi);
		data.generateConvGraph(Qi);
		data.generateNodesAndEdges(Qi);
		data.generateDependencyTree(Qi);
		LinkedList<Table> table = data.getTable();
		HashMap<Node,LinkedList<Node>> Ei = data.getEdges();
		LinkedList<Node> Ci = data.getNodes();
		
		IncognitoAlgoritm inc = new IncognitoAlgoritm(table, Ei, Ci, k, Qi, data);
		inc.anonymize(Qi);
		
	}

}

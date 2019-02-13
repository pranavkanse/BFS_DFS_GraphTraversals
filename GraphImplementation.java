import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

class Node {
	String data;
	Node(String d){
		this.data = d;
	}
}

class Graph {
	HashMap<Node, LinkedList<Node>> graphRep = new HashMap<Node, LinkedList<Node>>();
}

public class GraphImplementation {

	public static void main(String[] args) {
		Boolean visitedAllNodes = false;
		Node ndA = new Node("A");
		Node ndB = new Node("B");
		Node ndC = new Node("C");
		Node ndD = new Node("D");
		Node ndE = new Node("E");
		Node ndF = new Node("F");
		Node ndG = new Node("G");
		Node ndH = new Node("H");
		Node ndI = new Node("I");
		Node ndJ = new Node("J");
		Node ndX = new Node("X");
		Node ndY = new Node("Y");
		
		LinkedList<Node> listA = new LinkedList<Node>(Arrays.asList(ndA,ndB,ndH,ndC));
		LinkedList<Node> listB = new LinkedList<Node>(Arrays.asList(ndB,ndA,ndE));
		LinkedList<Node> listC = new LinkedList<Node>(Arrays.asList(ndC,ndA,ndD,ndF));
		LinkedList<Node> listD = new LinkedList<Node>(Arrays.asList(ndD,ndC,ndF,ndJ,ndG,ndE));
		LinkedList<Node> listE = new LinkedList<Node>(Arrays.asList(ndE,ndB,ndD,ndG));
		LinkedList<Node> listF = new LinkedList<Node>(Arrays.asList(ndF,ndC,ndD,ndJ));
		LinkedList<Node> listG = new LinkedList<Node>(Arrays.asList(ndG,ndH,ndE,ndD,ndI));
		LinkedList<Node> listH = new LinkedList<Node>(Arrays.asList(ndH,ndA,ndG));
		LinkedList<Node> listI = new LinkedList<Node>(Arrays.asList(ndI,ndJ,ndG));
		LinkedList<Node> listJ = new LinkedList<Node>(Arrays.asList(ndJ,ndF,ndD,ndI));
		LinkedList<Node> listX = new LinkedList<Node>(Arrays.asList(ndX,ndY));
		LinkedList<Node> listY = new LinkedList<Node>(Arrays.asList(ndY,ndX));
		
		Graph graphObj = new Graph();
		
		graphObj.graphRep.put(ndA,listA);
		graphObj.graphRep.put(ndB,listB);
		graphObj.graphRep.put(ndC,listC);
		graphObj.graphRep.put(ndD,listD);
		graphObj.graphRep.put(ndE,listE);
		graphObj.graphRep.put(ndF,listF);
		graphObj.graphRep.put(ndG,listG);
		graphObj.graphRep.put(ndH,listH);
		graphObj.graphRep.put(ndI,listI);
		graphObj.graphRep.put(ndJ,listJ);
		graphObj.graphRep.put(ndX,listX);
		graphObj.graphRep.put(ndY,listY);
		//Graph is represented using Adjacency List.
		//The list consist of nodes each of which has some data.
		
		System.out.println("Graph representation using Linked Lists:");
		printBeforeTraversal(graphObj);
		
		//DFS Traversal
		HashSet<Node> visitedSet = new HashSet<Node>();
		Node firstNode = (Node) (graphObj.graphRep.keySet().toArray()[0]);
		System.out.println("\nDFS traversal. Starting from: "+firstNode.data);
		printDFSTraversal(graphObj,firstNode,visitedSet);
		
		//For disconnected graph extra handling has been done below
		while(!visitedAllNodes) {
			if(graphObj.graphRep.size()>visitedSet.size()) {
				Set<Node> graphSet = graphObj.graphRep.keySet();
				Boolean found = false;
				Node newNode = null;
				for(Node ndFound : graphSet) {
					if(!(visitedSet.contains(ndFound))) {
						found = true;
						newNode = ndFound;
					}
				}
				if ((found)&&(newNode!=null)) {
					printDFSTraversal(graphObj,newNode,visitedSet);
				}
			}
			else
				visitedAllNodes = true;
		}
		
		//BFS Traversal
		visitedAllNodes = false;
		HashSet<Node> visitedBFSSet = new HashSet<Node>();
		Queue<Node> BFSq = new LinkedList<Node>();
		Node firstBFSNode = (Node) (graphObj.graphRep.keySet().toArray()[0]);
		System.out.println("\n\nBFS traversal. Starting from: "+firstBFSNode.data);
		BFSq.add(firstBFSNode);
		visitedBFSSet.add(firstBFSNode);
		printBFSTraversal(graphObj,visitedBFSSet,BFSq);
		
		//For disconnected graph extra handling has been done below
		while(!visitedAllNodes) {
			if(graphObj.graphRep.size()>visitedBFSSet.size()) {
				Set<Node> graphSet = graphObj.graphRep.keySet();
				Boolean found = false;
				Node newNode = null;
				for(Node ndFound : graphSet) {
					if(!(visitedBFSSet.contains(ndFound))) {
						found = true;
						newNode = ndFound;
						BFSq.add(newNode);
						visitedBFSSet.add(newNode);
					}
				}
				if ((found)&&(newNode!=null)) {
					printBFSTraversal(graphObj,visitedBFSSet,BFSq);
				}
			}
			else
				visitedAllNodes = true;
		}
		
	}

	private static void printBeforeTraversal(Graph graphObj) {
		for(Map.Entry<Node,LinkedList<Node>> hmap : graphObj.graphRep.entrySet()) {
			String printList = "";
			for(Node nd: hmap.getValue()) {
				printList = printList + nd.data + "-->";
			}
			System.out.println(printList.substring(0,printList.length()-3));
		}
	}
	
	private static void printDFSTraversal(Graph graphObj,Node nd,HashSet<Node> visited) {
		if(graphObj.graphRep.containsKey(nd)) {
			for(Node liElement: graphObj.graphRep.get(nd)) {
				if(!(visited.contains(liElement))) {
					System.out.print(liElement.data+" ");
					visited.add(liElement);
					printDFSTraversal(graphObj,liElement,visited);
				}
			}
		}
	}
	
	private static void printBFSTraversal(Graph graphObj,HashSet<Node> visited,Queue<Node> BFSq) {
			Node qNode = (Node) BFSq.remove();
			System.out.print(qNode.data+" ");
			for(Node liElement: graphObj.graphRep.get(qNode)) {
				if(!(visited.contains(liElement))) {
					BFSq.add(liElement);
					visited.add(liElement);
				}
			}
			if (!(BFSq.isEmpty())) {
				printBFSTraversal(graphObj,visited,BFSq);
			}
	}

}

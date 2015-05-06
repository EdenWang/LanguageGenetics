package tree;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;

import treeEditDistance.TreeDefinitionOnID;


public class Tree extends TreeDefinitionOnID{

	private List<Node> allNodes;

	private Node startNode;


	private double fitness;

	private double simplicityScore;

	private final double widthFactor = 1;
	private final double depthFactor = 1;

	public Tree() {
		startNode = new Node(Tag.START, 1);
		simplicityScore = getSimplicityScore();
	}

	public static List<Node> returnAllNodes(Node node){
	    List<Node> listOfNodes = new ArrayList<Node>();
	    addAllNodes(node, listOfNodes);
	    return listOfNodes;
	}

	private static void addAllNodes(Node node, List<Node> listOfNodes) {
	    if (node != null) {
	        listOfNodes.add(node);
	        List<Node> children = node.getAllChildren();
	        if (children != null) {
	            for (Node child: children) {
	                addAllNodes(child, listOfNodes);
	            }
	        }
	    }
	}

	public List<Node> getAllNodes() {
		allNodes = returnAllNodes(startNode);
		return allNodes;
	}


	public void changeNode(Node oldNode, Node newNode) {
		Node parentNode = getParent(oldNode);
		parentNode.getAllChildren().remove(oldNode);
		parentNode.getAllChildren().add(newNode);
	}


	public void removeNode(Node node) {
		Node parentNode = getParent(node);
		parentNode.getAllChildren().remove(node);
	}

	public void changeProbability(Node node, int probability) {
		int difference = node.getProbability() - probability;
		node.setProbability(probability);

		Node parentNode = getParent(node);
		int amountOfOtherChildren = parentNode.getAllChildren().size() - 1;

		int remainder = difference % amountOfOtherChildren;
		int restProbability = (int) Math.floor(difference / amountOfOtherChildren);

		for(Node child: node.getAllChildren()) {
			if(child != node) {
				int currentProbability = child.getProbability();
				child.setProbability(currentProbability + restProbability);
			}
		}

		for(Node child: node.getAllChildren()) {
			if(child != node) {
				int currentProbability = child.getProbability();
				child.setProbability(currentProbability + remainder);
				break;
			}
		}


	}

	private Node getParent(Node node) {
		return startNode.getParent(node);
	}

/*	private void findAllNodes() {
		allNodes = startNode.getAllChildren();

	}*/

	public double getSimplicityScore() {
		calculateSimplicity();
		return simplicityScore;
	}



	private void calculateSimplicity() {
		simplicityScore = widthFactor * getWidth() + depthFactor - getDepth();
	}



	public double getDepth() {
		return startNode.getDepth();
	}



	public double getWidth() {
		return startNode.getWidth();
	}



	public Node getStartNode() {
		return startNode;
	}

	
	public void setStartNode(Node node) {
		startNode = node;
	}

	public void setFitness(double fitness){
		this.fitness = fitness;
	}

	public double getFitness(){

		return this.fitness;
	}

	//add by eden
	public Hashtable<Node, List<Node>> treeNodeStructure = new Hashtable<Node, List<Node>>();
	public Hashtable<Node, List<Node>> GetHashTable(){

		Hashtable<Node, List<Node>> HashMap_A = new Hashtable<Node, List<Node>>();
		for (Node parent: this.getAllNodes()){
			List<Node> Children = new ArrayList<Node>();
			for (Node child: parent.getAllChildren()){
				Children.add(child);
			}
			HashMap_A.put(parent, Children);

		}
		return HashMap_A;
	}
	
	public void TreeWithID(Tree tree1, Node start, int ordering){
		
		SetRoot(tree1.startNode);
		treeNodeStructure = tree1.GetHashTable();
		OrderNodes(ordering);
	}

//add by eden
	public Collection<Node> GetParentNodes(){
		return treeNodeStructure.keySet();
	}
} 

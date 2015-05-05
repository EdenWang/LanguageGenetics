package tree;


import java.util.List;


public class Tree{

	private List<Node> allNodes;
	
	private Node startNode;
	
	private Node root;
	
	private double fitness;
	
	private double simplicityScore;
	
	private final double widthFactor = 1;
	private final double depthFactor = 1;
	
	public Tree() {
		startNode = new Node(Tag.START, 1);
		simplicityScore = getSimplicityScore();
	}
	

	public List<Node> getAllNodes() {
		findAllNodes();
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

	private void findAllNodes() {
		allNodes = startNode.getAllChildren();
		
	}
	
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
    
	// add by Eden
	public Node getRoot(){
		
		return root;
	}
	
	// add by Eden
	public void setRoot(Node aRoot){
		
		root = aRoot;
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
	
}

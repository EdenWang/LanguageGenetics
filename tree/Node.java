package tree;

import java.util.ArrayList;
import java.util.List;

public class Node {
	
	private Tag tag;
	private int probability; 
	private int amount;
	
	private List<Node> children;
	
	
	public Node(Tag tag, int probability) {
		this.amount = 1;
		this.tag = tag;
		this.probability = probability;
	}

	public List<Node> getAllChildren() {
		List<Node> allChildren = new ArrayList<Node>();
		
		for(Node child: children) {
			allChildren.addAll(child.getAllChildren());
		}
		
		return allChildren;
	}
	// add by Eden
			public int getChildrenNum(){
				
				return this.getAllChildren().size();
			}
			
    // add by Eden
     public void addAllChildren(List<Node> children){
    	 for (Node child:children){
    		 this.addChild(child);
    	 }
     }

	public double getDepth() {
		double maxDepth = 0;
		for(Node child: children) {
			double childDepth = child.getDepth();
			if(childDepth > maxDepth) {
				maxDepth = childDepth;
			}
		}
		
		return 1 + maxDepth;
	}

	public double getWidth() {
		double maxWidth = 1;
		for(Node child: children) {
			double currentWidth = children.size();
			if(currentWidth > maxWidth) {
				maxWidth = currentWidth;
			}
			
			double childMaxWidth = child.getWidth();
			if(childMaxWidth > maxWidth) {
				maxWidth = childMaxWidth;
			}
		}
		
		return maxWidth;
	}

	public int getProbability() {
		return probability;
	}

	public void setProbability(int probability) {
		this.probability = probability;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public Node getParent(Node node) {
		if(children.contains(node)) {
			return this;
		}
		else {
			for(Node child: children) {
				return child.getParent(node);
			}
		}
		return null;
	}

	public void addAmount() {
		amount++;
	}
	
	public int getAmount() {
		return amount;
	}

	public void calculateProbabilities() {
		int totalAmount = 0;
		for(Node child: children) {
			totalAmount = totalAmount + child.getAmount();
		}
		for(Node child: children) {
			int probability = child.getAmount() / totalAmount;
			child.setProbability(probability);
		}
	}

	public void addChild(Node node) {
		children.add(node);
	}
	
	// add by Eden
	public boolean sameNode(Node aNode){
		if (this.getTag() == aNode.getTag() && this.getProbability() == aNode.getProbability()){
			return true;
		}
		else {
			return false;
		}
	}
	
	

}

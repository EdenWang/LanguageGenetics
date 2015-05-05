package treeEditDistance;

import tree.Node;

public class BasicDelete extends TreeEditOperation{
	
	public BasicDelete(){
		
		super.opName = "DELETE";
	}
	
	public double getCost(int aNodeID, int bNodeID, TreeWithID aTree, TreeWithID bTree){
		
		Node bNode = bTree.GetNode(bNodeID);
		double probability = bNode.getProbability();
		double cost = probability*1;
		return cost;
	}

}

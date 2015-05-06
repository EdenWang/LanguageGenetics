package treeEditDistance;

import tree.Node;

public class BasicDelete extends TreeEditOperation{
	
	public static int DeleteCost = 1;
	
	public BasicDelete(){
		
		super.opName = "DELETE";
	}
	
	public double getCost(int aNodeID, int bNodeID, TreeWithID aTree, TreeWithID bTree){
		
		Node bNode = bTree.GetNode(bNodeID);
		double probability = bNode.getProbability();
		double cost = probability*DeleteCost;
		return cost;
	}

}

package treeEditDistance;
import tree.Node;

public class BasicInsert extends TreeEditOperation{
	
	public BasicInsert(){
		
		super.opName = "INSERT";
	}
	
	public double getCost(int aNodeID, int bNodeID, TreeWithID aTree, TreeWithID bTree){
		
		Node aNode = aTree.GetNode(aNodeID);
		double probability = aNode.getProbability();
		double cost = probability*1;
		return cost;
	}
	}
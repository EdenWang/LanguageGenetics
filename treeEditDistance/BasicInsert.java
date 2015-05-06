package treeEditDistance;
import tree.Node;

public class BasicInsert extends TreeEditOperation{
	
	public static int InsertCost = 1;
	public BasicInsert(){
		
		super.opName = "INSERT";
	}
	
	public double getCost(int aNodeID, int bNodeID, TreeWithID aTree, TreeWithID bTree){
		
		Node aNode = aTree.GetNode(aNodeID);
		double probability = aNode.getProbability();
		double cost = probability*InsertCost;
		return cost;
	}
	}
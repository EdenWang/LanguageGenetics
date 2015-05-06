package treeEditDistance;
import tree.Node;
import tree.Tree;

public class BasicInsert extends TreeEditOperation{
	
	public static int InsertCost = 1;
	public BasicInsert(){
		
		super.opName = "INSERT";
	}
	
	public double getCost(int aNodeID, int bNodeID, Tree aTree, Tree bTree){
		
		Node aNode = aTree.GetNode(aNodeID);
		double probability = aNode.getProbability();
		double cost = probability*InsertCost;
		return cost;
	}
	}
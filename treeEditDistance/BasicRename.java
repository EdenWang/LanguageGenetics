package treeEditDistance;

import tree.Node;
import tree.Tag;

public class BasicRename extends TreeEditOperation{
	
	public static int RenameCost = 2; 
	 public BasicRename() {
			super.opName = "RELABEL";
		    }
	 
	 public double getCost(int aNodeID, int bNodeID, TreeWithID aTree, TreeWithID bTree){
		
		 Node aNode = aTree.GetNode(aNodeID);
		 Node bNode = bTree.GetNode(bNodeID);
		Tag aTag = aNode.getTag();
		Tag bTag = bNode.getTag();
		double aProbability = aNode.getProbability();
		double bProbability = bNode.getProbability();
		double cost = 0;
		
		if (aNode.sameNode(bNode)){
			
			 cost = 0;
		} 
		
		else {
			if (aProbability > bProbability){
			}
			else{
				double p = aProbability;
				aProbability = bProbability;
				bProbability = p;
			}
			
			if (aTag == bTag){
				
				
				 cost = (aProbability - bProbability)*RenameCost/4;
				
			}
			else {
				
				if (aTag.sameGroup(bTag)){
					
				   cost = (aProbability - bProbability)*RenameCost/2;
				}
				else {
					
					cost = (aProbability - bProbability)*RenameCost;
				}
			}
		}
		
		return cost;
	 }

}

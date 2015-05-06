package Evaluation;
 import tree.*;
 import treeEditDistance.*;

public class FindSimilarMember {
	
	private double distance = 0.0;
	public static double minimumDistance = 0.0; // minimum distance to be no similar
	
	public boolean isSimilar(Tree aTree, Tree bTree){
		
		distance = getDistance(aTree, bTree);
		
		if(distance < minimumDistance)
			return true;
		else 
			return false;
		
	}
	public double getDistance(Tree aTree, Tree bTree){
		
		TreeWithID aTreeWithID = new treeEditDistance.TreeWithID(aTree, aTree.getStartNode().getTag().toString(), 0);
		TreeWithID bTreeWithID = new treeEditDistance.TreeWithID(bTree, bTree.getStartNode().getTag().toString(), 0);
		OpsTreeTransform ops = new OpsTreeTransform();
		
		TreeEditDistanceCal TDC = new TreeEditDistanceCal();
		distance = TDC.TreeEditDistanceCalculation(aTreeWithID, bTreeWithID, ops);
		
		return distance;
	}

}

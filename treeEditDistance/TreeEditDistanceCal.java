package treeEditDistance;
import java.util.Hashtable;
import java.util.ArrayList;


public class TreeEditDistanceCal {
	
//	private Hashtable<String, Hashtable<String, Double>> ForestDistance = null;
	private double[][] Distance = null;
	
	public double TreeEditDistanceCalculation (TreeWithID aTree, TreeWithID bTree, OpsTreeTransform ops){
		
		Distance = new double[aTree.GetNodeNumber()+1][bTree.GetNodeNumber()+1];
		
		// pre-procedures
		// 1. find leftmost leaf and keyroots
		Hashtable<Integer, Integer> aLeftLeaf = new Hashtable<Integer, Integer>();
		Hashtable<Integer, Integer> bLeftLeaf = new Hashtable<Integer, Integer>();
		ArrayList<Integer> aKeyroots = new ArrayList<Integer>();
		ArrayList<Integer> bKeyroots = new ArrayList<Integer>();
		
		FindHelpTable(aTree, aLeftLeaf, aKeyroots, aTree.GetRootID());
		FindHelpTable(aTree, aLeftLeaf, aKeyroots, aTree.GetRootID());
		
		for (Integer aKeyroot: aKeyroots) {
		    for (Integer bKeyroot:bKeyroots) {
		    	
		    	Hashtable<Integer, Hashtable<Integer, Double>> FD = new Hashtable<Integer, Hashtable<Integer, Double>>();
		    	SetFD(aLeftLeaf.get(aKeyroot), bLeftLeaf.get(bKeyroot), 0.0d, FD);
		    	
		    	// for all descendant rooted at akeyroot: i 
		    	for (int i= aLeftLeaf.get(aKeyroot); i<= aKeyroot; i++){
		    		
		    		SetFD(i, bLeftLeaf.get(bKeyroot)-1, GetFD(i-1, bLeftLeaf.get(bKeyroot)-1, FD) + ops.getOp(OpsTreeTransform.DELETE).getCost(i, 0, aTree, bTree), FD);
		    	}
		    	

		    	// for all descendant rooted at bkeyroot: j 
                for (int j= bLeftLeaf.get(bKeyroot); j<= bKeyroot; j++){
		    		
		    		SetFD(aLeftLeaf.get(aKeyroot)-1, j, GetFD(aLeftLeaf.get(aKeyroot)-1, j-1, FD) + ops.getOp(OpsTreeTransform.INSERT).getCost(0, j, aTree, bTree), FD);
		    	}
                
                for (int i= aLeftLeaf.get(aKeyroot); i<= aKeyroot; i++){
                	 for (int j= bLeftLeaf.get(bKeyroot); j<= bKeyroot; j++){

                          double min = java.lang.Math.min(GetFD(i-1, j, FD) + ops.getOp(OpsTreeTransform.DELETE).getCost(i, 0, aTree, bTree), GetFD(i, j-1, FD) + ops.getOp(OpsTreeTransform.INSERT).getCost(0, j, aTree, bTree));
                		 
                          if ((aLeftLeaf.get(i) == aLeftLeaf.get(aKeyroot))
                  			    && 
                  			    (bLeftLeaf.get(j) == bLeftLeaf.get(bKeyroot))){
                        	  
                        	  Distance[i][j] = java.lang.Math.min(min, GetFD(i-1, j-1, FD) + ops.getOp(OpsTreeTransform.RENAME).getCost(i, j, aTree, bTree));
                        	  
                        	  SetFD(i, j, Distance[i][j], FD);
                          }
                          else{
                        	  
                        	  SetFD(i, j, java.lang.Math.min(min, GetFD(aLeftLeaf.get(i)-1, bLeftLeaf.get(j)-1,FD) + Distance[i][j]), FD);
                          }
                	 }
                }
		    }
		}
		    	
		// return result
		
		return Distance[aTree.GetNodeNumber()][bTree.GetNodeNumber()];
		
		}
	
	public void FindHelpTable(TreeWithID aTree, Hashtable<Integer, Integer> LeftMostLeaves, ArrayList<Integer> keyroots, int aNodeID){
		
		FindHelpTableRecurse(aTree, LeftMostLeaves, keyroots, aNodeID);
		
		// add root to keyroots
		keyroots.add(aNodeID);
		
		// add boundry leaf
		LeftMostLeaves.put(0,0);
	}
	
	public void FindHelpTableRecurse(TreeWithID aTree, Hashtable<Integer, Integer> LeftMostLeaves, ArrayList<Integer> keyroots, int aNodeID){
		
		if(aTree.isLeaf(aNodeID)){
			
			LeftMostLeaves.put(aNodeID, aNodeID);
			
		}
		else{
			
			boolean seenLeftMost = false;
			for(Integer child: aTree.GetChildrenIDs(aNodeID)){
				
				FindHelpTableRecurse(aTree, LeftMostLeaves, keyroots, aNodeID);
				
				if (!seenLeftMost){
					
				  LeftMostLeaves.put(aNodeID, LeftMostLeaves.get(child));
				  seenLeftMost = true;
				}
				else{
					
					keyroots.add(child);
				}
			}
			
		}
		
	}
			
		private double GetFD (int a, int b, Hashtable<Integer, Hashtable<Integer,Double>> ForestDistance) {
			
			Hashtable<Integer, Double> rows = null;
			if (!ForestDistance.containsKey(a)){
				
				ForestDistance.put(a, new Hashtable<Integer, Double>());
				
			}
			
			rows = ForestDistance.get(a);
			
			if (!rows.containsKey(b)){
				
				rows.put(b, 0.0);
			}
			
			return rows.get(b);
			
		}
		
		private void SetFD(int a, int b, double aValue, Hashtable<Integer, Hashtable<Integer,Double>> ForestDistance ){
			
			Hashtable<Integer, Double> rows = null;
            if (!ForestDistance.containsKey(a)){
				
				ForestDistance.put(a, new Hashtable<Integer, Double>());
				
			}
            
            rows = ForestDistance.get(a);
            rows.put(b, aValue);
		}
}

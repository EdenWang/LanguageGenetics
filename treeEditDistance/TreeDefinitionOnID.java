package treeEditDistance;
 
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Collection;
import tree.Node;


// turn tree into ordering nodes for the convinience of calculating tree edit distance
public abstract class TreeDefinitionOnID {
	
      public static final int POSTORDER = 0;
      public static final int PREORDER = 1;
      private int ChosenOrder = 0; // default postoder
      
      private String root = null;
      
      private Hashtable<Integer, Node> IDsToNode = new Hashtable<Integer, Node>();
      private Hashtable<Integer, String> IDsToLabel = new Hashtable<Integer, String>(); 
      private Hashtable<String, Integer> LabelToIDs = new Hashtable<String, Integer>(); 
      private Hashtable<Integer, ArrayList<Integer>> TreeStructureIDs = new Hashtable<Integer, ArrayList<Integer>>();
      
      //set root
      public void SetRoot (String _root){
    	  
    	  root = _root;
      }
      
      // return root
      public String GetRoot() {
    	  
    	  return root;
      }
      
      // return rootID
      public int GetRootID() {
    	  
    	  return LabelToIDs.get(root);
      }
      
      // put 0 to choose postorder, 1 to choose preorder, this order is used to number nodes
      public void SetOrder(int order) {
    	  
    	  ChosenOrder = order;
      }
      
      // return oder chosen now
      public int GetOrder() {
    	  
    	  return ChosenOrder;
      }
      
      // function of ordering the nodes in tree
      public void OrderNodes(int ordering) {
    	  
    	  if (ordering == 0) {
    		  
    		  SetPostOrdering(0, root);
    		  SetOrder(0);
    	  }
    	  else {
    		  
    		  SetOrder(1);
    	  }
    	  
    	  for (String Parent: GetNodes()) {
    		  
    		  ArrayList<Integer> IndexedChildren = new ArrayList<Integer>();
    		  
    		  for (String child: GetChildren(Parent)) {
    			  
    			  IndexedChildren.add(GetNodeID(child));
    			  
    		  }
    			 TreeStructureIDs.put(GetNodeID(Parent), IndexedChildren);
    			 
    			  
    	  }
      }
      
      public int SetPostOrdering (int Counter, String NodeLabel_A) {
    	  
    	  int InternalCounter = Counter;
    	  
    	  for (String child: GetChildren(NodeLabel_A)) {
    		  
    		  InternalCounter = SetPostOrdering (InternalCounter, child);
    		  
    	  }
    	  
    	  // put new ID to this node
    	   PutLabel(InternalCounter + 1, NodeLabel_A);
    	   PutNodeID(NodeLabel_A, InternalCounter + 1);
    	   
    	   return InternalCounter + 1;
      }
      
      // get node based on ID (not sure the node is got right)
      public Node GetNode(int NodeID){
    	  
    	  return IDsToNode.get(NodeID);
      }
      
      // input ID to get Label
      public String GetLabel (int NodeID) {
    	  
    	  return IDsToLabel.get(NodeID);
      }
      
      // input Label to get ID
      
      public int GetNodeID (String NodeLabel) {
    	  
    	  return LabelToIDs.get(NodeLabel);
      }
      
      // put label to certain ID
      public void PutLabel(int NodeID, String NodeLabel) {
    	  
    	  IDsToLabel.put(NodeID, NodeLabel);
      }
      
      // put certain ID to label
      
      public void PutNodeID (String NodeLabel, int NodeID) {
    	  
    	  LabelToIDs.put(NodeLabel, NodeID);
      }
      
      
      public abstract Collection<String> GetNodes();
      
      public abstract List<String> GetChildren(String NodeLabel_A);
      
      
      // return the children ID of this node
      public Collection<Integer> GetChildrenIDs(int NodeID) {
    	  
    	  return TreeStructureIDs.get(NodeID); 
      }
      
      // whether leaf node?
      public boolean isLeaf(int NodeID) {
    		return (GetChildrenIDs(NodeID).size() == 0);
    	    }
      
      // return the total node numbers of the tree
      public int GetNodeNumber() {
    	  
    	  return TreeStructureIDs.keySet().size();
      }
      
      public abstract String toString();
}

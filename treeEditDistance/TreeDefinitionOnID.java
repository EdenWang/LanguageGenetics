package treeEditDistance;
 
import java.util.ArrayList;
import java.util.Hashtable;
//import java.util.List;
import java.util.Collection;
import tree.Node;


// turn tree into ordering nodes for the convinience of calculating tree edit distance
public abstract class TreeDefinitionOnID {
	
      public static final int POSTORDER = 0;
      public static final int PREORDER = 1;
      private int ChosenOrder = 0; // default postoder
      
      private Node root = null;
      
      private Hashtable<Integer, Node> IDsToNode = new Hashtable<Integer, Node>();
    //  private Hashtable<Integer, String> IDsToLabel = new Hashtable<Integer, String>(); 
      private Hashtable<Node, Integer> NodeToIDs = new Hashtable<Node, Integer>(); 
      private Hashtable<Integer, ArrayList<Integer>> TreeStructureIDs = new Hashtable<Integer, ArrayList<Integer>>();
      
      //set root
      public void SetRoot (Node _root){
    	  
    	  root = _root;
      }
      
      // return root
      public Node GetRoot() {
    	  
    	  return root;
      }
      
      // return rootID
      public int GetRootID() {
    	  
    	  return NodeToIDs.get(root);
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
    	  
    	  for (Node Parent: GetParentNodes()) {
    		  
    		  ArrayList<Integer> IndexedChildren = new ArrayList<Integer>();
    		  
    		  for (Node child: Parent.getAllChildren()) {
    			  
    			  IndexedChildren.add(GetNodeID(child));
    			  
    		  }
    			 TreeStructureIDs.put(GetNodeID(Parent), IndexedChildren);
    			 
    			  
    	  }
      }
      
      public int SetPostOrdering (int Counter, Node aNode) {
    	  
    	  int InternalCounter = Counter;
    	  
    	  for (Node child: aNode.getAllChildren()) {
    		  
    		  InternalCounter = SetPostOrdering (InternalCounter, child);
    		  
    	  }
    	  
    	  // put new ID to this node
    	   PutNode(InternalCounter + 1, aNode);
    	   PutNodeID(aNode, InternalCounter + 1);
    	   
    	   return InternalCounter + 1;
      }
      
      // get node based on ID (not sure the node is got right)
      public Node GetNode(int NodeID){
    	  
    	  return IDsToNode.get(NodeID);
      }
      
      // input ID to get Label
      public Node GetLabel (int NodeID) {
    	  
    	  return IDsToNode.get(NodeID);
      }
      
      // input Label to get ID
      
      public int GetNodeID (Node aNode) {
    	  
    	  return NodeToIDs.get(aNode);
      }
      
      // put label to certain ID
      public void PutNode(int NodeID, Node aNode) {
    	  
    	  IDsToNode.put(NodeID, aNode);
      }
      
      // put certain ID to label
      
      public void PutNodeID (Node aNode, int NodeID) {
    	  
    	  NodeToIDs.put(aNode, NodeID);
      }
      
      
      public abstract Collection<Node> GetParentNodes();
      
   //   public abstract List<Node> GetChildren(String NodeLabel_A);
      
      
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
      
    //  public abstract String toString();
}

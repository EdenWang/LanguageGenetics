package Initialization;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import tree.Node;
import tree.Tag;
import tree.Tree;

public class InitialnizedPopualtion {
	
	private int maxDepth = 6; // to be determined
    //private int minDepth = 3; // to be determined
	
   public Tree GenerateRandomTree(int maxDepth){
	   
	   Tree aTree = new Tree();
	   Hashtable<Node, List<Node>> aHashTree = BuildRandomTree(maxDepth);
	   for (Node aNode: aHashTree.keySet()){
		   if(aNode.getTag() == Tag.START) {
			   aTree.setStartNode(aNode);
		   }
	   }
	   
	   return aTree;
	   
   }
   
   // hashtable should be examined
   public Hashtable<Node, List<Node>> BuildRandomTree(int maxDepth){
	   
	   Node root = new Node(Tag.START, 1); // initionlization of root
	   NodeFactory aNodeFactory = new NodeFactory();
	   Hashtable<Node, List<Node>> aNodes = new Hashtable<Node, List<Node>>();
	   if (maxDepth > 0){
		  root = aNodeFactory.GetNode();
	   
	     for (int i = 1; i < root.getChildrenNum()+1; i++){
	    	 
	    	 aNodes.putAll(BuildRandomTree(maxDepth - 1));
	     }
	     List<Node> rootChildren = new ArrayList<Node>();
	     rootChildren.add(root);	    		 
	     
	     aNodes.put(new Node(Tag.START, 1), rootChildren);
	     return aNodes;
       }
	   else{
		   
		 return null;
	   }
     }
}


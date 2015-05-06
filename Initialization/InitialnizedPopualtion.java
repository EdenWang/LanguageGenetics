package Initialization;

import java.util.ArrayList;
//import java.util.Hashtable;
import java.util.List;
import java.util.Random;

import tree.Node;
import tree.Tag;
import tree.Tree;

public class InitialnizedPopualtion {
	
	public static int maxDepth = 6; // to be determined
	public static int maxWidth = 20; // to be determined
    //private int minDepth = 3; // to be determined
	public static int initialTreeNum = 100;
	Random random = new Random();
   public Tree GenerateRandomTree(){
	   
	   Tree aTree = new Tree();
	   Node start = new Node(Tag.START,1);
	   for (int i = 1; i < random.nextInt(maxWidth)+1; i++){
		   
		   start.addChild(BuildRandomTree(maxDepth-1));
	   }
	   
	   aTree.setStartNode(start);
	   return aTree;
	   
   }
   
   // hashtable should be examined
   public Node BuildRandomTree(int maxDepth){
	   
//	   Node start = new Node(Tag.START, 1); // initionlization of root
	   NodeFactory aNodeFactory = new NodeFactory();
	   Node root = new Node(Tag.CC, 1); // initialization of root
	//   Hashtable<Node, List<Node>> aNodes = new Hashtable<Node, List<Node>>();
	   if (maxDepth > 0){
		  root = aNodeFactory.GetNode();
	      
	     for (int i = 1; i < random.nextInt(maxWidth)+1; i++){
	    	 
	    	 root.addChild(BuildRandomTree(maxDepth - 1));
	    	 
	     }    		 
	   }
	   else{
		   
		 root = aNodeFactory.GetNode();
	   }
	   
	   return root;
     }
   
   public List<Tree> getInitialPopulation(){ 

		List<Tree> parents = new ArrayList<Tree>();
		for (int i = 0; i< initialTreeNum; i++){

			InitialnizedPopualtion IP = new InitialnizedPopualtion();
			Tree  iTree = IP.GenerateRandomTree();
			parents.add(iTree);
		}
		return parents;
	}
   
   

}


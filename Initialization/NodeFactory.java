package Initialization;

import java.util.ArrayList;

import tree.Node;
import tree.Tag;

public class NodeFactory {

	  private ArrayList<Node> rNodeFactory;
	//  private Node rNode;
	  //private int rNodeID; 
      
	  public ArrayList<Node> GetNodeFactory(){
			
			for (Tag tag:tree.Tag.tags){
				
				int random = (int) Math.random()*100+1;
				Node aNode = new Node(tag, random);
				rNodeFactory.add(aNode);	
			}
			
			return rNodeFactory;
		}
	  
	  public Node GetNode(){
		  
		  for (Tag tag:tree.Tag.tags){
				
				int random = (int) Math.random()*100+1;
				Node aNode = new Node(tag, random);
				rNodeFactory.add(aNode);	
			}
		  int nodeID = (int) Math.random()*rNodeFactory.size() + 1; 
		  return rNodeFactory.get(nodeID);
	  }
	
}

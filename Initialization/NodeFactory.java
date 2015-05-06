package Initialization;

import java.util.ArrayList;

import tree.Node;
import tree.Tag;
import java.util.Random;
import java.util.List;
public class NodeFactory {

	  private List<Node> rNodeFactory = new ArrayList<Node>();
	//  private Node rNode;
	  //private int rNodeID; 
      Random random = new Random();
	  public List<Node> GetNodeFactory(){
			
		  int count = 0;
		  
			for (Tag tag:tree.Tag.tags){
				
				count++;
				System.out.print(count);
				int random1 = random.nextInt(100)+1;
				Node aNode = new Node(tag, random1);
				rNodeFactory.add(aNode);	
			}
			
			return rNodeFactory;
		}
	  
	  public Node GetNode(){
		  GetNodeFactory();
		  
		  for (Tag tag:tree.Tag.tags){
				
				int random1 = random.nextInt(100)+1;
				//System.out.print(random1);
				Node aNode = new Node(tag, random1);
				rNodeFactory.add(aNode);	
			}
		  int nodeID = random.nextInt(rNodeFactory.size()) + 1; 
		  return rNodeFactory.get(nodeID);
	  }
	
}

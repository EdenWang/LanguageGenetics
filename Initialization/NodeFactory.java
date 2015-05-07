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
			
		 // int count = 0;
		  
			for (Tag tag:tree.Tag.tags){
				if(tag == Tag.START) {
					continue;
				}
			//	count++;
			//	System.out.print(count);
				int random1 = random.nextInt(100)+1;
				Node aNode = new Node(tag, random1);
				rNodeFactory.add(aNode);
			}
			
		//	System.out.print(rNodeFactory.get(4).getTag().toString());
			return rNodeFactory;
		}
	  
	  public Node GetNode(){
		  
		  GetNodeFactory();
		  
		int nodeID = random.nextInt(rNodeFactory.size()) ;
		 // System.out.println(nodeID); 
		// System.out.println(rNodeFactory.size());
		  
		  return rNodeFactory.get(nodeID);
	  }
	
}

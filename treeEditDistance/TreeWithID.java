package treeEditDistance;


import java.util.Hashtable;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import tree.Node;
import tree.Tree;

public class TreeWithID extends TreeDefinitionOnID{
	
	public Hashtable<String, ArrayList<String>> treeStructure = new Hashtable<String, ArrayList<String>>();
	public Hashtable<Node, ArrayList<Node>> treeNodeStructure = new Hashtable<Node, ArrayList<Node>>();
	
	// get hashmap of node in Tree_A
	public Hashtable<Node, ArrayList<Node>> GetHashTable(Tree tree_A){
		Hashtable<Node, ArrayList<Node>> HashMap_A = new Hashtable<Node, ArrayList<Node>>();
		 for (Node parent: tree_A.getAllNodes()){
			 ArrayList<Node> Children = new ArrayList<Node>();
			 for (Node child: parent.getAllChildren()){
				 Children.add(child);
			 }
			 HashMap_A.put(parent, Children);
			 
		 }
		 return HashMap_A;
		 }
	
	// get hashmap of String in Tree_A
	public Hashtable<String, ArrayList<String>> GetHashTable_String(Tree tree_A){
		Hashtable<String, ArrayList<String>> HashMapString_A = new Hashtable<String, ArrayList<String>>();
		 for (Node parent: tree_A.getAllNodes()){
			 ArrayList<String> Children = new ArrayList<String>();
			 for (Node child: parent.getAllChildren()){
				 Children.add(child.getTag().toString());
			 }
			 HashMapString_A.put(parent.getTag().toString(), Children);
			 
		 }
		 return HashMapString_A;
		 }
	
	// note tree with ID in order (postorder[0] or preorder[1])
	public TreeWithID (Tree tree_B, String _root, int ordering){
		SetRoot(_root);
		treeStructure = GetHashTable_String(tree_B); 
		treeNodeStructure = GetHashTable(tree_B);
		OrderNodes(ordering);
	}
	
	 
	public Collection<String> GetNodes(){
		return treeStructure.keySet();
	}
    
	public List<String> GetChildren(String Nodelabel_A){
		
		return treeStructure.get(Nodelabel_A);
	}
     
	public String toString(){
		int RootID = GetRootID();
		StringBuffer rStr = new StringBuffer();

		for (int i=RootID;i>0;i--) {
		    rStr.append(GetLabel(i)+"("+i+") \n");
		    for (String child : GetChildren(GetLabel(i))) {
			rStr.append(" - "+child+"("+GetNodeID(child)+")  \n");
		    }
		}
		return rStr.toString();
	    }
		
	}


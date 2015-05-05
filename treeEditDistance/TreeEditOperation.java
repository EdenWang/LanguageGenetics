package treeEditDistance;


public abstract class TreeEditOperation {
	String opName = "";

    public String getName() {
	return opName;
    }

    public abstract double getCost(int aNodeID, int bNodeID, TreeWithID aTree, TreeWithID bTree);
}

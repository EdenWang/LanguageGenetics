package treeEditDistance;
import tree.Tree;

public abstract class TreeEditOperation {
	String opName = "";

    public String getName() {
	return opName;
    }

    public abstract double getCost(int aNodeID, int bNodeID, Tree aTree, Tree bTree);
}

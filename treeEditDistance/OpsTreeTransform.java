package treeEditDistance;

import java.util.ArrayList;
import java.util.List;

public class OpsTreeTransform {
	
	public static final int INSERT = 0;
    public static final int DELETE = 1;
    public static final int RENAME = 2;
   // public static final int IDENTITY = 3;

    private ArrayList<TreeEditOperation> operations 
	= new ArrayList<TreeEditOperation>();

    public OpsTreeTransform() {
	operations.add(INSERT, new BasicInsert());
	operations.add(DELETE, new BasicDelete());
	operations.add(RENAME, new BasicRename());
    }

    /** Returns the nth operation.  For convenience, the operation IDs are
     *  provided as named int constants:
     */
    public TreeEditOperation getOp(int opID) {
	return operations.get(opID);
    }

    public List<TreeEditOperation> getOperations() {
	return operations;

    }
}

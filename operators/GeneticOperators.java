package operators;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import tree.Node;
import tree.Tag;
import tree.Tree;

public class GeneticOperators {

	private final Random random = new Random();
    public static int repairThreshold = 5;

	public void crossover(Tree tree1, Tree tree2) {
		List<Node> nodes1 = tree1.getAllNodes();
		List<Node> nodes2 = tree2.getAllNodes();
		
		int low = 0;
		int high = nodes1.size() - 1;
		int index1 = random.nextInt(high - low) + low;
		
		low = 0;
		high = nodes2.size() - 1;
		int index2 = random.nextInt(high - low) + low;
		
		Node node1 = nodes1.get(index1);
		Node node2 = nodes2.get(index2);
		
		tree1.changeNode(node1, node2);
		tree2.changeNode(node2, node1);
	}

	public void mutate(Tree tree) {
		List<Node> nodes = tree.getAllNodes();
		
		int low = 0;
		int high = nodes.size() - 1;
		int index = random.nextInt(high - low) + low;
		
		Node node = nodes.get(index);
		
		low = 0;
		high = 1;
		int mutate = random.nextInt(high - low) + low;
		
		if(mutate == 0) {
			mutateProbability(tree, node);
		}
		else {
			mutateTag(node);
		}
		
	}

	private void mutateTag(Node node) {
		Tag oldTag = node.getTag();
		Tag newTag = Tag.randomTag(oldTag);
		
		node.setTag(newTag);
	}

	private void mutateProbability(Tree tree, Node node) {
		int low = 0;
		int high = 100;
		int probability = random.nextInt(high - low) + low;
		
		tree.changeProbability(node, probability);
		
	}

	public void repair(Tree tree) {
		List<Node> nodes = tree.getAllNodes();
		List<Node> toRemove = new ArrayList<Node>();
		
		for(Node node: nodes) {
			if(node.getProbability() < repairThreshold) {
				toRemove.add(node);
			}
		}
		
		for(Node node: toRemove) {
			tree.removeNode(node);
		}
	}

}

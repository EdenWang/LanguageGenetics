package Selection;

import java.util.ArrayList;
import java.util.List;

import tree.Tree;
import Evaluation.FindSimilarMember;

public class PairedParents {
	
	private Tree parent1;
	private Tree parent2;
	
	private double fitness;
	
	private double distanceAdjuster = 2;
	private double fitnessAdjuster = 1;
	
	public PairedParents(Tree tree1, Tree tree2) {
		parent1 = tree1;
		parent2 = tree2;
	}
	
	public void calculateFitness() {
		double distance = (new FindSimilarMember()).getDistance(parent1, parent2);
		double totalFitness = parent1.getFitness() + parent2.getFitness();
		
		fitness = distanceAdjuster * distance +  fitnessAdjuster * totalFitness;
	}

	public double getFitness() {
		return fitness;
	}

	public List<Tree> getParents() {
		List<Tree> parents = new ArrayList<Tree>();
		parents.add(parent1);
		parents.add(parent2);
		return parents;
	}

}

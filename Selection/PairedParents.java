package Selection;

import java.util.ArrayList;
import java.util.List;

import tree.Tree;
import Evaluation.FindSimilarMember;

public class PairedParents {
	
	private Tree parent1;
	private Tree parent2;
	
	private double fitness;
	
	public static double distanceAdjuster = 2;
	public static double fitnessAdjuster = 1;
	
	public PairedParents(Tree tree1, Tree tree2) {
		parent1 = tree1;
		parent2 = tree2;
	}
	 public Tree getParent(int parentID){
		 if (parentID == 1)
			 return parent1;
		 else if (parentID == 2)
			 return parent2;
		 else 
			 return null;
		 
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

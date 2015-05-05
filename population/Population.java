package population;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Evaluation.CalFitness;
import Evaluation.FindSimilarMember;
import tree.Tree;


public class Population {

	private List<Tree> population;
	private double fitnessThreshold = 0.5;

	private static Population instance = null;

	public static Population getInstance() {
		if(instance == null) {
			instance = new Population();
		}
		return instance;
	}

	private Population() {
		population = new ArrayList<Tree>();
	}

	public void removeMember(Tree member) {
		population.remove(member);
	}

	public void addMember(Tree member) {
		population.add(member);
	}	

	public List<Tree> getPopulation() {
		return population;
	}
	
	public void removeSimilarMembers() {
		List<Tree> toRemove = new ArrayList<Tree>();
		int i = 0;
		while(i < population.size()) {
			Tree tree1 = population.get(i);
			int j = i + 1;
			while(j < population.size()) {
				Tree tree2 = population.get(j);
				if((new FindSimilarMember()).isSimilar(tree1, tree2)) {
					toRemove.add(tree1);
				}
			}
		}
		
		for(Tree tree: toRemove) {
			removeMember(tree);
		}
	}

	public void removeLowFitnessMembers() throws ClassNotFoundException, IOException{
		
		List<Tree> toRemove = new ArrayList<Tree>();
		
		for (Tree pop:population){
			
			pop.setFitness((new CalFitness()).FitnessCal(pop));
		}
		
		double sum=0.0;
		for (Tree pop:population){
			sum = sum + pop.getFitness();
		}
		
		double averFit = sum/population.size();
		
		for (Tree pop:population){
			
			if (pop.getFitness() < averFit*fitnessThreshold)
				toRemove.add(pop);
		}
		for(Tree tree: toRemove) {
			removeMember(tree);
		}
		
		
	}
	
	public void filterPopulation() throws ClassNotFoundException, IOException {
		removeSimilarMembers();
		removeLowFitnessMembers();
	
	}
	
	public void addToPopulation(List<Tree> toAdd) {
		population.addAll(toAdd);
	}
}

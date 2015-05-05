package Selection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;
import java.util.Random;

import population.Population;
import tree.Tree;

public class SelectParent {

	//private int parentNum = 100; // initial parent number
	private List<PairedParents> possibleParents = new ArrayList<PairedParents>();
	
	private NavigableMap<Double, PairedParents> randomList;
	private Random random = new Random();
	private double sumOfAllFitness = 0;

	public void makePairedParents() throws ClassNotFoundException, IOException{
		Population.getInstance().filterPopulation();
		List<Tree> parents = Population.getInstance().getPopulation();
		int i = 0;
		while(i < parents.size()) {
			Tree tree1 = parents.get(i);
			int j = i + 1;
			while(j < parents.size()) {
				Tree tree2 = parents.get(j);
				PairedParents toAdd = new PairedParents(tree1, tree2);
				possibleParents.add(toAdd);
			}
		}
	}
	
	public PairedParents getRandomParents() {
		double value = random.nextDouble() * sumOfAllFitness;
		return randomList.ceilingEntry(value).getValue();

	}
	
	public void makeRandomList() {
		for(PairedParents parents: possibleParents) {
			parents.calculateFitness();
			double fitness = parents.getFitness();
			sumOfAllFitness = sumOfAllFitness + fitness;
			randomList.put(sumOfAllFitness, parents);
		}
	}

}

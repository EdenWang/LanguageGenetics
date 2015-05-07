package Selection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;

import population.Population;
import tree.Tree;
//import articles.Article;

public class SelectParent {

	//private int parentNum = 100; // initial parent number
	public List<PairedParents> possibleParents = new ArrayList<PairedParents>();
	
	private NavigableMap<Double, PairedParents> randomList;
	private Random random = new Random();
	private double sumOfAllFitness = 0;

	public void makePairedParents() throws ClassNotFoundException, IOException{
		Population.getInstance().filterPopulation();
		//System.out.println(1);
		List<Tree> parents = Population.getInstance().getPopulation();
		int i = 0;
		while(i < parents.size()) {
			System.out.println(1);
			Tree tree1 = parents.get(i);
			int j = i + 1;
			while(j < parents.size()) {
				Tree tree2 = parents.get(j);
				PairedParents toAdd = new PairedParents(tree1, tree2);
				possibleParents.add(toAdd);
				j++;
			}
			i++;
		}
	}
	
	public PairedParents getRandomParents() {
		double value = random.nextDouble() * sumOfAllFitness;
		return randomList.ceilingEntry(value).getValue();

	}
	
	public void makeRandomList() {
		randomList = new TreeMap<Double, PairedParents>();
		for(PairedParents parents: possibleParents) {
			parents.calculateFitness();
			double fitness = parents.getFitness();
			sumOfAllFitness = sumOfAllFitness + fitness;
			randomList.put(sumOfAllFitness, parents);
		}
	}

}

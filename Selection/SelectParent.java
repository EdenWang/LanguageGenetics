package Selection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import population.Population;
import tree.Tree;
import Initialization.InitialnizedPopualtion;

public class SelectParent {

	private int parentNum = 100; // initial parent number

	public ArrayList<Tree> GetParents(int parentNum){ 

		ArrayList<Tree> parents = new ArrayList<Tree>();
		for (int i = 0; i< parentNum; i++){

			InitialnizedPopualtion IP = new InitialnizedPopualtion();
			Tree  iTree = IP.GenerateRandomTree(7);
			parents.add(iTree);
		}
		return parents;
	}

	


	public List<Tree> selectParent(int selectedParentNum) throws ClassNotFoundException, IOException{
		
		Population pop = Population.getInstance();
		List<Tree> populationTrees = pop.getPopulation();
		populationTrees.addAll(GetParents(parentNum));
		pop.removeSimilarMembers();
		pop.removeLowFitnessMembers();
		
		return populationTrees;
		
	}
}

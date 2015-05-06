package main;

import Evaluation.*;
import articles.*;
import Initialization.*;
import operators.*;
import population.*;
import Selection.*;
import tree.*;
import treeEditDistance.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Hashtable;

public class Main {

	//public static int iniParentNum = 10; // initial parents number

	public static void setParameters() {
		CalFitness.accuracyWeight = 5;  //for fitness calculation
		CalFitness.simplicityWeight = 1; // for fitness calculation
		CalFitness.ArticleTreeNum = 10; // for calculate fitness (compared with number of article tree numbers)
		FindSimilarMember.minimumDistance = 0.5; // for judge similar members
		InitialnizedPopualtion.maxDepth = 6; // for tree depth
		InitialnizedPopualtion.maxWidth = 20; // for tree width
		InitialnizedPopualtion.initialTreeNum = 100; // for initialization
		GeneticOperators.repairThreshold = 5; // for repair operation in genetic operators
		Population.fitnessThreshold = 0.5;  // for initialization
		PairedParents.distanceAdjuster = 2; // for find good paired parents
		PairedParents.fitnessAdjuster = 1; //  for find good paired parents
		BasicDelete.DeleteCost = 1;  // for tree edit distance calculation
		BasicInsert.InsertCost = 1; // for tree edit distance calculation
		BasicRename.RenameCost = 2; // for tree edit distance calculation
	}




	public static void main(String args[]) throws ClassNotFoundException, IOException { 
	//	setParameters();
//        ArrayList<Tree> randomTrees = (new InitialnizedPopualtion()).getInitialPopulation();
		//Node aNode = new Node(Tag.CC,1);
		//List<Node> aF = new NodeFactory().GetNodeFactory();
	Node node1 = (new InitialnizedPopualtion()).BuildRandomTree(5);

//		Population iniPop = new Population();
//		iniPop.addToPopulation(randomTrees);
//		// iniPop.getInstance();
	//	(new SelectParent()).makePairedParents();
		//(new SelectParent()).makeRandomList();
		//PairedParents iniPairParents = (new SelectParent()).getRandomParents();
		
		//for (int i = 0; i < iniParentNum; i++){
			//(new GeneticOperators()).crossover(iniPairParents.getParent(1), iniPairParents.getParent(2));
		//	(new GeneticOperators()).mutate();
		
		}





	}





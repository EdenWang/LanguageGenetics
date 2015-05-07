package main;

import Evaluation.*;
//import articles.*;
import Initialization.*;
import operators.*;
import population.*;
import Selection.*;
import tree.*;
import treeEditDistance.*;

//import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
//import java.util.Hashtable;

import objectCloner.*;

import java.util.Random;

//import com.sun.javafx.css.parser.StopConverter;

public class Main {

	public static int iniParentNum = 5; // initial parents number
	public static double MutationRate = 0.05;// mutation rate
	public static double maxFitness = 100000; // while reach maxFitness, stop
	public static int generation = 0; // generation
	public static int MaxGeneration = 10; // max generation 
	private static Random random = new Random();
	public static Population iniPop = Population.getInstance();
	public static Tree finalTree = new Tree();
	public static void setParameters() {
		CalFitness.accuracyWeight = 5;  //for fitness calculation
		CalFitness.simplicityWeight = 1; // for fitness calculation
		CalFitness.ArticleTreeNum = 20; // for calculate fitness (compared with number of article tree numbers)
		FindSimilarMember.minimumDistance = 100; // for judge similar members
		InitialnizedPopualtion.maxDepth = 6; // for tree depth
		InitialnizedPopualtion.maxWidth = 6; // for tree width
		InitialnizedPopualtion.initialTreeNum = 30; // for initialization
		GeneticOperators.repairThreshold = 5; // for repair operation in genetic operators
		Population.fitnessThreshold = 0.5;  // for initialization
		PairedParents.distanceAdjuster = 2; // for find good paired parents
		PairedParents.fitnessAdjuster = 1; //  for find good paired parents
		BasicDelete.DeleteCost = 1;  // for tree edit distance calculation
		BasicInsert.InsertCost = 1; // for tree edit distance calculation
		BasicRename.RenameCost = 2; // for tree edit distance calculation
	}




	public static void main(String args[]) throws Exception { 
		setParameters();
	
		//        ArrayList<Tree> randomTrees = (new InitialnizedPopualtion()).getInitialPopulation();
		//Node aNode = new Node(Tag.CC,1);
		//List<Node> aF = new NodeFactory().GetNodeFactory();
		/*	NodeFactory bF = new NodeFactory();
	Node aNode = bF.GetNode();
	System.out.println(aNode.getTag().toString());
	System.out.println(aNode.getProbability());
	Node node1 = (new InitialnizedPopualtion()).BuildRandomTree(InitialnizedPopualtion.maxDepth);
	 System.out.println( node1.children.size());
	 System.out.println(node1.getChildrenNum());
	System.out.println(node1.getTag().toString());
	aNode.addChild(node1);
	Tree tree1 = (new InitialnizedPopualtion()).GenerateRandomTree();
	  System.out.println( tree1.getAllNodes().size());
	  tree1.TreeWithID(tree1, tree1.getStartNode(), 0);
	  System.out.println(tree1.GetNodeNumber());
	  System.out.println(tree1.GetRootID());
	System.out.println(tree1.getDepth());
	System.out.println(tree1.getWidth());
    List<Tree> trees = (new InitialnizedPopualtion()).getInitialPopulation();
    System.out.println(trees.size());
    System.out.println(trees.get(5).getDepth());
	System.out.println(trees.get(3).getWidth());
	Population iniPop = new Population();
	iniPop.addToPopulation(trees);
	 System.out.println(iniPop.getPopulation().size());
	System.out.println( tree1.getStartNode().getAllChildren().size());
	 tree1.TreeWithID(tree1, tree1.getStartNode(), 0);
	 System.out.println(tree1.GetRoot().getChildrenNum());
	 OpsTreeTransform Ops = new OpsTreeTransform();
	System.out.println( tree1.GetNode(1).getProbability());
	double distance = (new TreeEditDistanceCal()).TreeEditDistanceCalculation(trees.get(1), trees.get(3), Ops);
	// System.out.println(tree1.getStartNode().getTag().toString());
	 double fitness = (new CalFitness()).FitnessCal(tree1);
	 System.out.println(distance);
	 System.out.println(fitness);*/
		// (new ArticleExtractor()).getArticles();
		// Article article = ArticleDB.getInstance().getRandomArticle();
		// System.out.print(article.getContent());
		// Tree ArticleTree = (new ArticleTreeGenerator()).generateTree(ArticleDB.getInstance().getRandomArticle());
		//	System.out.println(ArticleTree.getWidth());
		//		// iniPop.getInstance();
		//(new SelectParent()).makePairedParents();
		// System.out.println((new SelectParent()).possibleParents.size());
		//System.out.println((new SelectParent()).possibleParents.size());
		//	(new SelectParent()).makeRandomList();
		//	 System.out.println((new SelectParent()).possibleParents.size());	
		//PairedParents iniPairParents = (new SelectParent()).getRandomParents();

		//for (int i = 0; i < iniParentNum; i++){
		//(new GeneticOperators()).crossover(iniPairParents.getParent(1), iniPairParents.getParent(2));
		//	(new GeneticOperators()).mutate();
	//	Tree tree1 = (new InitialnizedPopualtion()).GenerateRandomTree();
	//	tree1.printTree();
		List<Tree> randomTrees = (new InitialnizedPopualtion()).getInitialPopulation();
	//	Population iniPop = new Population();
		iniPop.addToPopulation(randomTrees);
		while(!stopCondition() || generation < MaxGeneration){
			//(new SelectParent()).makePairedParents();
			SelectParent select = new SelectParent();
			select.makePairedParents();
			select.makeRandomList();
			//(new SelectParent()).makeRandomList();
			//PairedParents iniPairedParents = (new SelectParent()).getRandomParents();
			List<PairedParents> saveIniPairedParentsList = new ArrayList<PairedParents> ();
			List<PairedParents> iniPairedParentsList = new ArrayList<PairedParents> ();
			for (int i = 0; i < iniParentNum; i++){
				PairedParents iniPairedParents = select.getRandomParents();
				PairedParents savedIniPairedParents = (PairedParents) (new ObjectCloner()).deepCopy(iniPairedParents);
				saveIniPairedParentsList.add(savedIniPairedParents);
				//PairedParents iniPairedParents2 = iniPairedParents;
				//(new ObjectCloner()).deepCopy(iniPairedParents);
				(new GeneticOperators()).crossover(iniPairedParents.getParent(1), iniPairedParents.getParent(2));
				iniPairedParentsList.add(iniPairedParents);
			}

			for (int i = 0; i < (int) (2*iniParentNum*MutationRate); i++ ){

				(new GeneticOperators()).mutate(iniPairedParentsList.get(random.nextInt(iniPairedParentsList.size())).getParent(random.nextInt(1)+1));
			}

			for (int i = 0; i < iniParentNum; i++){

				(new GeneticOperators()).repair(iniPairedParentsList.get(i).getParent(1));
				(new GeneticOperators()).repair(iniPairedParentsList.get(i).getParent(2));
			}
			for (int i=0; i < saveIniPairedParentsList.size(); i++){
				iniPop.addToPopulation(saveIniPairedParentsList.get(i).getParents());	
			}
			generation++;
		}	
		finalTree.printTree();
		System.out.println(finalTree.getFitness());
	}
	
	public static boolean stopCondition(){
		// while reach maxFitness, stop 
		// int finalChildIndex = 0; // index of the final child or the fulfill requirement child
		 double fitness = 0; 
		for (int i=0; i < iniPop.getPopulation().size(); i++){
	//		System.out.println(1);
		fitness = iniPop.getPopulation().get(i).getFitness();
	//	finalChildIndex++;
		if (fitness >= maxFitness){
			finalTree = iniPop.getPopulation().get(i);
			return true;
			
		}
		}
			return false;
	}
}










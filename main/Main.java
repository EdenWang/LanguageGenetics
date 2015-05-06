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
		CalFitness.ArticleTreeNum = 3; // for calculate fitness (compared with number of article tree numbers)
		FindSimilarMember.minimumDistance = 0.5; // for judge similar members
		InitialnizedPopualtion.maxDepth = 2; // for tree depth
		InitialnizedPopualtion.maxWidth = 5; // for tree width
		InitialnizedPopualtion.initialTreeNum = 10; // for initialization
		GeneticOperators.repairThreshold = 5; // for repair operation in genetic operators
		Population.fitnessThreshold = 0.5;  // for initialization
		PairedParents.distanceAdjuster = 2; // for find good paired parents
		PairedParents.fitnessAdjuster = 1; //  for find good paired parents
		BasicDelete.DeleteCost = 1;  // for tree edit distance calculation
		BasicInsert.InsertCost = 1; // for tree edit distance calculation
		BasicRename.RenameCost = 2; // for tree edit distance calculation
	}




	public static void main(String args[]) throws ClassNotFoundException, IOException { 
		setParameters();
//        ArrayList<Tree> randomTrees = (new InitialnizedPopualtion()).getInitialPopulation();
		//Node aNode = new Node(Tag.CC,1);
	//List<Node> aF = new NodeFactory().GetNodeFactory();
	NodeFactory bF = new NodeFactory();
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
	// double fitness = (new CalFitness()).FitnessCal(tree1);
	 System.out.println(distance);
	// System.out.println(fitness);
	 Tree ArticleTree = (new ArticleTreeGenerator()).generateTree(ArticleDB.getInstance().getRandomArticle());
	 System.out.println(ArticleTree.getWidth());
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
		
		}





	}





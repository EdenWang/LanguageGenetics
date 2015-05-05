package Evaluation;

import treeEditDistance.*;
import operators.*;
import tree.*;

import java.io.IOException;
import java.util.ArrayList;

import articles.ArticleDB;

public class CalFitness {
	
	public double Fitness = 0.0;
	private Double[]  Distance = null;
	public double FitnessCal(Tree aTree) throws ClassNotFoundException, IOException{
		
	
		int ArticleTreeNum = 10; // to be modified
		
		TreeWithID aTreeWithID = new treeEditDistance.TreeWithID(aTree, aTree.getStartNode().getTag().toString(), 0);
		
		ArticleTreeGenerator ArticleTreeG = new ArticleTreeGenerator();
		
		ArrayList<TreeWithID> ArticleTrees = new ArrayList<TreeWithID>();
		
		for (int i = 0; i < ArticleTreeNum; i++){
		Tree ArticleTree = ArticleTreeG.generateTree(ArticleDB.getInstance().getRandomArticle());
		TreeWithID ArticleTreeWithID = new treeEditDistance.TreeWithID(aTree, aTree.getStartNode().getTag().toString(), 0);
		ArticleTrees.add(ArticleTreeWithID);
		}
		
		for (int i= 0; i < ArticleTreeNum; i++){
			
			OpsTreeTransform ops = new OpsTreeTransform();
			
			TreeEditDistanceCal TDC = new TreeEditDistanceCal();
			Distance[i] = TDC.TreeEditDistanceCalculation(aTreeWithID, ArticleTrees.get(i), ops);
			
		}
		
		Fitness = sumAll(Distance)/ArticleTreeNum;
		return Fitness;
	}
		
	public double sumAll(Double[] distance){
			
			double sum = 0.0;
			for(double dis:distance){
				
			  sum = sum + dis;
			}
			
			return sum;
		}
	
	

}

package operators;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import tree.Node;
import tree.Tag;
import tree.Tree;
import articles.Article;
import articles.SentenceParser;

public class ArticleTreeGenerator {

	public Tree generateTree(Article article) throws IOException {
		List<List<Tag>> allSentences = new ArrayList<List<Tag>>();
		
		SentenceParser parser = new SentenceParser(article);
		
		while(parser.hasNext()) {
			String sentence = parser.next();
			List<Tag> tags = getTags(sentence);
			allSentences.add(tags);
		}
		
		Tree tree = new Tree();
		for(List<Tag> tags: allSentences) {
			addTagsToTree(tree, tags);
		}
		
		return tree;
		
	}

	private void addTagsToTree(Tree tree, List<Tag> tags) {
		Node parent = tree.getStartNode();
		for(Tag tag: tags) {
			boolean addingNode = true;
			for(Node child: parent.getAllChildren()) {
				if(child.getTag() == tag) {
					child.addAmount();
					parent.calculateProbabilities();
					
					addingNode = false;
					parent = child;
					break;
				}
			}
			if(addingNode == true) {
				Node node = new Node(tag, 100);
				parent.addChild(node);
				parent.calculateProbabilities();
				
				parent = node;
			}
			
		}
	}

	private List<Tag> getTags(String sentence) {
		List<Tag> tags = new ArrayList<Tag>();
		String[] words = sentence.split("\\s+");
		
		for(String word: Arrays.asList(words)) {
			String[] partsOfWord = word.split("/");
			try {			
				String possibleTag = partsOfWord[1];

				Tag foundTag = Tag.valueOf(possibleTag);
				tags.add(foundTag);
			}
			catch(Exception e) {
				continue;
			}
		}
		
		return tags;
	}

}

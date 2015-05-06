package articles;

import java.io.File;
import java.io.IOException;

public class ArticleExtractor {

	private String pathWiki = "D:\\Courses\\Final project\\LanguageGenetics\\wikipedia_test.txt";
	private ArticleDB articleDB;
	
	public ArticleExtractor() throws ClassNotFoundException, IOException {
		articleDB = ArticleDB.getInstance();
	}

	public void getArticles() throws IOException {

		File file = new File(pathWiki);

		ArticleParser parser = new ArticleParser(file);

		while(parser.hasNext()) {
			Article article = parser.next();
			articleDB.addArticle(article.getWeight(), article);
		}

		parser.close();

	}

}



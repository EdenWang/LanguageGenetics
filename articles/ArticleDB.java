package articles;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;

public class ArticleDB {

	private NavigableMap<Double, Article> articles;
	private final Random random;
	private double total;

	private static ArticleDB instance = null;
	
	public static ArticleDB getInstance() throws ClassNotFoundException, IOException {
		if(instance == null) {
			instance = new ArticleDB();
		}
		return instance;
	}

	private ArticleDB() throws ClassNotFoundException, IOException {
		this.random = new Random();
		this.total = 0;

		loadArticles();
		saveArticles();
	}

	public void addArticle(double weight, Article article) {
		total += weight;
		articles.put(total, article);
	}

	public Article getRandomArticle() {
		double value = random.nextDouble() * total;
		return articles.ceilingEntry(value).getValue();

	}

	@SuppressWarnings("unchecked")
	private void loadArticles() throws IOException, ClassNotFoundException {
		FileInputStream in;
		try {
			in = new FileInputStream("articles.out");

			ObjectInputStream ois = new ObjectInputStream(in);
			articles = (NavigableMap<Double, Article>) (ois.readObject());

			ois.close();
		} 
		catch (FileNotFoundException e) {
			ArticleExtractor articleExtractor = new ArticleExtractor();
			articles = new TreeMap<Double, Article>();
			articleExtractor.getArticles(this);
		}

	}

	private void saveArticles() throws IOException {
		FileOutputStream out = new FileOutputStream("articles.out");
		ObjectOutputStream oos = new ObjectOutputStream(out);

		oos.writeObject(articles);

		oos.flush();
		oos.close();
	}
}

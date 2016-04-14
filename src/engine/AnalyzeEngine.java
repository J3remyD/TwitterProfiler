package engine;
import Utils.ConnexionUtilities;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;

public class AnalyzeEngine {
	private final Twitter twitter;
	
	public AnalyzeEngine() {
		TwitterFactory tf = new TwitterFactory(ConnexionUtilities.getConfiguration());
		twitter = tf.getInstance();
	}

	public void run() {
		//TODO
	}
}

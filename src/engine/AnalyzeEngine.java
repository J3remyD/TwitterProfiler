package engine;
import Utils.ConnexionUtilities;
import twitter4j.*;

import java.util.ArrayList;
import java.util.List;

public class AnalyzeEngine {
	private final Twitter twitter;
	private List<AnalyzeTrend> fetchedTrends = null;
	
	public AnalyzeEngine() {
		TwitterFactory tf = new TwitterFactory(ConnexionUtilities.getConfiguration());
		twitter = tf.getInstance();
	}

	public void run() {
		List<AnalyzeTrend>fetchedTrends = retrieveTrendsFromDb();
		if(fetchedTrends == null || fetchedTrends.isEmpty()) {
			try {
				fetchedTrends = retrieveTrendsFromTwitter();
			} catch (TwitterException e) {
				e.printStackTrace();
			}
		}
		computeTrends();
	}

	private List<AnalyzeTrend> retrieveTrendsFromDb() {
		//TODO
		return null;
	}

	private List<AnalyzeTrend> retrieveTrendsFromTwitter() throws TwitterException {
		ResponseList<Location> availableTrends = twitter.getAvailableTrends();
		List<AnalyzeTrend> extractedTrends = new ArrayList<>();
		for (Location location : availableTrends) {
			AnalyzeTrend trend = new AnalyzeTrend(location.getCountryName(), location.getName(), location.getWoeid());
			extractedTrends.add(trend);
		}
		return extractedTrends;
	}

	private void computeTrends() {
		//TODO
		fetchedTrends.stream().filter(t -> t.getId() != 1).forEach(trend -> {
			try {
				System.out.println("trendOF(" + trend.getCountryName() + " - " + trend.getCity() + "): " +
						"-> " + twitter.getPlaceTrends(trend.getId()));
			} catch (TwitterException e) {
				e.printStackTrace();
			}
		});
	}
}

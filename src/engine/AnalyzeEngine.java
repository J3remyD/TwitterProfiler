package engine;
import Utils.ConnexionUtilities;
import Utils.DBUtilities;
import Utils.QueryUtilities;

import twitter4j.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AnalyzeEngine {
	private Twitter twitter;
	private List<AnalyzeTrend> fetchedTrends = null;
	
	public AnalyzeEngine() {}

	public void run() {
		fetchedTrends = retrieveTrendsFromDb();
		if(fetchedTrends == null || fetchedTrends.isEmpty()) {
			TwitterFactory tf = new TwitterFactory(ConnexionUtilities.getConfiguration());
			twitter = tf.getInstance();
			try {
				fetchedTrends = retrieveTrendsFromTwitter();
				insertIntoDb(fetchedTrends);
			} catch (TwitterException e) {
				e.printStackTrace();
			}
		}
		computeTrends();
	}

	private List<AnalyzeTrend> retrieveTrendsFromDb() {
		List<AnalyzeTrend> trends = new ArrayList<>();
		final Connection connection = DBUtilities.getConnection();
		if(connection != null) {
			try {
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(DBUtilities.selectTrends());
				while (rs.next())
				{
					String id = rs.getString("id");
					String country = rs.getString("country");
					String city = rs.getString("city");
					trends.add(new AnalyzeTrend(id, country, city));
				}
				stmt.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return trends;
	}

	private void insertIntoDb(List<AnalyzeTrend> trends) {
		final Connection connection = DBUtilities.getConnection();
		if(connection != null) {
			trends.stream().forEach(t -> {
				try {
					Statement stmt = connection.createStatement();
					stmt.executeUpdate(DBUtilities.insertTrend(t));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			});
		}
	}

	private List<AnalyzeTrend> retrieveTrendsFromTwitter() throws TwitterException {
		ResponseList<Location> availableTrends = twitter.getAvailableTrends();
		List<AnalyzeTrend> extractedTrends = new ArrayList<>();
		for (Location location : availableTrends) {
			AnalyzeTrend trend = new AnalyzeTrend(QueryUtilities.TREND_ID_PREFIX + location.getWoeid(), location.getCountryName(), location.getName());
			extractedTrends.add(trend);
		}
		return extractedTrends;
	}

	private void computeTrends() {
		fetchedTrends.stream().filter(t -> Integer.parseInt(t.getId().substring(QueryUtilities.TREND_ID_PREFIX.length())) != 1)
		.forEach(trend -> {
			System.out.println("trendOF(" + trend.getCountryName() + " - " + trend.getCity() + "): " + "-> " + trend.getId());
		});
	}
}

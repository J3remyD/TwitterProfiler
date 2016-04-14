package Utils;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class ConnexionUtilities {
	public static Configuration getConfiguration() {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey("*********************")
		  .setOAuthConsumerSecret("******************************************")
		  .setOAuthAccessToken("**************************************************")
		  .setOAuthAccessTokenSecret("******************************************");
		return cb.build();
	}
}	
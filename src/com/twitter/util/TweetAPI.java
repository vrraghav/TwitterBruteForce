/**
 * This class is the implementation of required functionalities...
 * Used the Twitter4J API's open source Java libraries for Twitter API, easy to integrate Java application to Twitter Service.
 */

package com.twitter.util;

import java.io.IOException;
import java.util.List;

import com.tweet.constants.TweetConstants;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class TweetAPI {
	Twitter twitter = new TwitterFactory().getInstance();

	public void connection(){
		try{
			// Twitter 4j API -- handling the OAuthentication 
			twitter.setOAuthConsumer(TweetConstants.CONSUMER_KEY,TweetConstants.CONSUMER_SECRET);
			String accessToken = getSavedAccessToken();
			String accessTokenSecret = getSavedAccessTokenSecret();
			AccessToken oathAccessToken = new AccessToken(accessToken,
					accessTokenSecret);
			twitter.setOAuthAccessToken(oathAccessToken);

			// OAuthentication -- end
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failed to read tweets: " + e.getMessage());
		}
	}
	//This method by default returns latest 20 tweets every call.

	public void GetTweet() throws TwitterException, IOException {

		try {
			connection();
			// Getting time line -- Start
			ResponseList<Status> list = twitter.getHomeTimeline();
			for (Status each : list) {
				System.out.println("User name :: @" + each.getUser().getScreenName()
						+ " - " + each.getUser().getName() + "\n" + each.getText()
						+ "\n" + each.getRetweetCount());
			}
			// Getting time line -- End
		}catch (TwitterException te) {
			te.printStackTrace();
			System.out.println("Failed to read tweets: " + te.getMessage());
		}
	}
	
	// Search Implementation
	
	public void SearchTweets(String q){
		try {
			connection();
			Query query = new Query(q);
			QueryResult result;
			do {
				result = twitter.search(query);
				List<Status> tweets = result.getTweets();
				for (Status tweet : tweets) {
					System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
				}
			} while ((query = result.nextQuery()) != null);
			System.exit(0);
		} catch (TwitterException te) {
			te.printStackTrace();
			System.out.println("Failed to search tweets: " + te.getMessage());
			System.exit(-1);
		}
	}

	private String getSavedAccessTokenSecret() {
		return TweetConstants.ACCESS_TOKEN_SECRET;
	}

	private String getSavedAccessToken() {
		return TweetConstants.ACCESS_TOKEN;	
	}
}

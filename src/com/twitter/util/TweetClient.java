/** 
 * This is a client class where we are actually calling the Twitter API methods....
 *
 */
package com.twitter.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TweetClient {
	public static void main(String[] args) throws Exception {
		System.out.println("Please enter the get tweet or search");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String choice = br.readLine();
		twittercall(choice);
	}
	public static void twittercall(String choice) {
		try{
			TweetAPI tweet = new TweetAPI();
			switch(choice){
			case "get tweet":
				tweet.GetTweet(); // API Method
				break;
			case "search":
				System.out.println("Enter input String to search in tweets");
				BufferedReader searchInput = new BufferedReader(new InputStreamReader(System.in));
				String input = searchInput.readLine();
				tweet.SearchTweets(input); //API method
				break;
			default:
				System.out.println("Wrong choice...Please enter the get tweet or search");
				BufferedReader Dinput = new BufferedReader(new InputStreamReader(System.in));
				String Chinput = Dinput.readLine();
				twittercall(Chinput); 
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}

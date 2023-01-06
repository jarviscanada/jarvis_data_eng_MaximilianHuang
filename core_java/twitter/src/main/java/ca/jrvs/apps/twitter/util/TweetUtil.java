package ca.jrvs.apps.twitter.util;

import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import java.util.Arrays;

public class TweetUtil {


  /**
   * Create a tweet with given text, and optionally location (in longitude and latitude)
   * @param text string used as text of tweet
   * @param lon longitude (from -180.0 to 180.0 inclusive)
   * @param lat latitude (from -90.0 to 90.0 inclusive)
   * @return Tweet object
   */
  public static Tweet buildTweet(String text, Double lon, Double lat) {
    Tweet tweet = new Tweet();
    Coordinates coordinates = new Coordinates();
    if ((lon != null) && (lat != null)) {
      coordinates.setCoordinates(Arrays.asList(lon, lat));
      coordinates.setType("Point");
      tweet.setCoordinates(coordinates);
    }
    tweet.setText(text);
    return tweet;
  }

  public static Tweet buildTweet(String text) {
    return buildTweet(text, null, null);
  }

}

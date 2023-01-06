package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.util.TweetUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Controller
public class TwitterController implements Controller {

  private static final String COORD_SEP = ":";
  private static final String COMMA = ",";

  private final Service service;

  @Autowired
  public TwitterController(Service service) {
    this.service = service;
  }

  @Override
  public Tweet postTweet(String[] args) {
    if (args.length != 3) {
      throw new IllegalArgumentException("USAGE: TwitterCLIApp post \"tweet_text\" \"latitude:longitude\"");
    }

    String text = args[1];
    String coords = args[2];
    String[] splitCoords = coords.split(COORD_SEP);
    if (splitCoords.length != 2) {
      throw new IllegalArgumentException("Invalid location format\n"
          + "Usage: TwitterCLIApp post \"tweet_text\" \"latitude:longitude\"");
    }

    double lat;
    double lon;
    try {
      lat = Double.parseDouble(splitCoords[0]);
      lon = Double.parseDouble(splitCoords[1]);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Invalid location format\n"
          + "Usage: TwitterCLIApp post \"tweet_text\" \"latitude:longitude\"");
    }

    Tweet tweet = TweetUtil.buildTweet(text, lon, lat);
    return service.postTweet(tweet);
  }

  @Override
  public Tweet showTweet(String[] args) {
    if (args.length != 2) {
      throw new IllegalArgumentException("USAGE: TwitterCLIApp show tweet_id");
    }
    String idStr = args[1];
    return service.showTweet(idStr, null);
  }

  @Override
  public List<Tweet> deleteTweet(String[] args) {
    if (args.length != 2) {
      throw new IllegalArgumentException("USAGE: TwitterCLIApp delete [id1,id2,...]");
    }
    String idListStr = args[1];
    String[] idList = idListStr.split(COMMA);
    return service.deleteTweets(idList);
  }
}

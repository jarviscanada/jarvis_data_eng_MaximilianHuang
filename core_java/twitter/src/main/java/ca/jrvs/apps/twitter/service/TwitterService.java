package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Tweet;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class TwitterService implements Service {

  private final CrdDao dao;

  @Autowired
  public TwitterService(CrdDao dao) {
    this.dao = dao;
  }

  @Override
  public Tweet postTweet(Tweet tweet) {
    validatePostTweet(tweet);
    return (Tweet) dao.create(tweet);
  }

  private void validatePostTweet(Tweet tweet) {
    String text = tweet.getText();
    Double lat = tweet.getCoordinates().getLatitude();
    Double lon = tweet.getCoordinates().getLongitude();

    if (text.length() == 0) {
      throw new IllegalArgumentException("Tweet text cannot be empty");
    }

    if (text.length() > 280) {
      throw new IllegalArgumentException("Tweet text exceeds 280 characters");
    }

    if ((lat > 90.0) || (lat < -90.0)) {
      throw new IllegalArgumentException("Latitude out of bounds (range of -90.0 to 90.0)");
    }

    if ((lon > 180.0) || (lon < -180.0)) {
      throw new IllegalArgumentException("Longitude out of bounds (range of -180.0 to 180.0)");
    }
  }

  @Override
  public Tweet showTweet(String id, String[] fields) {
    validateId(id);
    return (Tweet) dao.findById(id);
  }


  @Override
  public List<Tweet> deleteTweets(String[] ids) {
    List<Tweet> tweets = new ArrayList<>();
    for (String id : ids) {
      validateId(id);
      tweets.add((Tweet) dao.deleteById(id));
    }
    return tweets;
  }

  private void validateId (String id) {
    try {
      Long.parseLong(id);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException(id + " not a valid tweet ID, must be long int");
    }
  }
}

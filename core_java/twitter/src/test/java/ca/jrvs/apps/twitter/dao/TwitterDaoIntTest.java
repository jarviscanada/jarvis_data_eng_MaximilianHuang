package ca.jrvs.apps.twitter.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.JsonUtil;
import ca.jrvs.apps.twitter.util.TweetUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TwitterDaoIntTest {

  private TwitterDao dao;
  private static String consumerKey;
  private static String consumerSecret;
  private static String accessToken;
  private static String tokenSecret;

  @BeforeClass
  public static void setupClass() {
    consumerKey = System.getenv("consumerKey");
    consumerSecret = System.getenv("consumerSecret");
    accessToken = System.getenv("accessToken");
    tokenSecret = System.getenv("tokenSecret");
    System.out.println(consumerKey + "\n" + consumerSecret + "\n" + accessToken
        + " " + tokenSecret);
  }

  @Before
  public void setupTest() {
    HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret,
        accessToken, tokenSecret);
    dao = new TwitterDao(httpHelper);
  }

  @Test
  public void testCreate() throws JsonProcessingException {
    String hashtag = "#abc";
    String text = "@huangm66_p41 hello world " + hashtag + " " + System.currentTimeMillis();
    Double lat = 1d;
    Double lon = -1d;
    Tweet postTweet = TweetUtil.buildTweet(text, lon, lat);
    System.out.println(JsonUtil.toPrettyJson(postTweet, false));

    Tweet tweet = dao.create(postTweet);

    assertEquals(text, tweet.getText());

    assertNotNull(tweet.getCoordinates());
    assertEquals(2, tweet.getCoordinates().getCoordinates().size());
    assertEquals(lon, tweet.getCoordinates().getLongitude());
    assertEquals(lat, tweet.getCoordinates().getLatitude());

    assertTrue(hashtag.contains(tweet.getEntities().getHashtags().get(0).getText()));
  }

  @Test
  public void testFindById() throws JsonProcessingException {
    String hashtag = "#abc";
    String text = "@huangm66_p41 hello world " + hashtag + " " + System.currentTimeMillis();
    Double lat = 1d;
    Double lon = -1d;
    Tweet postTweet = TweetUtil.buildTweet(text, lon, lat);
    System.out.println(JsonUtil.toPrettyJson(postTweet, false));

    Tweet tweet = dao.create(postTweet);
    String idStr = tweet.getIdStr();

    Tweet foundTweet = dao.findById(idStr);
    assertEquals(foundTweet.getIdStr(), idStr);
    assertEquals(foundTweet.getText(), text);
  }


  @Test
  public void testDeleteById() throws JsonProcessingException {
    String hashtag = "#abc";
    String text = "@huangm66_p41 hello world " + hashtag + " " + System.currentTimeMillis();
    Double lat = 1d;
    Double lon = -1d;
    Tweet postTweet = TweetUtil.buildTweet(text, lon, lat);
    System.out.println(JsonUtil.toPrettyJson(postTweet, false));

    Tweet tweet = dao.create(postTweet);
    String idStr = tweet.getIdStr();

    Tweet deletedTweet = dao.deleteById(idStr);
    assertEquals(deletedTweet.getIdStr(), idStr);
    assertEquals(deletedTweet.getText(), tweet.getText());
  }
}
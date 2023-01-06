package ca.jrvs.apps.twitter.controller;

import static org.junit.Assert.*;

import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.service.TwitterService;
import java.util.List;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TwitterControllerIntTest {

  private TwitterController controller;

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
    TwitterDao dao = new TwitterDao(httpHelper);
    Service service = new TwitterService(dao);
    controller = new TwitterController(service);
  }

  @Test
  public void postTweet() {
    String hashtag = "#abc";
    String text = "@huangm66_p41 hello world " + hashtag + " " + System.currentTimeMillis();
    String coords = "50:60";
    String[] args = {"post", text, coords};

    Tweet tweet = controller.postTweet(args);

    assertEquals(text, tweet.getText());

    assertNotNull(tweet.getCoordinates());
    assertEquals(2, tweet.getCoordinates().getCoordinates().size());
    assertEquals((Double) 60d, tweet.getCoordinates().getLongitude());

    assertTrue(hashtag.contains(tweet.getEntities().getHashtags().get(0).getText()));
  }

  @Test
  public void showTweet() {
    String text = "abc " + System.currentTimeMillis();
    String coords = "50:60";
    String[] postArgs = {"post", text, coords};
    Tweet resultTweet = controller.postTweet(postArgs);

    String idStr = resultTweet.getIdStr();
    String[] args = {"show", idStr};
    Tweet tweet = controller.showTweet(args);

    assertEquals(text, tweet.getText());

    assertNotNull(tweet.getCoordinates());
    assertEquals(2, tweet.getCoordinates().getCoordinates().size());
    assertEquals((Double) 60d, tweet.getCoordinates().getLongitude());
  }

  @Test
  public void deleteTweet() {
    String text = "abc " + System.currentTimeMillis();
    String coords = "50:60";
    String[] postArgs = {"post", text, coords};
    Tweet resultTweet = controller.postTweet(postArgs);

    String idStr = resultTweet.getIdStr();
    String[] args = {"delete", idStr};
    List<Tweet> tweets = controller.deleteTweet(args);

    Tweet tweet = tweets.get(0);

    assertEquals(text, tweet.getText());

    assertNotNull(tweet.getCoordinates());
    assertEquals(2, tweet.getCoordinates().getCoordinates().size());
    assertEquals((Double) 60d, tweet.getCoordinates().getLongitude());
  }
}
package ca.jrvs.apps.twitter.service;

import static org.junit.Assert.*;

import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.JsonUtil;
import ca.jrvs.apps.twitter.util.TweetUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import org.assertj.core.util.Arrays;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TwitterServiceIntTest {

  private TwitterService service;
  private Tweet inputTweet;
  private String hashtag;
  private String text;
  private Double lon;
  private Double lat;

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
  public void setupTest() throws JsonProcessingException {
    HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret,
        accessToken, tokenSecret);
    TwitterDao dao = new TwitterDao(httpHelper);
    service = new TwitterService(dao);

    hashtag = "#abc";
    text = "@huangm66_p41 hello world " + hashtag + " " + System.currentTimeMillis();
    lat = 1d;
    lon = -1d;
    inputTweet = TweetUtil.buildTweet(text, lon, lat);
    System.out.println(JsonUtil.toPrettyJson(inputTweet, false));
  }

  @Test
  public void postTweet() {
    Tweet resultTweet = service.postTweet(inputTweet);

    assertEquals(text, resultTweet.getText());

    assertNotNull(resultTweet.getCoordinates());
    assertEquals(2, resultTweet.getCoordinates().getCoordinates().size());
    assertEquals(lon, resultTweet.getCoordinates().getLongitude());
    assertEquals(lat, resultTweet.getCoordinates().getLatitude());

    assertTrue(hashtag.contains(resultTweet.getEntities().getHashtags().get(0).getText()));
  }

  @Test
  public void showTweet() {
    Tweet resultTweet = service.postTweet(inputTweet);
    String idStr = resultTweet.getIdStr();

    Tweet foundTweet = service.showTweet(idStr, null);

    assertEquals(foundTweet.getIdStr(), idStr);
    assertEquals(foundTweet.getText(), text);
  }

  @Test
  public void deleteTweets() {
    Tweet resultTweet = service.postTweet(inputTweet);
    String idStr = resultTweet.getIdStr();

    List<Tweet> deletedTweet = service.deleteTweets(Arrays.array(idStr));

    assertNotNull(deletedTweet.get(0));
    assertEquals(deletedTweet.get(0).getIdStr(), idStr);
    assertEquals(deletedTweet.get(0).getText(), text);
  }
}
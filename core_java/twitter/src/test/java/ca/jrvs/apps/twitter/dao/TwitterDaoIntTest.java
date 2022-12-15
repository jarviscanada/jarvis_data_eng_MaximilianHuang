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
        + "\n" + tokenSecret);
  }

  @Before
  public void setupTest() throws JsonProcessingException {
    HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret,
        accessToken, tokenSecret);
    dao = new TwitterDao(httpHelper);
    hashtag = "#abc";
    text = "@huangm66_p41 hello world " + hashtag + " " + System.currentTimeMillis();
    lat = 1d;
    lon = -1d;
    inputTweet = TweetUtil.buildTweet(text, lon, lat);
    System.out.println(JsonUtil.toPrettyJson(inputTweet, false));
  }

  @Test
  public void testCreate() {
    Tweet resultTweet = dao.create(inputTweet);

    assertEquals(text, resultTweet.getText());

    assertNotNull(resultTweet.getCoordinates());
    assertEquals(2, resultTweet.getCoordinates().getCoordinates().size());
    assertEquals(lon, resultTweet.getCoordinates().getLongitude());
    assertEquals(lat, resultTweet.getCoordinates().getLatitude());

    assertTrue(hashtag.contains(resultTweet.getEntities().getHashtags().get(0).getText()));
  }

  @Test
  public void testFindById() {
    Tweet resultTweet = dao.create(inputTweet);
    String idStr = resultTweet.getIdStr();

    Tweet foundTweet = dao.findById(idStr);

    assertEquals(foundTweet.getIdStr(), idStr);
    assertEquals(foundTweet.getText(), text);
  }


  @Test
  public void testDeleteById() {
    Tweet resultTweet = dao.create(inputTweet);
    String idStr = resultTweet.getIdStr();

    Tweet deletedTweet = dao.deleteById(idStr);

    assertEquals(deletedTweet.getIdStr(), idStr);
    assertEquals(deletedTweet.getText(), text);
  }
}
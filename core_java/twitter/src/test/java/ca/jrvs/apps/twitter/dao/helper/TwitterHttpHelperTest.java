package ca.jrvs.apps.twitter.dao.helper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class TwitterHttpHelperTest {

  private static HttpHelper httpHelper;

  @BeforeClass
  public static void setupTest() {
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");

    httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret,
        accessToken, tokenSecret);
  }

  @Test
  public void testHttpPost() throws URISyntaxException, IOException {
    HttpResponse response = httpHelper.httpPost(new URI(
        "https://api.twitter.com/1.1/statuses/update.json?status=HelloWorld"));
    System.out.println(EntityUtils.toString(response.getEntity()));
  }

  @Ignore
  @Test
  public void testHttpGet() {
  }
}
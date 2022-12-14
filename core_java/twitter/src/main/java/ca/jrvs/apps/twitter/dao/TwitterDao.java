package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.JsonUtil;
import com.google.gdata.util.common.base.PercentEscaper;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

public class TwitterDao implements CrdDao<Tweet, String> {

  // URI constants
  private static final String API_BASE_URI = "https://api.twitter.com";
  private static final String POST_PATH = "/1.1/statuses/update.json";
  private static final String SHOW_PATH = "/1.1/statuses/show.json";
  private static final String DELETE_PATH = "/1.1/statuses/destroy/";

  // URI symbols
  private static final String QUERY_SYM = "?";
  private static final String AMPERSAND = "&";
  private static final String EQUAL = "=";

  // Response code
  private static final int HTTP_OK = 200;

  private final HttpHelper httpHelper;

  public TwitterDao(HttpHelper httpHelper) {
    this.httpHelper = httpHelper;
  }

  /**
   * Create a Tweet based on Tweet Object
   * @param entity entity to be created
   * @return Tweet that has been created
   */
  @Override
  public Tweet create(Tweet entity) {
    URI uri;
    try {
      uri = getPostUri(entity);
    } catch (URISyntaxException e) {
      throw new IllegalArgumentException("Invalid tweet input", e);
    }

    HttpResponse response = httpHelper.httpPost(uri);
    return parseResponseBody(response, HTTP_OK);
  }

  /**
   * Construct a URI for Tweet creation POST request
   * @param tweet Tweet to be created
   * @return URI for POST request
   * @throws URISyntaxException
   */
  private URI getPostUri(Tweet tweet) throws URISyntaxException {
    PercentEscaper escaper = new PercentEscaper("", false);
    String status = tweet.getText();
    Coordinates coordinates = tweet.getCoordinates();

    return new URI(API_BASE_URI + POST_PATH +
        QUERY_SYM + "status" + EQUAL + escaper.escape(status) +
        AMPERSAND + "lat" + EQUAL + coordinates.getLatitude() +
        AMPERSAND + "long" + EQUAL + coordinates.getLongitude()
    );
  }

  /**
   * Get a Tweet specified by ID string
   * @param id entity id
   * @return Tweet that has been retrieved
   */
  @Override
  public Tweet findById(String id) {
    URI uri = URI.create(API_BASE_URI + SHOW_PATH +
        QUERY_SYM + "id" + EQUAL + id
    );
    HttpResponse response = httpHelper.httpGet(uri);
    return parseResponseBody(response, HTTP_OK);
  }


  /**
   * Delete a Tweet specified by ID string
   * @param id of the entity to be deleted
   * @return Tweet that has been deleted
   */
  @Override
  public Tweet deleteById(String id) {
    URI uri = URI.create(API_BASE_URI + DELETE_PATH + id + ".json");
    HttpResponse response = httpHelper.httpPost(uri);
    return parseResponseBody(response, HTTP_OK);
  }

  /**
   * Convert response from HTTP Tweet request to Tweet object
   * @param response response from Twitter REST request
   * @param expectedStatusCode expected status of reply
   * @return Tweet object
   */
  Tweet parseResponseBody(HttpResponse response, Integer expectedStatusCode) {
    // Check response status
    int status = response.getStatusLine().getStatusCode();
    if (status != expectedStatusCode) {
      try {
        System.out.println(EntityUtils.toString(response.getEntity()));
      } catch (IOException e) {
        System.out.println("Response has no entity");
      }
      throw new RuntimeException("Unexpected HTTP status:" + status);
    }

    if (response.getEntity() == null) {
      throw new RuntimeException("Empty response body");
    }

    // Convert Response entity to String
    String jsonStr;
    try {
      jsonStr = EntityUtils.toString(response.getEntity());
    } catch (IOException e) {
      throw new RuntimeException("Failed to convert entity to String", e);
    }

    // Deserialize JSON string to Tweet object
    try {
      return JsonUtil.toObjectFromJson(jsonStr, Tweet.class);
    } catch (IOException e) {
      throw new RuntimeException("Unable to convert JSON string to Tweet object", e);
    }
  }
}

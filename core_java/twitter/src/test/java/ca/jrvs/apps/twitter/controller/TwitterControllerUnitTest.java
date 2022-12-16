package ca.jrvs.apps.twitter.controller;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.TwitterService;
import java.util.ArrayList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwitterControllerUnitTest {

  @Mock
  TwitterService service;

  @InjectMocks
  TwitterController controller;

  @Test
  public void postTweet() {
    when(service.postTweet(any())).thenReturn(new Tweet());
    controller.postTweet(new String[] {"post", "hello", "50:60"});

    // Failing test cases
    // Coordinates missing
    try {
      controller.postTweet(new String[] {"post", "hello"});
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }

    // Coordinates not float
    try {
      controller.postTweet(new String[] {"post", "hello", "50:a"});
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }

    // Coordinates have more than 2 parts
    try {
      controller.postTweet(new String[] {"post", "hello", "50:60:70"});
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
  }

  @Test
  public void showTweet() {
    when(service.showTweet(any(), any())).thenReturn(new Tweet());
    controller.showTweet(new String[] {"show", "123"});

    // ID empty
    try {
      controller.postTweet(new String[] {"show", ""});
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }

    // ID missing
    try {
      controller.postTweet(new String[] {"show"});
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
  }

  @Test
  public void deleteTweet() {
    when(service.deleteTweets(any())).thenReturn(new ArrayList<>());
    controller.deleteTweet(new String[] {"delete", "123"});
    controller.deleteTweet(new String[] {"delete", "123,234,345"});

    // ID missing
    try {
      controller.deleteTweet(new String[] {"delete"});
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }

    // Too many arguments
    try {
      controller.deleteTweet(new String[] {"delete", "123", "2345"});
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }

  }
}
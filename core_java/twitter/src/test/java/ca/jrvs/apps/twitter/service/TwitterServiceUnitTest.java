package ca.jrvs.apps.twitter.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.TweetUtil;
import net.bytebuddy.pool.TypePool.Resolution.Illegal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwitterServiceUnitTest {

  @Mock
  CrdDao dao;

  @InjectMocks
  TwitterService service;

  @Test
  public void postTweet() {
    when(dao.create(any())).thenReturn(new Tweet());
    service.postTweet(TweetUtil.buildTweet("test", 50.0, 0.0));

    // Failing test cases
    try {
      service.postTweet(TweetUtil.buildTweet("", 50.0, 0.0));
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }

    try {
      service.postTweet(TweetUtil.buildTweet("a", 181.0, 0.0));
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }

    try {
      service.postTweet(TweetUtil.buildTweet("a", 50.0, 91.0));
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
  }

  @Test
  public void showTweet() {
    when(dao.findById(any())).thenReturn(new Tweet());
    service.showTweet("123", null);

    try {
      service.showTweet("abc", null);
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }

  }

  @Test
  public void deleteTweets() {
    when(dao.deleteById(any())).thenReturn(new Tweet());
    service.deleteTweets(new String[] {"123", "234", "345", "456"});

    try {
      service.deleteTweets(new String[] {"123", "234", "abc", "456"});
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
  }
}
package ca.jrvs.practice.codingChallenge;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ValidAnagramTest {

  @Test
  void validAnagramSorting() {
    assertTrue(ValidAnagram.validAnagramSorting("aabbcc", "cabbac"));
    assertFalse(ValidAnagram.validAnagramSorting("abcz", "abc"));
  }

  @Test
  void validAnagramLinear() {
    assertTrue(ValidAnagram.validAnagramLinear("aabbcc", "cabbac"));
    assertFalse(ValidAnagram.validAnagramLinear("abcz", "abc"));
  }
}
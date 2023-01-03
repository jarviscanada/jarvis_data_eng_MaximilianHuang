package ca.jrvs.practice.codingChallenge;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StringContainsOnlyDigitsTest {

  @Test
  void checkStringAscii() {
    assertAll(() -> assertTrue(StringContainsOnlyDigits.checkStringAscii("12345")),
        () -> assertFalse(StringContainsOnlyDigits.checkStringAscii("123,45")));
  }

  @Test
  void checkStringAPI() {
    assertAll(() -> assertTrue(StringContainsOnlyDigits.checkStringAPI("12345")),
        () -> assertFalse(StringContainsOnlyDigits.checkStringAPI("123,45")));
  }

  @Test
  void checkStringRegex() {
    assertAll(() -> assertTrue(StringContainsOnlyDigits.checkStringRegex("12345")),
        () -> assertFalse(StringContainsOnlyDigits.checkStringRegex("123,45")));
  }
}
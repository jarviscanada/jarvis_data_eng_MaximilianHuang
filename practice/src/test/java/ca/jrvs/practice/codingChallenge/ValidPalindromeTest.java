package ca.jrvs.practice.codingChallenge;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ValidPalindromeTest {

  ValidPalindrome vp = new ValidPalindrome();
  String str1 = "A man, a plan, a canal: Panama.";
  String str2 = "Race a car";


  @Test
  void checkPointers() {
    assertTrue(vp.checkPointers(str1));
    assertFalse(vp.checkPointers(str2));
  }

  @Test
  void checkRecursive() {
    assertTrue(vp.checkRecursive(str1));
    assertFalse(vp.checkRecursive(str2));
  }
}
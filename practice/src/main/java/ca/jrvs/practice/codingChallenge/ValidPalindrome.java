package ca.jrvs.practice.codingChallenge;

/*
 * Ticket: https://www.notion.so/jarvisdev/Valid-Palindrome-9db09b820489450a8e092d0224b6de82
 */
public class ValidPalindrome {

  public boolean checkPointers(String str) {
    String s = str.toLowerCase().replaceAll("[^a-zA-Z0-9]", "");
    int n = s.length();
    if (n < 2) return true;

    int l = 0, r = n-1;
    while (l < r) {
      if (s.charAt(l) != s.charAt(r)) {
        return false;
      }
      l++;
      r--;
    }
    return true;
  }

  public boolean checkRecursive(String str) {
    String s = str.toLowerCase().replaceAll("[^a-zA-Z0-9]", "");
    int n = s.length();
    if (n < 2) return true;

    if (s.charAt(0) != s.charAt(n-1)) {
      return false;
    }
    return checkRecursive(s.substring(1, n-1));
  }
}

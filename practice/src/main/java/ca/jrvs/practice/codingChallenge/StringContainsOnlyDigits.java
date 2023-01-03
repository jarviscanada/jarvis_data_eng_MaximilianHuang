package ca.jrvs.practice.codingChallenge;

import java.util.regex.Pattern;

/*
 * Ticket: https://www.notion.so/jarvisdev/Check-if-a-String-contains-only-digits-ed4fefbc93d148629d71bb080712579b
 */
public class StringContainsOnlyDigits {

  /*
   * Big-O: O(n)
   * Iterates over string a single time
   */
  public static boolean checkStringAscii(String s) {
    for (char c : s.toCharArray()) {
      if (((int) c < 48) || ((int) c > 57)) {
        return false;
      }
    }
    return true;
  }

  /*
   * Big-O: O(n)
   * Iterates over string a single time (valueOf parses string by using single loop)
   */
  public static boolean checkStringAPI(String s) {
    try {
      Integer.valueOf(s);
    } catch (NumberFormatException e) {
      return false;
    }
    return true;
  }

  /*
   * Big-O: O(n)
   * Regex runs on a string of length n in O(n) time
   * https://en.wikipedia.org/wiki/Regular_expression#Implementations_and_running_times
   */
  public static boolean checkStringRegex(String s) {
    String regex = "\\d+";
    Pattern pattern = Pattern.compile(regex);
    return pattern.matcher(s).matches();
  }

}

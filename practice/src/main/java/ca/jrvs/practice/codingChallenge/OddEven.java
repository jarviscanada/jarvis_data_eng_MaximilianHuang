package ca.jrvs.practice.codingChallenge;

/*
 * Ticket: https://www.notion.so/jarvisdev/Sample-Check-if-a-number-is-even-or-odd-726863de71764f088b0e690a109889bc
 */
public class OddEven {
  /*
   * Big-O: O(1)
   * Method only has one arithmetic statement
   */
  public static String oddEvenMod(int i) {
    return i % 2 == 0 ? "even" : "odd";
  }

  /*
   * Big-O: O(1)
   * Only applies bitwise operator once
   */
  public static String oddEvenBit(int i) {
    return (i & 1) == 0 ? "even" : "odd";
  }
}

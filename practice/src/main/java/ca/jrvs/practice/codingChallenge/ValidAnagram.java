package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;

/*
 * Ticket: https://www.notion.so/jarvisdev/Valid-Anagram-61cdc00fe4cb4ce5870b2a3c4594d40c
 */
public class ValidAnagram {

  /*
   * Big-O: n log(n)
   * Best sorting algorithms are O(n log(n)), plus O(n) required for comparing Strings
   */
  public static boolean validAnagramSorting(String s, String t) {
    String sSorted = sortString(s);
    String tSorted = sortString(t);
    return sSorted.equals(tSorted);
  }

  private static String sortString(String s) {
    char[] tmp = s.toCharArray();
    Arrays.sort(tmp);
    return new String(tmp);
  }

  /*
   * Big-O: O(n)
   * Loops done over each string, running constant time operations
   */
  public static boolean validAnagramLinear(String s, String t) {
    int[] sCount = new int[26];
    int[] tCount = new int[26];

    for (char c : s.toCharArray()) {
      int ascii = (int) c - 97;
      sCount[ascii] += 1;
    }

    for (char c : t.toCharArray()) {
      int ascii = (int) c - 97;
      tCount[ascii] += 1;
    }

    return Arrays.equals(sCount, tCount);
  }
}

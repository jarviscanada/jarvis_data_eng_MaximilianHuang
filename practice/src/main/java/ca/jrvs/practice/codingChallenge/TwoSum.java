package ca.jrvs.practice.codingChallenge;

import java.util.HashMap;

/*
 * Ticket: https://www.notion.so/jarvisdev/Two-Sum-2e59c2097174414098c4fbd947e1daca
 */
public class TwoSum {

  /*
   * Big-O: O(n^2)
   * Uses nested loop over nums (array of size n)
   */
  public static int[] twoSumBruteForce(int[] nums, int target) {
    for (int i = 0; i < nums.length - 1; i++) {
      int num = nums[i];
      for (int j = i + 1; j < nums.length; j++) {
        if (nums[j] == target - num) {
          return new int[] {i, j};
        }
      }
    }
    return null;
  }

  /*
   * Big-O: O(n)
   * Iterates over nums once, doing constant time operations (hash table)
   */
  public static int[] twoSumMap(int[] nums, int target) {
    HashMap<Integer, Integer> seenValues = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      int num = nums[i];
      int diff = target - num;
      if (seenValues.containsKey(diff)) {
        return new int[] {seenValues.get(diff), i};
      } else {
        seenValues.put(num, i);
      }
    }
    return null;
  }

}

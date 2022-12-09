package ca.jrvs.practice.codingChallenge;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TwoSumTest {

  @Test
  void twoSumBruteForce() {
    assertAll(
        () -> assertArrayEquals(new int[] {0, 1}, TwoSum.twoSumBruteForce(new int[] {2,7,11,15}, 9)),
        () -> assertArrayEquals(new int[] {1, 2}, TwoSum.twoSumBruteForce(new int[] {3, 2, 4}, 6)),
        () -> assertArrayEquals(new int[] {0, 1}, TwoSum.twoSumBruteForce(new int[] {3, 3}, 6))
    );
  }

  @Test
  void twoSumMap() {
    assertAll(
        () -> assertArrayEquals(new int[] {0, 1}, TwoSum.twoSumMap(new int[] {2,7,11,15}, 9)),
        () -> assertArrayEquals(new int[] {1, 2}, TwoSum.twoSumMap(new int[] {3, 2, 4}, 6)),
        () -> assertArrayEquals(new int[] {0, 1}, TwoSum.twoSumMap(new int[] {3, 3}, 6))
    );
  }
}
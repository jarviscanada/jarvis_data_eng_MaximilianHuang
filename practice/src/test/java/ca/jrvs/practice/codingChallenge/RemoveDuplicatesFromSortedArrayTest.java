package ca.jrvs.practice.codingChallenge;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

class RemoveDuplicatesFromSortedArrayTest {

  @Test
  void removeDuplicates() {
    RemoveDuplicatesFromSortedArray rd = new RemoveDuplicatesFromSortedArray();
    int[] arr1 = {1, 1, 2};
    int[] arr2 = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};

    int res1 = rd.removeDuplicates(arr1);
    int res2 = rd.removeDuplicates(arr2);

    assertEquals(2, res1);
    assertEquals(5, res2);

    assertArrayEquals(new int[] {1, 2}, Arrays.copyOf(arr1, res1));
    assertArrayEquals(new int[] {0, 1, 2, 3, 4}, Arrays.copyOf(arr2, res2));
  }
}
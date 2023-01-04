package ca.jrvs.practice.sorting;

import static org.junit.Assert.*;

import org.junit.Test;

public class QuickSortTest {
  int[] ints = {2, 3, 1, -5, 6, 4};

  @Test
  public void quicksort() {
    QuickSort qs = new QuickSort();
    qs.quicksort(ints, 0, ints.length - 1);
    for (int i = 0; i < ints.length - 1; i++) {
      assertTrue(ints[i] <= ints[i + 1]);
    }
  }
}
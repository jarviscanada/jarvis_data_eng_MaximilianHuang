package ca.jrvs.practice.sorting;

import static org.junit.Assert.*;

import org.junit.Test;

public class MergeSortTest {

  Integer[] ints = {2, 3, 1, -5, 6, 4};

  @Test
  public void sort() {
    MergeSort.sort(ints);
    for (int i = 0; i < ints.length - 1; i++) {
      assertTrue(ints[i] <= ints[i + 1]);
    }
  }
}
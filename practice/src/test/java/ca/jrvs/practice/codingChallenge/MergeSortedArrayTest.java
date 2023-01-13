package ca.jrvs.practice.codingChallenge;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MergeSortedArrayTest {

  @Test
  void merge() {
    MergeSortedArray msa = new MergeSortedArray();
    int[] nums1 = {1, 2, 3, 0, 0, 0};
    int[] nums2 = {4, 5, 6};
    msa.merge(nums1, 3, nums2, 3);
    assertArrayEquals(new int[] {1, 2, 3, 4, 5, 6}, nums1);

    int[] nums3 = {1};
    int[] nums4 = {};
    msa.merge(nums3, 1, nums4, 0);
    assertArrayEquals(new int[] {1}, nums3);

    int[] nums5 = {0};
    int[] nums6 = {1};
    msa.merge(nums5, 0, nums6, 1);
    assertArrayEquals(new int[] {1}, nums5);
  }
}
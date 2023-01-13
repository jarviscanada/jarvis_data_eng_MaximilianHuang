package ca.jrvs.practice.codingChallenge;

/*
 * Ticket: https://www.notion.so/jarvisdev/Merge-Sorted-Array-f968345abd084c3aa9fac1c543e8b9ad
 */
public class MergeSortedArray {

  /*
   * Big-O: O(m+n)
   * Iterates over nums1 and nums2 a single time
   */
  public void merge(int[] nums1, int m, int[] nums2, int n) {
    int i = m - 1, j = n - 1, k = m + n - 1;

    while ((i >= 0) && (j >= 0)) {
      if (nums1[i] > nums2[j]) {
        nums1[k] = nums1[i];
        i--;
      } else {
        nums1[k] = nums2[j];
        j--;
      }
      k -= 1;
    }
    while (i >= 0) {
      nums1[k] = nums1[i];
      i--;
      k--;
    }
    while (j >= 0) {
      nums1[k] = nums2[j];
      j--;
      k--;
    }
  }
}

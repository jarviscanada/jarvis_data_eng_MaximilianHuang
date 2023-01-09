package ca.jrvs.practice.codingChallenge;

public class RemoveDuplicatesFromSortedArray {

  public int removeDuplicates(int[] arr) {
    int n = arr.length;

    if (n < 2) {
      return n;
    }

    int idx = 1;
    for (int i=0; i<n-1; i++) {
      if (arr[i] < arr[i+1]) {
        arr[idx] = arr[i + 1];
        idx++;
      }
    }
    return idx;
  }

}

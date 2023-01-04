package ca.jrvs.practice.sorting;

import java.util.Arrays;

public class MergeSort {

  public static <E extends Comparable<E>> void sort(E[] arr) {
    int n = arr.length;
    if (n < 2) {
      return;
    }

    int mid = n / 2;
    E[] l = Arrays.copyOf(arr, mid);
    E[] r = Arrays.copyOf(arr, n - mid);

    System.arraycopy(arr, 0, l, 0, mid);
    System.arraycopy(arr, mid, r, 0, n - mid);

    sort(l);
    sort(r);

    merge(arr, l, r, mid, n - mid);
  }

  private static <E extends Comparable<E>> void merge(E[] arr, E[] l, E[] r, int left, int right) {
    int i = 0, j = 0, k = 0;

    while (i < left && j < right) {
      if (l[i].compareTo(r[j]) < 0) {
        arr[k++] = l[i++];
      } else {
        arr[k++] = r[j++];
      }
    }
    while (i < left) {
      arr[k++] = l[i++];
    }
    while (j < right) {
      arr[k++] = r[j++];
    }
  }

}

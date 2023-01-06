package ca.jrvs.practice.search;

import java.util.Optional;

public class BinarySearch {

  /**
   * find the target index in a sorted array
   *
   * @param arr sorted input array
   * @param target value to be searched
   * @return target index or Optional.empty() if not found
   */
  public <E extends Comparable<E>> Optional<Integer> binarySearchRecursion(E[] arr, E target) {
    return binarySearchHelper(arr, target, 0, arr.length - 1);
  }


  public <E extends Comparable<E>> Optional<Integer> binarySearchHelper(E[] arr, E target, int l, int r) {
    if (l > r) {
      return Optional.empty();
    }

    int mid = (l + r) / 2;
    if (target.compareTo(arr[mid]) < 0) {
      return binarySearchHelper(arr, target, l, mid - 1);
    } else if (target.compareTo(arr[mid]) > 0) {
      return binarySearchHelper(arr, target, mid + 1, r);
    } else {
      return Optional.of(mid);
    }

  }

  /**
   * find the target index in a sorted array
   *
   * @param arr sorted input array
   * @param target value to be searched
   * @return target index or Optional.empty() if not found
   */
  public <E extends Comparable<E>> Optional<Integer> binarySearchIteration(E[] arr, E target) {
    int l = 0, r = arr.length - 1;
    while (l <= r) {
      int mid = (l + r) / 2;
      if (target.compareTo(arr[mid]) < 0) {
        r = mid - 1;
      } else if (target.compareTo(arr[mid]) > 0) {
        l = mid + 1;
      } else {
        return Optional.of(mid);
      }
    }
    return Optional.empty();
  }
}
package ca.jrvs.practice.sorting;

public class QuickSort {

  public void quicksort(int[] arr, int start, int end) {
    if (start < end) {
      int partitionIndex = partition(arr, start, end);

      quicksort(arr, start, partitionIndex - 1);
      quicksort(arr, partitionIndex + 1, end);
    }
  }

  private int partition(int[] arr, int start, int end) {
    int pivot = arr[end];
    int i = start - 1;

    for (int j = start; j < end; j++) {
      if (arr[j] <= pivot) {
        i++;

        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
      }
    }

    int tmp = arr[i+1];
    arr[i+1] = arr[end];
    arr[end] = tmp;

    return i + 1;
  }

}

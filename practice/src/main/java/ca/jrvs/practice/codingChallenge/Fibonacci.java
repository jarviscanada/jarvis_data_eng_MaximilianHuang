package ca.jrvs.practice.codingChallenge;

/*
 * Ticket: https://www.notion.so/jarvisdev/Fibonacci-Number-Climbing-Stairs-ba5b129b4b734c0188dcd98b79e09c03
 */
public class Fibonacci {

  /*
   * Big-O: O(2^n)
   * Each call of function recursively makes 2 more calls
   */
  public static int fibonacciRecursion(int n) {
    if (n < 2) {
      return n;
    }

    return fibonacciRecursion(n - 1) + fibonacciRecursion(n - 2);
  }

  /*
   * Big-O: O(n)
   * Runs a single loop, only assigning variables, over array of n elements
   */
  public static int fibonacciDynamic(int n) {
    int[] cache = new int[n + 1];
    if (n < 2) {
      return n;
    }
    cache[0] = 0;
    cache[1] = 1;

    for (int i=2; i<=n; i++) {
      cache[i] = cache[i-1] + cache[i-2];
    }
    return cache[n];
  }
}

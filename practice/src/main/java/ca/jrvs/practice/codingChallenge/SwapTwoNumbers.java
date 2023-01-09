package ca.jrvs.practice.codingChallenge;

/*
 * Ticket: https://www.notion.so/jarvisdev/Swap-two-numbers-50a6bcaa3cc14bbbb0e94fc64cc0573d
 */
public class SwapTwoNumbers {
  // Swap two numbers without using a third variable

  /*
   * Big-O: O(1)
   * Only bitwise operations
   */
  private static void swapTwoNumbersBit(int i, int j) {
    i = i ^ j;
    j = j ^ i;
    i = i ^ j;
    System.out.println("Values now " + i + " and " + j);
  }

  /*
   * Big-O: O(1)
   * Only arithmetic operations
   */
  private static void swapTwoNumbersArithmetic(int i, int j) {
    i += j;
    j = i - j;
    i -= j;
    System.out.println("Values now " + i + " and " + j);
  }

  public static void main(String[] args) {
    swapTwoNumbersBit(1, 2);
    swapTwoNumbersArithmetic(2, 3);
  }

}

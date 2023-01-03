package ca.jrvs.practice.codingChallenge;

/*
 * Ticket: https://www.notion.so/jarvisdev/Nth-Node-From-End-of-LinkedList-9a27e3d2f2304c3aa8bcaeba5f18e62a
 */
public class NthNodeFromEnd {

  /*
   * Big-O: O(n)
   * Only requires one loop over linked list
   */
  public static ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode slow = head;
    ListNode fast = head;

    for (int i=0; i<n; i++) {
      fast = fast.next;
    }

    if (fast == null) {
      return head.next;
    }

    while (fast.next != null) {
      fast = fast.next;
      slow = slow.next;
    }

    slow.next = slow.next.next;
    return head;
  }
}

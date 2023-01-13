package ca.jrvs.practice.codingChallenge;

/*
 * Ticket: https://www.notion.so/jarvisdev/Reverse-Linked-List-5b4738b104414e2cadfa44601aa5fa38
 */
public class ReverseLinkedList {

  /*
   * Big-O: O(n)
   * Iterates over linked list only once
   */
  public ListNode reverseIterative(ListNode head) {
    ListNode prev = null;
    ListNode curr = head;

    while (curr != null) {
      ListNode next = curr.next;
      curr.next = prev;
      prev = curr;
      curr = next;
    }
    return prev;
  }

  /*
   * Big-O: O(n)
   * Iterates over linked list once
   */
  public ListNode reverseRecursive(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }
    ListNode res = reverseRecursive(head.next);
    head.next.next = head;
    head.next = null;
    return res;
  }
}

package ca.jrvs.practice.codingChallenge;

/*
 * Ticket: https://www.notion.so/jarvisdev/Middle-of-the-Linked-List-30550505464a403f88808f65c0de8cbb
 */
public class MiddleLinkedList {

  /*
   * Big-O: O(n)
   * Iterates over linked list once
   */
  public ListNode findMiddle(ListNode head) {
    ListNode fast = head, slow = head;

    while ((fast != null) && (fast.next != null)) {
      fast = fast.next.next;
      slow = slow.next;
    }

    return slow;
  }
}

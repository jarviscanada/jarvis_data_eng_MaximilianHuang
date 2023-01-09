package ca.jrvs.practice.codingChallenge;

/*
 * Ticket: https://www.notion.so/jarvisdev/LinkedList-Cycle-cbc28bbc52c84ad199d5e14b227061b2
 */
public class LinkedListCycle {

  /*
   * Big-O: O(n)
   * Runs through list once if not cycle, at most twice if a cycle
   */
  public boolean checkListCycle(ListNode head) {
    ListNode fast = head;
    ListNode slow = head;

    while ((fast != null) && (fast.next != null)) {
      fast = fast.next.next;
      slow = slow.next;
      if (fast == slow) {
        return true;
      }
    }
    return false;
  }

}

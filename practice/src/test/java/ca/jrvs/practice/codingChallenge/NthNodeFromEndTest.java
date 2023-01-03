package ca.jrvs.practice.codingChallenge;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NthNodeFromEndTest {

  @Test
  void removeNthFromEnd() {
    ListNode lst1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
    ListNode lst2 = new ListNode(1);
    ListNode lst3 = new ListNode(1, new ListNode(2));

    assertEquals(1, lst1.val);
    assertEquals(3, lst1.next.next.val);
    assertEquals(4, lst1.next.next.next.val);
    ListNode res1 = NthNodeFromEnd.removeNthFromEnd(lst1, 2);
    assertEquals(1, res1.val);
    assertEquals(3, res1.next.next.val);
    assertEquals(5, res1.next.next.next.val);

    assertEquals(1, lst2.val);
    ListNode res2 = NthNodeFromEnd.removeNthFromEnd(lst2, 1);
    assertNull(res2);


    assertEquals(2, lst3.next.val);
    ListNode res3 = NthNodeFromEnd.removeNthFromEnd(lst3, 1);
    assertNull(res3.next);
  }
}
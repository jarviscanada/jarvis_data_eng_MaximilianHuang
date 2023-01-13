package ca.jrvs.practice.codingChallenge;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MiddleLinkedListTest {

  @Test
  void findMiddle() {
    MiddleLinkedList mll = new MiddleLinkedList();
    ListNode head1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
    ListNode head2 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, new ListNode(6))))));
    
    ListNode res1 = mll.findMiddle(head1);
    ListNode res2 = mll.findMiddle(head2);
    
    assertEquals(3, res1.val);
    assertEquals(4, res2.val);
  }
}
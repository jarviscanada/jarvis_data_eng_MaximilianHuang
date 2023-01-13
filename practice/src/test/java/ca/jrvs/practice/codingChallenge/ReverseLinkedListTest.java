package ca.jrvs.practice.codingChallenge;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReverseLinkedListTest {

  ReverseLinkedList rll;
  ListNode head;
  ListNode shortHead;

  @BeforeEach
  void init() {
    rll = new ReverseLinkedList();
    head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
    shortHead = new ListNode(1, new ListNode(2));
  }

  @Test
  void reverseIterative() {
    ListNode res1 = rll.reverseIterative(head);
    assertEquals(5, res1.val);
    assertEquals(1, res1.next.next.next.next.val);
    assertNull(res1.next.next.next.next.next);

    ListNode res2 = rll.reverseIterative(shortHead);
    assertEquals(2, res2.val);
    assertEquals(1, res2.next.val);
    assertNull(res2.next.next);
  }

  @Test
  void reverseRecursive() {
    ListNode res1 = rll.reverseRecursive(head);
    assertEquals(5, res1.val);
    assertEquals(1, res1.next.next.next.next.val);
    assertNull(res1.next.next.next.next.next);

    ListNode res2 = rll.reverseRecursive(shortHead);
    assertEquals(2, res2.val);
    assertEquals(1, res2.next.val);
    assertNull(res2.next.next);
  }
}
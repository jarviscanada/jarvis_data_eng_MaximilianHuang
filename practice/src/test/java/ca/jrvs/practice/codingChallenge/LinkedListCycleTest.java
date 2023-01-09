package ca.jrvs.practice.codingChallenge;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class LinkedListCycleTest {

  ListNode loop1 = new ListNode(1);
  ListNode loop2 = new ListNode(2, new ListNode(3, loop1));

  ListNode notLoop = new ListNode(1, new ListNode(2, new ListNode(3)));

  @Test
  void checkListCycle() {
    loop1.next = loop2;
    LinkedListCycle llc = new LinkedListCycle();
    assertTrue(llc.checkListCycle(loop1));
    assertFalse(llc.checkListCycle(notLoop));
  }
}
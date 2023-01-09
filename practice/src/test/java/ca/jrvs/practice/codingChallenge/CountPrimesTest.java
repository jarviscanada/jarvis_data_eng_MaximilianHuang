package ca.jrvs.practice.codingChallenge;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CountPrimesTest {

  @Test
  void countPrimes() {
    CountPrimes cp = new CountPrimes();
    assertEquals(4, cp.countPrimes(10));
    assertEquals(0, cp.countPrimes(1));
  }

}
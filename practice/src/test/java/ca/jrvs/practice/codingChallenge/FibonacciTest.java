package ca.jrvs.practice.codingChallenge;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FibonacciTest {

  @Test
  @DisplayName("Fibonacci sequence calculated using recursion")
  void fibonacciRecursion() {
    assertAll(() -> assertEquals(0, Fibonacci.fibonacciRecursion(0)),
        () -> assertEquals(1, Fibonacci.fibonacciRecursion(1)),
        () -> assertEquals(1, Fibonacci.fibonacciRecursion(2)),
        () -> assertEquals(2, Fibonacci.fibonacciRecursion(3)),
        () -> assertEquals(55, Fibonacci.fibonacciRecursion(10))
    );
  }

  @Test
  void fibonacciDynamic() {
    assertAll(() -> assertEquals(0, Fibonacci.fibonacciDynamic(0)),
        () -> assertEquals(1, Fibonacci.fibonacciDynamic(1)),
        () -> assertEquals(1, Fibonacci.fibonacciDynamic(2)),
        () -> assertEquals(2, Fibonacci.fibonacciDynamic(3)),
        () -> assertEquals(55, Fibonacci.fibonacciDynamic(10))
    );
  }
}
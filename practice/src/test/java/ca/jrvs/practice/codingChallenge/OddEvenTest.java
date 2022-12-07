package ca.jrvs.practice.codingChallenge;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OddEvenTest {

  @Test
  @DisplayName("Check if integer is even or odd, using modulo")
  void oddEvenMod() {
    assertAll(() -> assertEquals("even", OddEven.oddEvenMod(0)),
        () -> assertEquals("even", OddEven.oddEvenMod(2)),
        () -> assertEquals("even", OddEven.oddEvenMod(-2)),
        () -> assertEquals("even", OddEven.oddEvenMod(4)),
        () -> assertEquals("odd", OddEven.oddEvenMod(1)),
        () -> assertEquals("odd", OddEven.oddEvenMod(-1)),
        () -> assertEquals("odd", OddEven.oddEvenMod(5))
    );
  }

  @Test
  @DisplayName("Check if integer is even or odd, using modulo")
  void oddEvenBit() {
    assertAll(() -> assertEquals("even", OddEven.oddEvenBit(0)),
        () -> assertEquals("even", OddEven.oddEvenBit(2)),
        () -> assertEquals("even", OddEven.oddEvenBit(-2)),
        () -> assertEquals("even", OddEven.oddEvenBit(4)),
        () -> assertEquals("odd", OddEven.oddEvenBit(1)),
        () -> assertEquals("odd", OddEven.oddEvenBit(-1)),
        () -> assertEquals("odd", OddEven.oddEvenBit(5))
    );
  }
}
package ca.jrvs.practice.codingChallenge;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ValidParenthesesTest {

  @Test
  void validParentheses() {
    assertAll(() -> assertTrue(ValidParentheses.isValid("")),
        () -> assertTrue(ValidParentheses.isValid("(){}[]")),
        () -> assertTrue(ValidParentheses.isValid("(({[{[(){[]}[()]()]}]}))")),
        () -> assertFalse(ValidParentheses.isValid("}")),
        () -> assertFalse(ValidParentheses.isValid("(]")),
        () -> assertFalse(ValidParentheses.isValid("["))
    );
  }
}
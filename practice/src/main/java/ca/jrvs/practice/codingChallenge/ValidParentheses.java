package ca.jrvs.practice.codingChallenge;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/*
 * Ticket: https://www.notion.so/jarvisdev/Valid-Parentheses-544f6b065f4e4d26b222839ed1480405
 */
public class ValidParentheses {

  /*
   * Big-O: O(n)
   * Loops over string once with constant time operations
   */
  public static boolean isValid(String s) {
    // Create stack to store left brackets, and map for matching right brackets to left
    Deque<Character> deque = new ArrayDeque<>();
    Map<Character, Character> matchingBracket = new HashMap<>();
    matchingBracket.put('}', '{');
    matchingBracket.put(']', '[');
    matchingBracket.put(')', '(');

    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);

      // If next char is left bracket, put on top of stack
      if ("({[".contains(Character.toString(c))) {
        deque.push(c);
      } else {
        try {
          char leftBracket = deque.pop();
          // Check that the most recent left bracket matches right
          if (matchingBracket.get(c) != leftBracket) {
            return false;
          } // If stack is empty, then there is no matching left bracket
        } catch (NoSuchElementException e) {
          return false;
        }
      }
    }

    // Check that all left brackets have been matched
    return deque.isEmpty();
  }
}

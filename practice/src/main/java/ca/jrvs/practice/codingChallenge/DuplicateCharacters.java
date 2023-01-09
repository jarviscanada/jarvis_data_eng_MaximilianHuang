package ca.jrvs.practice.codingChallenge;

import java.util.HashMap;
import java.util.Map;

/*
 * Ticket: https://www.notion.so/jarvisdev/Duplicate-Characters-1ca38888f796416c89ed32a4c013c494
 */
public class DuplicateCharacters {
  public char[] duplicateCharacters(String s) {
    HashMap<Character, Integer> seenChars = new HashMap<>();

    for (char c : s.toCharArray()) {
      if (seenChars.containsKey(c)) {
        int count = seenChars.get(c);
        if (count == 1) {
          seenChars.replace(c, 2);
        }
      } else {
        seenChars.put(c, 1);
      }
    }

    StringBuilder duplicates = new StringBuilder();
    for (Map.Entry<Character, Integer> e : seenChars.entrySet()) {
      if (e.getValue() == 2) {
        if (e.getKey() != ' ') {
          duplicates.append(e.getKey());
        }
      }
    }

    return duplicates.toString().toCharArray();
  }
}

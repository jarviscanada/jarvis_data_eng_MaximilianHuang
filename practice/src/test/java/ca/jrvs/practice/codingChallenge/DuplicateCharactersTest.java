package ca.jrvs.practice.codingChallenge;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DuplicateCharactersTest {

  @Test
  void duplicateCharacters() {
    DuplicateCharacters dc = new DuplicateCharacters();
    char[] res1 = dc.duplicateCharacters("hello world");
    char[] res2 = dc.duplicateCharacters("A black cat");

    assertArrayEquals(new char[] {'l', 'o'}, res1);
    assertArrayEquals(new char[] {'a', 'c'}, res2);
  }
}
package ca.jrvs.practice.search;

import static org.junit.Assert.*;

import java.util.Optional;
import org.junit.Test;

public class BinarySearchTest {

  Integer[] ints = {-1, 0, 3, 5, 9, 12};
  BinarySearch bs = new BinarySearch();

  @Test
  public void binarySearchRecursion() {
    assertEquals(Optional.of(4), bs.binarySearchRecursion(ints, 9));
    assertEquals(Optional.empty(), bs.binarySearchRecursion(ints, 2));
  }

  @Test
  public void binarySearchIteration() {
    assertEquals(Optional.of(4), bs.binarySearchIteration(ints, 9));
    assertEquals(Optional.empty(), bs.binarySearchIteration(ints, 2));
  }
}
package ca.jrvs.apps.practice;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LambdaStreamImp implements LambdaStreamExc {

  @Override
  public Stream<String> createStrStream(String... strings) {
    return Arrays.stream(strings);
  }

  @Override
  public Stream<String> toUpperCase(String... strings) {
    Stream<String> stringStream = Arrays.stream(strings);
    return stringStream.map(String::toUpperCase);
  }

  @Override
  public Stream<String> filter(Stream<String> stringStream, String pattern) {
    return stringStream.filter(s -> !s.equals(pattern));
  }

  @Override
  public IntStream createIntStream(int[] arr) {
    return IntStream.of(arr);
  }

  @Override
  public <E> List<E> toList(Stream<E> stream) {
    return stream.collect(Collectors.toList());
  }

  @Override
  public List<Integer> toList(IntStream intStream) {
    return intStream.boxed().collect(Collectors.toList());
  }

  @Override
  public IntStream createIntStream(int start, int end) {
    return IntStream.range(start, end + 1);
  }

  @Override
  public DoubleStream squareRootIntStream(IntStream intStream) {
    return intStream.asDoubleStream().map(Math::sqrt);
  }

  @Override
  public IntStream getOdd(IntStream intStream) {
    return intStream.filter(i -> i % 2 == 1);
  }

  @Override
  public Consumer<String> getLambdaPrinter(String prefix, String suffix) {
    return null;
  }

  @Override
  public void printMessages(String[] messages, Consumer<String> printer) {

  }

  @Override
  public void printOdd(IntStream intStream, Consumer<String> printer) {

  }

  @Override
  public Stream<Integer> flatNestedInt(Stream<List<Integer>> ints) {
    return null;
  }
}

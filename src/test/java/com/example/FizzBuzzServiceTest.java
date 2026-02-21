package com.example;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@QuarkusTest
public class FizzBuzzServiceTest {

  @Inject FizzBuzzService service;

  @ParameterizedTest
  @MethodSource("convertTestCases")
  public void testConvert(int number, String fizz, String buzz, String expected) {
    assertEquals(expected, service.convert(number, fizz, buzz));
  }

  static Stream<Arguments> convertTestCases() {
    return Stream.of(
        Arguments.of(1, "Fizz", "Buzz", "1"),
        Arguments.of(2, "Fizz", "Buzz", "2"),
        Arguments.of(3, "Fizz", "Buzz", "Fizz"),
        Arguments.of(5, "Fizz", "Buzz", "Buzz"),
        Arguments.of(6, "Fizz", "Buzz", "Fizz"),
        Arguments.of(10, "Fizz", "Buzz", "Buzz"),
        Arguments.of(15, "Fizz", "Buzz", "FizzBuzz"),
        Arguments.of(30, "Fizz", "Buzz", "FizzBuzz"),
        Arguments.of(3, "Foo", "Bar", "Foo"),
        Arguments.of(5, "Foo", "Bar", "Bar"),
        Arguments.of(15, "Foo", "Bar", "FooBar"));
  }

  @ParameterizedTest
  @MethodSource("validateStartEndTestCases")
  public void testValidateStartEnd(int start, int end, boolean isValid) {
    if (isValid) {
      assertDoesNotThrow(() -> service.validateStartEnd(start, end));
    } else {
      assertThrows(IllegalArgumentException.class, () -> service.validateStartEnd(start, end));
    }
  }

  static Stream<Arguments> validateStartEndTestCases() {
    return Stream.of(
        Arguments.of(0, 100, false),
        Arguments.of(-1, 100, false),
        Arguments.of(1, 0, false),
        Arguments.of(1, -1, false),
        Arguments.of(10, 5, false),
        Arguments.of(1, 1, true),
        Arguments.of(20, 30, true));
  }
}

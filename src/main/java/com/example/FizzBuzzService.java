package com.example;

import jakarta.enterprise.context.Dependent;

@Dependent
public class FizzBuzzService {

  void print(int start, int end, String fizz, String buzz) {
    validateStartEnd(start, end);
    for (int i = start; i <= end; i++) {
      System.out.println(convert(i, fizz, buzz));
    }
  }

  String convert(int number, String fizz, String buzz) {
    if (number % 3 == 0 && number % 5 == 0) {
      return fizz + buzz;
    } else if (number % 3 == 0) {
      return fizz;
    } else if (number % 5 == 0) {
      return buzz;
    } else {
      return Integer.toString(number);
    }
  }

  boolean validateStart(int start) {
    return start > 0;
  }

  boolean validateEnd(int end) {
    return end > 0;
  }

  void validateStartEnd(int start, int end) {
    if (!validateStart(start) || !validateEnd(end) || !(start <= end)) {
      throw new IllegalArgumentException(
          "Invalid start and end values. Start must be > 0, end must be > 0, and start must be <="
              + " end.");
    }
  }
}

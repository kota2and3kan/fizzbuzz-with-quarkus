package com.example;

import picocli.CommandLine;

public class FizzBuzzOptions {

  @CommandLine.Option(
      names = {"-s", "--start"},
      description = "Start number. Must be greater than 0.",
      required = false,
      defaultValue = "1")
  int start;

  @CommandLine.Option(
      names = {"-e", "--end"},
      description = "End number. Must be greater than 0 and greater than or equal to start.",
      required = false,
      defaultValue = "100")
  int end;

  @CommandLine.Option(
      names = {"-f", "--fizz"},
      description = "Output for multiples of 3",
      required = false,
      defaultValue = "Fizz")
  String fizz;

  @CommandLine.Option(
      names = {"-b", "--buzz"},
      description = "Output for multiples of 5",
      required = false,
      defaultValue = "Buzz")
  String buzz;
}

package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.quarkus.test.junit.main.Launch;
import io.quarkus.test.junit.main.LaunchResult;
import io.quarkus.test.junit.main.QuarkusMainLauncher;
import io.quarkus.test.junit.main.QuarkusMainTest;
import org.junit.jupiter.api.Test;

@QuarkusMainTest
public class FizzBuzzCommandTest {

  @Test
  public void testBasicLaunch(QuarkusMainLauncher launcher) {
    LaunchResult result = launcher.launch();
    assertTrue(result.getOutput().lines().anyMatch("1"::equals), result.getOutput());
    assertTrue(result.getOutput().lines().anyMatch("2"::equals), result.getOutput());
    assertTrue(result.getOutput().lines().anyMatch("Fizz"::equals), result.getOutput());
    assertTrue(result.getOutput().lines().anyMatch("4"::equals), result.getOutput());
    assertTrue(result.getOutput().lines().anyMatch("Buzz"::equals), result.getOutput());
    assertTrue(result.getOutput().lines().anyMatch("FizzBuzz"::equals), result.getOutput());
    assertEquals(0, result.exitCode());
  }

  @Test
  @Launch(
      value = {"--start=10"},
      exitCode = 0)
  public void testLaunchWithStart(LaunchResult result) {
    assertFalse(result.getOutput().lines().anyMatch("1"::equals), result.getOutput());
    assertFalse(result.getOutput().lines().anyMatch("8"::equals), result.getOutput());
    assertTrue(result.getOutput().lines().anyMatch("Buzz"::equals), result.getOutput());
    assertTrue(result.getOutput().lines().anyMatch("11"::equals), result.getOutput());
    assertTrue(result.getOutput().lines().anyMatch("Fizz"::equals), result.getOutput());
  }

  @Test
  @Launch(
      value = {"--end=15"},
      exitCode = 0)
  public void testLaunchWithEnd(LaunchResult result) {
    assertTrue(result.getOutput().lines().anyMatch("1"::equals), result.getOutput());
    assertTrue(result.getOutput().lines().anyMatch("2"::equals), result.getOutput());
    assertTrue(result.getOutput().lines().anyMatch("Fizz"::equals), result.getOutput());
    assertTrue(result.getOutput().lines().anyMatch("4"::equals), result.getOutput());
    assertTrue(result.getOutput().lines().anyMatch("Buzz"::equals), result.getOutput());
    assertFalse(result.getOutput().lines().anyMatch("16"::equals), result.getOutput());
  }

  @Test
  @Launch(
      value = {"--fizz=Foo"},
      exitCode = 0)
  public void testLaunchWithCustomFizz(LaunchResult result) {
    assertTrue(result.getOutput().lines().anyMatch("Foo"::equals), result.getOutput());
    assertFalse(result.getOutput().lines().anyMatch("Fizz"::equals), result.getOutput());
  }

  @Test
  @Launch(
      value = {"--buzz=Bar"},
      exitCode = 0)
  public void testLaunchWithCustomBuzz(LaunchResult result) {
    assertTrue(result.getOutput().lines().anyMatch("Bar"::equals), result.getOutput());
    assertFalse(result.getOutput().lines().anyMatch("Buzz"::equals), result.getOutput());
  }

  @Test
  @Launch(
      value = {"--start=-1"},
      exitCode = 2)
  public void testLaunchWithInvalidStart(LaunchResult result) {
    assertTrue(
        result
            .getErrorOutput()
            .lines()
            .anyMatch(line -> line.contains("Invalid start and end values.")),
        result.getErrorOutput());
  }

  @Test
  @Launch(
      value = {"--end=-1"},
      exitCode = 2)
  public void testLaunchWithInvalidEnd(LaunchResult result) {
    assertTrue(
        result
            .getErrorOutput()
            .lines()
            .anyMatch(line -> line.contains("Invalid start and end values.")),
        result.getErrorOutput());
  }

  @Test
  @Launch(
      value = {"--start=10", "--end=5"},
      exitCode = 2)
  public void testLaunchWithStartGreaterThanEnd(LaunchResult result) {
    assertTrue(
        result
            .getErrorOutput()
            .lines()
            .anyMatch(line -> line.contains("Invalid start and end values.")),
        result.getErrorOutput());
  }
}

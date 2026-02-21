package com.example;

import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(name = "fizzbuzz", mixinStandardHelpOptions = true)
public class FizzBuzzCommand implements Runnable {

  @CommandLine.Mixin private FizzBuzzOptions options;

  @CommandLine.Spec CommandLine.Model.CommandSpec spec;

  private final FizzBuzzService fizzBuzzService;

  public FizzBuzzCommand(FizzBuzzService fizzBuzzService) {
    this.fizzBuzzService = fizzBuzzService;
  }

  @Override
  public void run() {
    try {
      fizzBuzzService.print(options.start, options.end, options.fizz, options.buzz);
    } catch (IllegalArgumentException e) {
      throw new CommandLine.ParameterException(spec.commandLine(), e.getMessage());
    }
  }
}

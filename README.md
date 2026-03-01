# FizzBuzz with Quarkus

This is a simple FizzBuzz command created by using [Quarkus](https://quarkus.io/).

## Install

You can download a native binary (executable file) from [releases](https://github.com/kota2and3kan/fizzbuzz-with-quarkus/releases).

## Build

### Native Image

```shell
./gradlew build
```

> Note: Native image build is enabled by default in [`application.properties`](./src/main/resources/application.properties). If you have changed this setting, add `-Dquarkus.native.enabled=true` to the command.

### Uber Jar

```shell
./gradlew build \
  -Dquarkus.native.enabled=false \
  -Dquarkus.package.jar.enabled=true \
  -Dquarkus.package.jar.type=uber-jar
```

## Usage

```shell
$ fizzbuzz --help
Usage: fizzbuzz [-hV] [-b=<buzz>] [-e=<end>] [-f=<fizz>] [-s=<start>]
  -b, --buzz=<buzz>     Output for multiples of 5
  -e, --end=<end>       End number. Must be greater than 0 and greater than or
                          equal to start.
  -f, --fizz=<fizz>     Output for multiples of 3
  -h, --help            Show this help message and exit.
  -s, --start=<start>   Start number. Must be greater than 0.
  -V, --version         Print version information and exit.
```

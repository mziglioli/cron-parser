# Getting Started

## Description
command line application which parses a cron string and expands each field
to show the times at which it will run.

## Dependencies
* gradle
* java 13

## how to test it
1- build:
 ```
./gradlew build
 ```
2- run:
 ```
./gradlew test
 ```
## how to run it

1- build:
 ```
./gradlew build
 ```
2- run:
 ```
./gradlew bootRun -Pargs="*/15 0 1,15 * 1-5 /usr/bin/find"
 ```
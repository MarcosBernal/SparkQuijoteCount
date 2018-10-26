# Overview

Couple of lines in spark to read from quijote.txt and count number of *Quijote* word occurrencies.

It saves the expected result: **2167** in the relative path: **occurences_quijote/csv/\*.csv**.

## Files

- [src/main/scala/com/example/QuijoteCount.scala](https://github.com/MarcosBernal/SparkQuijoteCount/blob/master/src/main/scala/com/example/QuijoteCount.scala): contains the lines of the word count program
- quijote.txt: Book extracted from [http://www.gutenberg.org](http://www.gutenberg.org/cache/epub/2000/pg2000.txt)
- target/scala-2.11: contains compiled jar after running `sbt package`



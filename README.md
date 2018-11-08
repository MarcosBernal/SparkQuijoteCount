# Overview

Couple of lines in spark to read from quijote.txt and count number of *Quijote* word occurrencies.

It saves the expected result: **2167** in the relative path: **occurences_quijote/csv/\*.csv**.

# Use

Local execution:
- `spark-submit --master local --class com.example.QuijoteCount target/scala-2.11/sparkquijotecount_2.11-1.0.jar`

It reads from the relative path from the default configured file system:
- in case of local fs: **./quijote.txt**
- in case of HDFS: **hdfs:///\<defaultPath\>/quijote.txt**

The results will be stored in:
- in case of local fs: **./occurences_quijote/csv/\*.csv**
- in case of HDFS: **hdfs:///\<defaultPath\>/occurences_quijote/csv/\*.csv**

Additionally two parameters can be given to specify different paths. For instance with this call:
- `spark-submit --master <MASTER_URL> --class com.example.QuijoteCount <YOUR_PATH>/sparkquijotecount_2.11-1.0.jar <YOUR_PATH>/quijote.txt <YOUR_DESTINY_PATH>`

## Files

- [src/main/scala/com/example/QuijoteCount.scala](https://github.com/MarcosBernal/SparkQuijoteCount/blob/master/src/main/scala/com/example/QuijoteCount.scala): contains the lines of the word count program
- quijote.txt: Book extracted from [http://www.gutenberg.org](http://www.gutenberg.org/cache/epub/2000/pg2000.txt)
- target/scala-2.11: contains compiled jar after running `sbt package`



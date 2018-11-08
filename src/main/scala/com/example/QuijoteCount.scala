package com.example

import org.apache.spark.sql._
import org.apache.spark.sql.SparkSession

object QuijoteCount {
  def main(args: Array[String]) {

    val inputFile = if (args.length != 2) "quijote.txt" else args(0)
    val outputFolder = if (args.length != 2) "occurences_quijote" else args(1)

    val spark = SparkSession.builder.getOrCreate
    import spark.implicits._

    val quijoteDS: Dataset[String] = spark.read.textFile(inputFile) // Optimal form spark.read.textFile(inputFile).flatMap(_.split("[\\W]+")).filter(_.matches("Quijote")).count
    val quijoteWords: Dataset[String] = quijoteDS.flatMap(_.split("[\\W]+")).filter(_.matches("[\\w]+")) // \W matches all NON alphanumeric and underscore chars
    val quijoteWordOccurrences: Long = quijoteWords.filter(_.matches("Quijote")).count                   // \w matches all alphanumeric and underscore chars

    val quijoteDF: DataFrame = Seq(quijoteWordOccurrences).toDF 
    quijoteDF.coalesce(1).write.save(outputFolder + "/parquet")
    quijoteDF.coalesce(1).write.format("csv").save(outputFolder + "/csv")
  }
}

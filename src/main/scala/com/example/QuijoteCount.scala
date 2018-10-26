package com.example

import org.apache.spark.sql._
import org.apache.spark.sql.SparkSession

object QuijoteCount {
  def main(args: Array[String]) {
    val spark = SparkSession.builder.getOrCreate
    import spark.implicits._

    val quijoteDS: Dataset[String] = spark.read.textFile("quijote.txt")
    val quijoteWords: Dataset[String] = quijoteDS.flatMap(_.split("[\\W]+")).filter(_.matches("[\\w]+")) // \W matches all NON alphanumeric and underscore chars
    val quijoteWordOccurrences: Long = quijoteWords.filter(_.matches("Quijote")).count                   // \w matches all alphanumeric and underscore chars

    val quijoteDF: DataFrame = Seq(quijoteWordOccurrences).toDF 
    quijoteDF.coalesce(1).write.save("occurences_quijote/parquet")
    quijoteDF.coalesce(1).write.format("csv").save("occurences_quijote/csv")
  }
}

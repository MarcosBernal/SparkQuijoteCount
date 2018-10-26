package com.example

import org.apache.spark.sql._
import org.apache.spark.sql.SparkSession

object QuijoteCount {
  def main(args: Array[String]) {
    val spark = SparkSession.builder.getOrCreate
    import spark.implicits._

    val quijoteDS: Dataset[String] = spark.read.textFile("quijote.txt")
    val quijoteWords: Dataset[String] = quijoteDS.flatMap(_.split("[\\W]+")).filter(_.matches("[\\w]+"))
    val quijoteWordOccurrences: Long = quijoteWords.filter(_.matches("Quijote")).count

    val quijoteDF: DataFrame = Seq(quijoteWordOccurrences).toDF 
    quijoteDF.write.save("/tmp/OccurencesOfQuijote/parquet")
    quijoteDF.write.format("csv").save("/tmp/OccurencesOfQuijote/csv")
  }
}

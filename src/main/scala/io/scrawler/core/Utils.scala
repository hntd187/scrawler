package io.scrawler.core

import scala.collection.JavaConverters._

private[scrawler] object Utils {
  def getSystemProperties: Map[String, String] = {
    (for ((k, v) <- System.getProperties.asScala) yield {
      (k: String, v: String)
    }).toMap
  }
}
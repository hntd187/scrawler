package io.scrawler.core

import scala.collection.JavaConversions._

private[scrawler] object Utils {
  def getSystemProperties: Map[String, String] = {
    val props = for ((k, v) <- System.getProperties) yield (k, v)
    props.toMap
  }
}
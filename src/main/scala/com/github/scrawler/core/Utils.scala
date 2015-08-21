package com.github.scrawler.core

import scala.collection.JavaConversions._

private[scrawler] object Utils {
  def getSystemProperties: Map[String, String] = {
    val props = for(key <- System.getProperties.stringPropertyNames()) yield (key, System.getProperty(key))
    props.toMap
  }
}
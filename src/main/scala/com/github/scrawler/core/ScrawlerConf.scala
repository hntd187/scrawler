package com.github.scrawler.core

import java.io.File
import java.io.FileInputStream
import java.util.Properties
import java.util.concurrent.ConcurrentHashMap

class ScrawlerConf(val props : File = new File("scrawler.properties")) {
  val properties = new Properties()
  properties.load(new FileInputStream(props))

  def this(props : String) = {
    this(new File(props))
  }
  
  def set(key : String, value : String): ScrawlerConf = {
    if (key == null) throw new NullPointerException("Null key value")
    if (value == null) throw new NullPointerException("Null value for key: " + key)
    properties.put(key, value)
    this
  }
  
  def get(key : String): String = {
    if (properties.containsKey(key)) properties.getProperty(key) else throw new NoSuchElementException(key)
  }
}
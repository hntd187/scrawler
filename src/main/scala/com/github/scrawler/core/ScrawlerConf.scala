package com.github.scrawler.core

import java.util.concurrent.ConcurrentHashMap
import scala.collection.JavaConversions._

private[scrawler] class ScrawlerConf() extends Logging{
  
  private val settings = new ConcurrentHashMap[String, String]()
  
  // Load any options passed via SCRAWLER_OPTS
  if (System.getenv.keySet.contains("SCRAWLER_OPTS")){
    System.getenv.get("SCRAWLER_OPTS")
      .split(",")
      .filter(o => o.startsWith("scrawler."))
      .foreach { o => 
        info(s"Loading option: $o")
        val option = o.split("=")
        set(option(0), option(1)) 
    }   
  }
  // Load any options passed via System Properties
  Utils.getSystemProperties
    .filter( p  => p._1.startsWith("scrawler."))
    .foreach{p  => 
      info(s"Loading Option: $p")
      set(p._1, p._2)
    }
  
  def set(key: String, value: String): ScrawlerConf = {
    if (key == null){
      throw new NullPointerException("Null key")
    }
    if (value == null){
      throw new NullPointerException(s"Null value for $key")
    }
    settings.put(key, value)
    this 
  }
  
  def get(key: String, default: String): String = {
    settings.getOrDefault(key, default)
  }
}
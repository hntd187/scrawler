package com.github.scrawler.job

import java.security.MessageDigest
import org.slf4j.{LoggerFactory, Logger}
import scala.collection.JavaConversions._

class CrawlJob(var jobName : String, var urls : List[String]) extends Job{
    private val jobId  : String = MessageDigest.getInstance("MD5").digest(jobName.getBytes).toString()
    private val logger : Logger = LoggerFactory.getLogger(getClass)
    private var crawled : List[String] = List()
    
    def updateUrls(urls : List[String]): Unit = {
      this.crawled.addAll(urls)
      this.crawled = this.crawled.distinct
      this.urls.addAll(urls)
      this.urls.filter(f => !this.crawled.contains(f))
    }
}
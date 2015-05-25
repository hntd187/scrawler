package com.github.scrawler.job

import java.security.MessageDigest
import org.slf4j.{LoggerFactory, Logger}

class CrawlJob(var jobName : String, val urls : Array[String]) extends Job{
    private val jobId  : String = MessageDigest.getInstance("MD5").digest(jobName.getBytes).toString()
    private val logger : Logger = LoggerFactory.getLogger(getClass)
}
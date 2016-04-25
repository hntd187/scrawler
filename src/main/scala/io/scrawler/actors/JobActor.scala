package io.scrawler.actors

import io.scrawler.core.Logging
import io.scrawler.job.CrawlJob
import io.scrawler.messages.{ParseHtmlMessage, UpdateJobMessage}

import akka.actor.{Actor, Props}

import scala.collection.mutable

class JobActor extends Actor with Logging {
  val parserActors = context.actorOf(Props(classOf[ParseHtmlActor]))
  val crawlJobs = mutable.MutableList.empty[CrawlJob]

  def receive = {
    case msg: CrawlJob =>
      crawlJobs += msg
      msg.urls.foreach(u => parserActors ! new ParseHtmlMessage(msg.jobId, u.baseUri))

    case msg: UpdateJobMessage =>
      val jobId = msg.jobId
      crawlJobs.filter(cj => cj.jobId == jobId).foreach(f => f.crawledUrls(msg.urls.toSet))

  }
}

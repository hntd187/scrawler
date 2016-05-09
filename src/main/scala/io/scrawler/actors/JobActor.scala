package io.scrawler.actors

import scala.collection.immutable

import akka.actor.{Actor, ActorLogging, Props}
import io.scrawler.core.ExtractedLink
import io.scrawler.messages.Message

case class UpdateJobMessage(jobId: String, urls: immutable.Set[ExtractedLink]) extends Message

case class CrawlJob(jobId: String, urls: immutable.Set[String])

class JobActor extends Actor with ActorLogging {
  val parserActors = context.actorOf(ParseHtmlActor())


  def receive = {
    case msg: CrawlJob =>
      log.info("Got Crawl job message")
      msg.urls.foreach(u => parserActors ! ParseHtmlMessage(msg.jobId, u))

    case msg: UpdateJobMessage =>
      log.info("Got Update job message")
      val jobId = msg.jobId

  }
}

object JobActor {
  def apply(args: Any*) = Props(classOf[JobActor], args)
}

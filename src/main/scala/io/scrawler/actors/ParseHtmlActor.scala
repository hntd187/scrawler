package io.scrawler.actors

import akka.actor._
import io.scrawler.core.Parser
import io.scrawler.messages.Message

case class ParseHtmlMessage(jobId: String, url: String) extends Message

class ParseHtmlActor extends Actor with ActorLogging {

  def receive = {
    case msg: ParseHtmlMessage =>
      log.info(f"Extracting links from: ${msg.url}")
      val links = Parser.parseURL(msg.url)
      sender() ! UpdateJobMessage(msg.jobId, links.toSet)
  }
}

object ParseHtmlActor {
  def apply(args: Any*) = Props(classOf[ParseHtmlActor], args)
}
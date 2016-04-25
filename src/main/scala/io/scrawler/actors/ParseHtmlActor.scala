package io.scrawler.actors

import io.scrawler.core.Parser
import io.scrawler.messages._

import akka.actor._
import org.apache.tika.sax._

import scala.collection.mutable

class ParseHtmlActor extends Actor with ActorLogging {

  def receive = {
    case msg: ParseHtmlMessage =>
      log.info(f"Extracting links from: ${msg.url}")
      val links = Parser.jsoupParse(msg.url)
      sender() ! UpdateJobMessage(msg.jobId, links.toArray)
  }
}

object ParseHtmlActor {
  def extractUrls(links: mutable.Buffer[Link]): Array[String] = {
    links.map(l => l.getUri).toArray
  }
}
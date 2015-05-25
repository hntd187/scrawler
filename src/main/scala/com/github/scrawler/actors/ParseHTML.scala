package com.github.scrawler.actors

import java.net.URL
import org.apache.tika.parser.html.HtmlParser
import org.apache.tika.sax.{BodyContentHandler, LinkContentHandler, TeeContentHandler, ToHTMLContentHandler}
import org.apache.tika.parser.ParseContext
import org.apache.tika.metadata.Metadata
import org.slf4j.{Logger, LoggerFactory}
import com.github.scrawler.messages.ParseHTMLMessage
import akka.actor.Actor

class ParseHTML extends Actor{
  private val logger : Logger = LoggerFactory.getLogger(getClass)
  def receive = {
    case msg: ParseHTMLMessage => {
      logger.info(msg.url)
      val parser = new HtmlParser
      val links = new LinkContentHandler
      val content = parser.parse(new URL(msg.url).openStream(), new TeeContentHandler(links, new BodyContentHandler, new ToHTMLContentHandler), new Metadata, new ParseContext)
      sender ! links.getLinks
    }
    case _ => logger.info("Recieved unknown message: {}", this)
  }

}
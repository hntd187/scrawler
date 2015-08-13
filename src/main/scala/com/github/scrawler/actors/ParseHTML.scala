package com.github.scrawler.actors

import java.net.URL
import java.util.List

import scala.collection.JavaConversions._

import org.apache.tika.metadata.Metadata
import org.apache.tika.parser.ParseContext
import org.apache.tika.parser.html.HtmlParser
import org.apache.tika.sax.{BodyContentHandler, Link, LinkContentHandler, TeeContentHandler, ToHTMLContentHandler}
import org.slf4j.{Logger, LoggerFactory}

import com.github.scrawler.messages.{ParseHTMLMessage, UpdateJobMessage}

import akka.actor.{Actor, Props, actorRef2Scala}

import com.github.scrawler.messages.{ParseHTMLMessage, UpdateJobMessage}

class ParseHTML extends Actor{
  private val logger : Logger = LoggerFactory.getLogger(getClass)
  def receive = {
    case msg: ParseHTMLMessage => {
      logger.info(msg.url)
      val parser = new HtmlParser
      val links = new LinkContentHandler
      val content = parser.parse(new URL(msg.url).openStream(), new TeeContentHandler(links, new BodyContentHandler, new ToHTMLContentHandler), new Metadata, new ParseContext)
      sender ! new UpdateJobMessage(ParseHTML.extractUrls(links.getLinks))
    }
    case _ => logger.warn("Recieved unknown message: {}", this)
  }
}

object ParseHTML{
  def props(args : String) = Props(classOf[ParseHTML], args)
  
  def extractUrls(links : List[Link]): Array[String] = {
    links.map(l => l.getUri).toArray
  }
}
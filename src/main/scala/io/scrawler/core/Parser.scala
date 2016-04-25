package io.scrawler.core

import java.io.InputStream

import org.apache.tika.metadata.Metadata
import org.apache.tika.parser.AutoDetectParser
import org.apache.tika.sax._
import org.jsoup.Jsoup

import scala.collection.JavaConverters._
import scala.collection.mutable
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

case class ExtractedLink(baseUri: String, text: String) extends Serializable

object Parser {
  private val parser = new AutoDetectParser()
  private val links = new LinkContentHandler()
  private val metadata = new Metadata()

  def jsoupParse(url: String): mutable.Buffer[ExtractedLink] = {
    val doc = Jsoup.connect(url).get()

    val elements = doc.select("a[href]").asScala.map(ele => ExtractedLink(ele.attr("abs:href"), ele.text()))
    elements
    //Future(Jsoup.connect(url).get()).map(d => d.select("a[href]").asScala).map(_.map(ele => new ExtractedLink(ele.attr("abs:href"), ele.text())))
  }

  def parseUrl(stream: InputStream): Future[mutable.Buffer[Link]] = {
    Future(parser.parse(stream, links, metadata)).map(f => links.getLinks.asScala)
  }
}

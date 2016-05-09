package io.scrawler.core

import scala.collection.JavaConverters._
import scala.collection.mutable

import org.jsoup.Jsoup

case class ExtractedLink(baseUri: String, text: String) extends Serializable

object Parser {
  def parseURL(url: String): mutable.Buffer[ExtractedLink] = {
    val doc = Jsoup.connect(url).get()
    doc.select("a[href]").asScala.map(ele => ExtractedLink(ele.attr("abs:href"), ele.text()))
  }
}

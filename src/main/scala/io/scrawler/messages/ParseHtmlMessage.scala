package io.scrawler.messages

import java.net.URL

case class ParseHtmlMessage(jobId: String, url: String) extends Message {

  val htmlUrl = new URL(url)
  val urlStream = htmlUrl.openStream()

  override def toString: String = {
    "[" + name + " = " + url + "]"
  }
}
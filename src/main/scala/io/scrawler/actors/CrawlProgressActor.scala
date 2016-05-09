package io.scrawler.actors

import scala.collection.concurrent.{Map, TrieMap}

import java.net.URL

import akka.actor._
import io.scrawler.actors.UrlStatuses.UrlStatuses

object UrlStatuses extends Enumeration {
  type UrlStatuses = Value
  val PENDING, INPROGRESS, FINISHED, ERROR = Value
}

case class UrlStatus(url: String)
case class UrlStatusResponse(url: String, status: UrlStatuses)
case class UpdateUrls(urls: Array[(URL, UrlStatuses)])
case class DomainStatus(domain: String)
case class DomainStatusResponse(domain: String, status: UrlStatuses)
case class CrawlReport(urls: Map[String, UrlStatuses], domains: Map[String, UrlStatuses])
case object CrawlReport

class CrawlProgressActor extends Actor with ActorLogging {

  val urls: Map[String, UrlStatuses] = new TrieMap[String, UrlStatuses].empty
  val domains: Map[String, UrlStatuses] = new TrieMap[String, UrlStatuses].empty

  override def receive = {
    case msg: UrlStatus    => sender() ! UrlStatusResponse(msg.url, urls(msg.url))
    case msg: UpdateUrls   => msg.urls.foreach(u => urls.putIfAbsent(u._1.toString, u._2))
    case msg: DomainStatus => sender() ! DomainStatusResponse(msg.domain, domains(msg.domain))
    case CrawlReport       => sender() ! CrawlReport(urls, domains)
  }
}

object CrawlProgressActor {
  def apply(args: Any*) = Props(classOf[CrawlProgressActor], args)
}

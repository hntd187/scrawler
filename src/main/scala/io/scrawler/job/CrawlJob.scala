package io.scrawler.job

import io.scrawler.core.{ExtractedLink, Logging}

import scala.collection.{immutable, mutable}

case class CrawlJob(jobName: String, urls: immutable.Set[ExtractedLink]) extends Job with Logging {
  val jobId: String = util.Random.alphanumeric.take(10).mkString
  private var crawled: mutable.Set[ExtractedLink] = mutable.Set.empty[ExtractedLink]

  def crawledUrls(urls: immutable.Set[ExtractedLink]) = crawled = crawled ++ urls

  def reportUrls: String = crawled.mkString(", \n")
}
package io.scrawler.core

import com.typesafe.config.{Config, ConfigFactory}

private[scrawler] class ScrawlerConf(conf: Config = ConfigFactory.load()) extends Logging {

  log.info(f"Config loaded from: ${conf.origin().filename()}")

  def get(key: String): String = conf.getString(key)
}
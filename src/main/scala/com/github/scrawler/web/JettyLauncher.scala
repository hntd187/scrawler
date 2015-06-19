package com.github.scrawler.web

import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.DefaultServlet
import org.eclipse.jetty.webapp.WebAppContext
import com.github.scrawler.core.ScrawlerConf

object JettyLauncher {
  def main(args: Array[String]) {
    val conf    = new ScrawlerConf()
    val server  = new Server(conf.get("scrawler.web.port").toInt)
    val context = new WebAppContext()
    context setContextPath(conf.get("scrawler.web.path"))
    context.setResourceBase(conf.get("scrawler.web.static"))
    context.addServlet(classOf[DefaultServlet], conf.get("scrawler.web.path"))
    server.setHandler(context)
    server.start
    server.join
  }
}
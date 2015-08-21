package com.github.scrawler.web

import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.DefaultServlet
import org.eclipse.jetty.webapp.WebAppContext
import com.github.scrawler.core.ScrawlerConf
import akka.actor.ActorSystem
import org.eclipse.jetty.server.ServerConnector
import com.github.scrawler.core.Logging

object JettyLauncher extends App with Logging{
    val conf       = new ScrawlerConf()
    val system     = ActorSystem("Scrawler-Actor-System")
    
    val webAppPath = JettyLauncher.getClass.getClassLoader.getResource("webapp").toString
    val port       = conf.get("scrawler.web.port", "8080").toInt
    
    val server  = new Server()
    val connector = new ServerConnector(server)
    
    info(s"Webapp port is $port")
    info(s"Webapp path is $webAppPath")
    
    connector.setPort(port)
    server.addConnector(connector)
    val context = new WebAppContext()
    context setContextPath(conf.get("scrawler.web.path","/"))
    context.setWar(webAppPath)
    server.setHandler(context)
    server.start
}
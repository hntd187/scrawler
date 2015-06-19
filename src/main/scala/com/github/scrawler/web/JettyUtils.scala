package com.github.scrawler.web

import org.eclipse.jetty.server.HttpConnectionFactory
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.server.ServerConnector
import org.eclipse.jetty.server.handler.ContextHandlerCollection
import org.eclipse.jetty.server.handler.ErrorHandler
import org.eclipse.jetty.servlet.ServletContextHandler
import org.eclipse.jetty.util.thread.QueuedThreadPool

import com.github.scrawler.core.ScrawlerConf

object JettyUtils {

  def startJettyServer(
      port: Int,
      handlers: Seq[ServletContextHandler],
      conf: ScrawlerConf,
      serverName: String = ""): ServerInfo = {

    val collection = new ContextHandlerCollection
    collection.setHandlers(handlers.toArray)
    
    val pool = new QueuedThreadPool(512)
    pool.setDaemon(true)
    
    val server = new Server(pool)
    server.manage(pool)
    server.setDumpAfterStart(false)
    server.setDumpBeforeStop(false)
    
    val errorHandler = new ErrorHandler()
    errorHandler.setShowStacks(true)
    server.addBean(errorHandler)
    server.setHandler(collection)
    
    val httpConfig = new HttpConnectionFactory()
    val connector = new ServerConnector(server, httpConfig)
    connector.setPort(port)
    connector.setName(serverName)
    connector.setIdleTimeout(conf.get("scrawler.web.timeout").toLong)
    server.addConnector(connector)
    
    try {
      server.start()
      server.join()
      new ServerInfo(server, port, collection)
    } catch {
      case e: Exception =>
        server.stop()
        pool.stop()
        throw e
    }
  }
}

case class ServerInfo(
    server : Server,
    port : Int, 
    root : ContextHandlerCollection)
package io.scrawler.core

import org.slf4j.{Logger, LoggerFactory}

private[scrawler] trait Logging {

  Runtime.getRuntime.addShutdownHook(new Thread() {
    override def run() {
      info(f"Shutting down $logName")
    }
  })

  private val logName: String = this.getClass.getName.stripSuffix("$")
  private val logger: Logger = LoggerFactory.getLogger(logName)

  protected def info(msg: => String) = logger.info(msg)
  protected def info(msg: => String, throwable: Throwable) = logger.info(msg, throwable)

  protected def warn(msg: => String) = logger.warn(msg)
  protected def warn(msg: => String, throwable: Throwable) = logger.warn(msg, throwable)

  protected def debug(msg: => String) = logger.debug(msg)
  protected def debug(msg: => String, throwable: Throwable) = logger.debug(msg, throwable)

  protected def error(msg: => String) = logger.error(msg)
  protected def error(msg: => String, throwable: Throwable) = logger.error(msg, throwable)

  protected def trace(msg: => String) = logger.trace(msg)
  protected def trace(msg: => String, throwable: Throwable) = logger.trace(msg, throwable)
}

package com.github.scrawler.core

import org.slf4j.{Logger, LoggerFactory}

private[scrawler] trait Logging {
  
  private var log_ : Logger = null
  
  protected def logName: String = {
     this.getClass.getName.stripSuffix("$")
  }
  
  protected def log: Logger = {
    if (log_ == null){
      log_ = LoggerFactory.getLogger(logName)
    }
    log_
  }
  
  protected def info(msg:  => String, throwable: Throwable = null) = logMsg(msg, "info", throwable)
  protected def warn(msg:  => String, throwable: Throwable = null) = logMsg(msg, "warn", throwable)
  protected def debug(msg: => String, throwable: Throwable = null) = logMsg(msg, "debug", throwable)
  protected def trace(msg: => String, throwable: Throwable = null) = logMsg(msg, "trace", throwable)
  protected def error(msg: => String, throwable: Throwable = null) = logMsg(msg, "error", throwable)
  
  
  private def logMsg(msg: => String, level: String = "info", throwable: Throwable){
    if (!levelEnabled(level)) return
    level.toLowerCase match {
      case "info"  => if (throwable != null) log.info(msg, throwable)  else log.info(msg)  
      case "warn"  => if (throwable != null) log.warn(msg, throwable)  else log.warn(msg)  
      case "debug" => if (throwable != null) log.debug(msg, throwable) else log.debug(msg) 
      case "trace" => if (throwable != null) log.trace(msg, throwable) else log.trace(msg)
      case "error" => if (throwable != null) log.error(msg, throwable) else log.error(msg) 
      case _       => logMsg("ERROR: Bad Logging Level!", "error", null)
    }
    
  }
  
  private def levelEnabled(level: String): Boolean = {
    level.toLowerCase match {
      case "info"  => log.isInfoEnabled
      case "warn"  => log.isWarnEnabled
      case "debug" => log.isDebugEnabled
      case "trace" => log.isTraceEnabled
      case "error" => log.isErrorEnabled
      case _ => false
    }
  }
}
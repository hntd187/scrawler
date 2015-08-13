package com.github.scrawler.messages

case class ParseHTMLMessage(val url : String) extends Message {

  override def toString(): String = {
    "[" + this.name + " = " + this.url + "]"
  }
}
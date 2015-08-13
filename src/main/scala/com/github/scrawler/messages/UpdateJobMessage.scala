package com.github.scrawler.messages;

case class UpdateJobMessage(val urls : Array[String]) extends Message {

  override def toString(): String = {
    this.name + " " + this.urls.mkString(",\n")
  }
}

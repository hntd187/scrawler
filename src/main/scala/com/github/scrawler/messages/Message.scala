package com.github.scrawler.messages

abstract class Message {
  def name = this.getClass.getName
}
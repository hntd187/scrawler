package io.scrawler.messages

abstract class Message {
  def name = getClass.getSimpleName
}
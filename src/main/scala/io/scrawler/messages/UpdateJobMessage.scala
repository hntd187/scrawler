package io.scrawler.messages

import io.scrawler.core.ExtractedLink

case class UpdateJobMessage(jobId: String, urls: Array[ExtractedLink]) extends Message {

  override def toString: String = {
    f"$name($jobId, $urls)"
  }
}

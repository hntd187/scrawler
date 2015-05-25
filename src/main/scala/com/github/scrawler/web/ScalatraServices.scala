package com.github.scrawler.web

import org.scalatra._
import scalate.ScalateSupport
import com.github.scrawler.job.CrawlJob

class ScalatraServices extends ScalatraServlet with ScalateSupport {
  get("/") {
    contentType="text/html"
    ssp("/WEB-INF/layouts/default.scaml", "title" -> "LOL", "headline" -> "Balls", "body" -> "LOL Webpage!")
  }
  get("/test"){
    <html>
			lol hi!
		</html>
  }
}

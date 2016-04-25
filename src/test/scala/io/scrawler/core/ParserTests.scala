package io.scrawler.core

import org.scalatest._

import scala.concurrent.duration._

class ParserTests extends FunSuite with Matchers {

  val u = "http://whobelyin.com"
  val expectedLinks = Array(
    ExtractedLink("http://www.politifact.com/personalities/bernie-s/", "Bern Doc Da Sand Man"),
    ExtractedLink("http://www.politifact.com/personalities/hillary-clinton/", "H-Clint"),
    ExtractedLink("http://www.politifact.com/personalities/donald-trump/", "Donnie T"),
    ExtractedLink("http://www.politifact.com/personalities/ted-cruz/", "Teddy Cruz Da Texas Law Firm"),
    ExtractedLink("http://www.politifact.com/personalities/john-kasich/", "Johnny Ka$h"),
    ExtractedLink("http://www.politifact.com/", "Politifact")
  )
  val p = Parser
  val parseTimeout: Duration = 20.seconds

  test("Should return Links") {
    val returnLinks = p.jsoupParse(u)
    returnLinks sameElements expectedLinks shouldBe true
    /*
    returnLinks.onComplete{
      case Success(s) => s sameElements expectedLinks shouldBe true
      case Failure(e) => throw e
    }
    */
  }

}

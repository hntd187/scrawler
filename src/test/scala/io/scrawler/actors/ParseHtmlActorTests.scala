package io.scrawler.actors

import io.scrawler.core.Parser
import io.scrawler.messages.{ParseHtmlMessage, UpdateJobMessage}

import akka.actor._
import akka.testkit.{ImplicitSender, TestKit}
import akka.util.Timeout
import org.scalatest._

import scala.concurrent.duration._

object ParseHtmlActorTests {
  val system = ActorSystem("Tests")
}

class ParseHtmlActorTests extends TestKit(ActorSystem("Test"))
  with ImplicitSender
  with FunSuiteLike
  with Matchers
  with BeforeAndAfterAll {

  implicit val timeout = Timeout(5.seconds)
  val url = "http://www.whobelyin.com"
  val expectedMsg = UpdateJobMessage("1", Parser.jsoupParse(url).toArray)

  override def afterAll: Unit = {
    TestKit.shutdownActorSystem(system)
  }

  test("Should respond with Update") {
    val actor: ActorRef = system.actorOf(Props(classOf[ParseHtmlActor]))
    actor ! ParseHtmlMessage("1", url)
    expectMsgClass(classOf[UpdateJobMessage])
    actor ! ParseHtmlMessage("1", url)
    val result = expectMsgClass(classOf[UpdateJobMessage])
    result.urls sameElements expectedMsg.urls shouldBe true
    result.jobId shouldBe "1"
  }

}

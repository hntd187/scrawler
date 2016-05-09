package io.scrawler.actors

import scala.concurrent.duration._

import akka.actor._
import akka.testkit.{ImplicitSender, TestKit}
import akka.util.Timeout
import io.scrawler.core.Parser
import org.scalatest._

class ParseHtmlActorTests extends TestKit(ActorSystem("Test"))
                                  with ImplicitSender
                                  with FunSuiteLike
                                  with Matchers
                                  with BeforeAndAfterAll {

  implicit val timeout = Timeout(5.seconds)
  val url = "http://www.whobelyin.com"
  val expectedMsg = UpdateJobMessage("1", Parser.parseURL(url).toSet)

  override def afterAll: Unit = {
    TestKit.shutdownActorSystem(system)
  }

  test("Should respond with Update") {
    val actor: ActorRef = system.actorOf(Props(classOf[ParseHtmlActor]))
    actor ! ParseHtmlMessage("1", url)
    expectMsgClass(classOf[UpdateJobMessage])
    actor ! ParseHtmlMessage("1", url)
    val result = expectMsgClass(classOf[UpdateJobMessage])
    result.urls == expectedMsg.urls shouldBe true
    result.jobId shouldBe "1"
  }

}

```scala
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.duration._
import scala.util.{Failure, Success}

class MyService(implicit ec: ExecutionContext) {
  def myMethod(): Future[Int] = {
    Future {
      // Simulate a long-running computation
      Thread.sleep(5000)
      42
    }
  }
}

object Main extends App {
  implicit val ec = scala.concurrent.ExecutionContext.global
  val service = new MyService()

  // Solution 1: Using Await.result (blocks until the Future completes)
  val result = Await.result(service.myMethod(), 10.seconds)
  println(s"Result: $result")

  // Solution 2: Handling the Future with callbacks (preferred for complex scenarios)
  service.myMethod().onComplete {
    case Success(value) => println(s"Success (callback): $value")
    case Failure(exception) => println(s"Failure (callback): ${exception.getMessage}")
  }

  //Keep the main thread alive (If you need to keep the program running to process other tasks)  
  Thread.sleep(6000)
  println("Exiting...")
}
```
```scala
import scala.concurrent.{ExecutionContext, Future}

class MyService(implicit ec: ExecutionContext) {
  def myMethod(): Future[Int] = {
    Future {
      // Some long-running computation
      Thread.sleep(5000)
      42
    }
  }
}

// Incorrect usage, this might hang the application
object Main extends App {
  val service = new MyService()
  service.myMethod().onComplete {
    case scala.util.Success(value) => println(s"Success: $value")
    case scala.util.Failure(exception) => println(s"Failure: ${exception.getMessage}")
  }
  // The program exits before the future completes!
  println("Exiting...")
}
```
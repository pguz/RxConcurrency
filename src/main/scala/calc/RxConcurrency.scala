package calc

import scala.concurrent.Future
import scala.concurrent.duration.Duration
import scala.util.{Success, Failure}
import rx.lang.scala.Observable
import java.math.BigDecimal
import scala.io.StdIn.{readBoolean}
import scala.concurrent.ExecutionContext.Implicits.global

object RxConcurrency extends App with ThreadLogger {

	val NUM_DIGITS: Int = 50000
    val piCalc = new PiCalc(NUM_DIGITS)

    val obsStatus: Observable[Double] = Observable
      .interval(Duration(0.5, scala.concurrent.duration.SECONDS))
      .map(_ => piCalc.getStatus)
      
    val subStatus = obsStatus.subscribe {
        status => log("status: " + status)
    }

    val f : Future[BigDecimal] = Future {piCalc.compute()}
    f.onComplete {
        case Success(result) =>
			subStatus.unsubscribe()
            log("Computation result: " + result);
        case Failure(e) =>
			subStatus.unsubscribe()
            error("Error is: " + e.getMessage)
    }

    log("Computation starts ")

    while(!readBoolean()) {
        log("Do you want exit?")
    }

    log("Thank You!")
}


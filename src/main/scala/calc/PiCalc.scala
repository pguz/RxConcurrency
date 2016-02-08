package calc

import java.math.BigDecimal

object PiCalc {
  val FOUR: BigDecimal = BigDecimal.valueOf(4)
  val ROUNDING_MODE = BigDecimal.ROUND_HALF_DOWN
  //val DIGITS: Int = 50000
  val MAX_I: Int = 46275
}

class PiCalc(digits: Int) extends Calc{
  import PiCalc._

  var i: Int = 1
  override def getStatus(): Double = (math rint ((i * 100.0) / MAX_I) * 100) / 100


  override def compute(): BigDecimal = {

    val arctan1_5: BigDecimal = arctan(5);
    val arctan1_239: BigDecimal = arctan(239);
    val pi: BigDecimal = arctan1_5.multiply(FOUR).subtract(arctan1_239).multiply(FOUR);

    pi.setScale(digits, BigDecimal.ROUND_HALF_UP);
  }

  def arctan(inverseX: Int): BigDecimal = {
    var result: BigDecimal = BigDecimal.valueOf(0);

    var term: BigDecimal = BigDecimal.ZERO
    var numer: BigDecimal =  BigDecimal.ONE
    var invX: BigDecimal = BigDecimal.valueOf(inverseX);
    var invX2: BigDecimal = BigDecimal.valueOf(inverseX * inverseX);

    numer = BigDecimal.ONE.divide(invX, digits, ROUNDING_MODE);
    result = numer;

    do {
      numer = numer.divide(invX2, digits, ROUNDING_MODE);

      val denom = 2 * i + 1
      term = (numer.divide(BigDecimal.valueOf(denom),
        digits, ROUNDING_MODE))
      if ((i % 2) != 0) {
        result = result.subtract(term)
      } else {
        result = result.add(term)
      }
      i = i + 1

    } while (term.compareTo(BigDecimal.ZERO) != 0)
    result
  }

}

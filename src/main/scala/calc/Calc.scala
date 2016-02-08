package calc

import java.math.BigDecimal

trait Calc {
  def compute(): BigDecimal
  def getStatus(): Double
}

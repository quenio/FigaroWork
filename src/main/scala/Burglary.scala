import com.cra.figaro.language._
import com.cra.figaro.library.compound._
import com.cra.figaro.algorithm.factored._

object Burglary
{
  def main(args: Array[String])
	{
    // Model:
    val burglary = Flip(0.01)
    val earthquake = Flip(0.0001)
    val alarm = CPD(
      burglary, earthquake,
      (false, false) -> Flip(0.001),
      (false, true) -> Flip(0.1),
      (true, false) -> Flip(0.9),
      (true, true) -> Flip(0.99)
    )
    val johnCalls = CPD(
      alarm,
      false -> Flip(0.01),
      true -> Flip(0.7)
    )

    // Evidence:
    johnCalls.observe(true)

    // Algorithm:
    val algorithm = VariableElimination(burglary, earthquake)
    algorithm.start()

    // Queries:
    println
    println("Given John called, the probability of burglary is:")
    println(algorithm.probability(burglary, true))
    println
    println("Given John called, the probability of earthquake is:")
    println(algorithm.probability(earthquake, true))
    println
  }
}

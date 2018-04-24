/*
 * Test.scala
 * A simple Figaro test program.
 *
 * Created By:      Michael Reposa (mreposa@cra.com)
 * Creation Date:   Aug 6, 2014
 *
 * Copyright 2013 Avrom J. Pfeffer and Charles River Analytics, Inc.
 * See http://www.cra.com or email figaro@cra.com for information.
 *
 * See http://www.github.com/p2t2/figaro for a copy of the software license.
 */

import com.cra.figaro.language._
import com.cra.figaro.algorithm.sampling._
import com.cra.figaro.algorithm.factored._

object VarElimTest
{
  def main(args: Array[String])
	{
    val test = Select(0.8 -> "Hello", 0.2 -> "Goodbye")

    val varElim = VariableElimination(test)
    varElim.start()
    println(varElim.probability(test, "Hello"))
    println(varElim.probability(test, "Goodbye"))

    val importance = Importance(10000, test)
    importance.start()
    println(importance.probability(test, "Hello"))
    println(importance.probability(test, "Goodbye"))
  }
}

import java.lang

import scala.annotation.tailrec

object zad1 {
  def main(args: Array[String]): Unit = {
    val days = List("poniedziałek", "wtorek", "środa", "czwartek", "piątek", "sobota", "niedziela")
    daysSeparatedByComma(days)
    daysSeparatedByCommaStartingWithP(days)
    daysSeparatedByCommaWhileLoop(days)
    println("Zad. 2a: " + daysSeparatedByCommaRecursion(days).dropRight(2))
    println("Zad. 2b: " + daysSeparatedByCommaRecursionReversed(days).dropRight(2))
    println("Zad. 3: " + daysSeparatedByCommaTailRecursion(days).drop(2))
    daysSeparatedByCommaFoldLeft(days)
    daysSeparatedByCommaFoldRight(days)
    println("Zad. 4c: " + daysSeparatedByCommaStartingWithPFoldLeft(days))
    val productPrices = Map("cheese" -> 5, "onion" -> 2, "bread" -> 3, "garlic" -> 4, "ham" -> 10, "pepper" -> 1)
    reducePrices(productPrices)
    printTuple(1, "test", 1.5)
    optionExample(productPrices, "garlic")
    val numbers = List(0, 1, 2, 0, 3, 0, 4, 5, 6, 7, 0, 0, 8, 9)
    println("Zad. 8: " + removeZeros(numbers))
    println("Zad. 9: " + increaseByOne(numbers))
    val numbers2 = List(-7, -6, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14)
    println("Zad. 10: " + removeValues(numbers2))
  }

  //1a
  def daysSeparatedByComma(days: List[String]) {
    var string: String = ""
    for (day <- days) string += day + ", "
    println("Zad. 1a: " + string.dropRight(2))
  }

  //1b
  def daysSeparatedByCommaStartingWithP(days: List[String]) {
    var string = ""
    val char = "p"
    for (day <- days) {
      if (day.startsWith(char)) string += day + ", "
    }
    println("Zad. 1b: " + string.dropRight(2))
  }

  //1c
  def daysSeparatedByCommaWhileLoop(days: List[String]) = {
    var string = ""
    var int = 0
    while (int < days.length) {
      string += days(int) + ", "
      int = int + 1
    }
    println("Zad. 1c: " + string.dropRight(2))
  }

  //2a
  def daysSeparatedByCommaRecursion(days: List[String]): String = {
    if (days.nonEmpty) {
      days.head + ", " + daysSeparatedByCommaRecursion(days.tail)
    } else
      ""
  }

  //2b
  def daysSeparatedByCommaRecursionReversed(days: List[String]): String = {
    if (days.nonEmpty) {
      days.last + ", " + daysSeparatedByCommaRecursionReversed(days.init)
    }
    else ""
  }

  //3
  def daysSeparatedByCommaTailRecursion(list: List[String]): String = {
    @tailrec
    def daysSeparatedByCommaTailRecursionAccumulator(list: List[String], accumulator: String): String = {
      list match {
        case Nil => accumulator
        case x :: xs => daysSeparatedByCommaTailRecursionAccumulator(xs, accumulator + ", " + x)
      }
    }

    daysSeparatedByCommaTailRecursionAccumulator(list, "")
  }

  //4a
  def daysSeparatedByCommaFoldLeft(days: List[String]): Unit = {
    val string = days.foldLeft("")((a, b) => a + b + ", ")
    println("Zad. 4a: " + string.dropRight(2))
  }

  //4b
  def daysSeparatedByCommaFoldRight(days: List[String]): Unit = {
    val string = days.foldRight("")((a, b) => a + ", " + b)
    println("Zad. 4b: " + string.dropRight(2))
  }

  //4c
  def daysSeparatedByCommaStartingWithPFoldLeft(days: List[String]): String = {
    days.fold("") { (a, b) => {
      if (b.startsWith("p")) {
        a + b + ", "
      } else {
        a
      }
    }
    }
  }

  //5
  def reducePrices(productPrices: Map[String, Int]): Unit = {
    val newMap = productPrices.view.mapValues(_ * 0.9).toMap
    println("Zad. 5: " + newMap)
  }

  //6
  def printTuple(tuple: (Int, String, Double)): Unit = {
    val string = tuple._1 + " " + tuple._2 + " " + tuple._3
    println("Zad. 6: " + string)
  }

  //7
  def optionExample(productPrices: Map[String, Int], productName: String): Unit = {
    var string = ""
    val productPrice = productPrices.get(productName)
    if (productPrice.isDefined) {
      string = "Zad. 7a: Cena produktu " + productName + " wynosi: " + productPrice.get
    } else {
      string = "Zad. 7a: Nie odnaleziono produktu " + productName + "."
    }
    println(string)
  }

  //8
  def removeZeros(list: List[Int]): List[Int] = list match {
    case Nil => Nil
    case h :: t =>
      if (h == 0)
        removeZeros(t)
      else
        h :: removeZeros(t)
  }


  //9
  def increaseByOne(numbers: List[Int]): List[Int] = {
    numbers.map(number => number + 1)
  }

  //10
  def removeValues(numbers2: List[Int]): List[Int] = {
    numbers2.filter(number => number <= 12 && number >= -5).map(number => number.abs)
  }
}

package io.fele.snap.game

import io.fele.snap.game.MatchingRule._
import io.fele.snap.model.CardSuit._


import scala.io.StdIn.readLine
import scala.util.{Success, Try}


object Main extends App {
  var numberOfDecks: Try[Int] = _
  var matchRuleOption: Try[Int] = _

  do {
    println("How many decks do you want to play with? Please enter a positive integer:")
    numberOfDecks = Try(readLine().toInt)
  } while(numberOfDecks.isFailure || numberOfDecks.get <= 0)
  do {
    println("How cards should be matched?: 1)on suit 2)on value 3) both Please enter 1, 2 or 3:")
    matchRuleOption = Try(readLine().toInt)
  } while(matchRuleOption.isFailure || matchRuleOption.get <= 0 || matchRuleOption.get > 3)

  val matchRule: MatchingRule = matchRuleOption match {
    case Success(1) => OnSuit
    case Success(2) => OnValue
    case Success(3) => Both
  }

  val config = Config(
    player0Name = "Mike",
    player1Name = "Chun",
    numberOfDecks = numberOfDecks.get,
    matchingRule = matchRule,
  )
}

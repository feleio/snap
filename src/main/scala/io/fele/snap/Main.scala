package io.fele.snap

import io.fele.snap.game.MatchingRule.{Both, MatchingRule, OnSuit, OnValue}
import io.fele.snap.game.{Config, EventHandler, EventLogger, Game}

import scala.io.StdIn.readLine
import scala.util.{Success, Try}

object Main extends App {
  var yn = ""
  do {
    val eventHandler: EventHandler = new EventLogger
    var numberOfDecks: Try[Int] = Try(0)
    var matchRuleOption: Try[Int] = Try(0)

    do {
      println("How many decks do you want to play with? Please enter a positive integer:")
      numberOfDecks = Try(readLine().toInt)
    } while(numberOfDecks.isFailure || numberOfDecks.get <= 0)
    do {
      println("How cards should be matched? (1)on suit (2)on value (3)both. Please enter 1, 2 or 3:")
      matchRuleOption = Try(readLine().toInt)
    } while(matchRuleOption.isFailure || matchRuleOption.get <= 0 || matchRuleOption.get > 3)

    val matchRule: MatchingRule = matchRuleOption match {
      case Success(1) => OnSuit
      case Success(2) => OnValue
      case Success(3) => Both
      case _ => Both // default
    }

    val config = Config(
      player0Name = "Mike",
      player1Name = "Chun",
      numberOfDecks = numberOfDecks.get,
      matchingRule = matchRule,
    )
    val game = new Game(config, eventHandler)
    game.start()

    do {
      println("Play again? Please enter yes or no:")
      yn = readLine()
    } while(yn != "yes" && yn != "no")
  } while (yn == "yes")
  println("Bye!")
}

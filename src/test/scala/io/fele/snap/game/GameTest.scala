package io.fele.snap.game

import io.fele.snap.game.MatchingRule._
import io.fele.snap.model.Deck
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

class GameTest extends AnyFreeSpec with Matchers{
  val player0Name = "A"
  val player1Name = "B"
  val numberOfDecks = 10
  val matchingRule: MatchingRule = Both
  val config = Config(player0Name, player1Name, numberOfDecks, matchingRule)

  "Game" - {
    "constructor should initialize Game correctly" in {
      val game: Game = new Game(config)
      game.config should be(config)
      game.allCards.size should be (Deck.cards.size * numberOfDecks)
      game.players(0).hasMoreCard should be (true)
      game.players(1).hasMoreCard should be (true)
    }

    "should be fair to both user after a lot of games" in {
      val gameResults: Seq[GameResult] = (1 to 10000).map { _ =>
        new Game(config).start()
      }

      val statistic = gameResults.foldLeft((0, 0, 0)) { (z, result) =>
        result match {
          case GameFinishedWithDrawGame =>
            (z._1, z._2, z._3 + 1)
          case GameFinishedWithWinner(winner, _, _) if winner.name == player0Name =>
            (z._1 + 1, z._2, z._3)
          case GameFinishedWithWinner(winner, _, _) if winner.name == player1Name =>
            (z._1, z._2 + 1, z._3)
        }
      }
      println(statistic)
    }
  }
}

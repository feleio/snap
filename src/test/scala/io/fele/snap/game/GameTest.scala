package io.fele.snap.game

import io.fele.snap.game.MatchingRule._
import io.fele.snap.model.Deck
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

class GameTest extends AnyFreeSpec with Matchers{
  val player0Name = "A"
  val player1Name = "B"
  val numberOfDecks = 3
  val matchingRule: MatchingRule = Both

  "Game" - {
    "constructor should initialize Game correctly" in {
      val config = Config(player0Name, player1Name, numberOfDecks, matchingRule)
      val game: Game = new Game(config)
      game.config should be(config)
      game.allCards.size should be (Deck.cards.size * numberOfDecks)
      game.players(0).hasMoreCard should be (true)
      game.players(1).hasMoreCard should be (true)
    }


  }
}

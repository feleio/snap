package io.fele.snap.game

import io.fele.snap.model.Card
import io.fele.snap.model.CardSuit._
import io.fele.snap.model.CardValue._

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers


class PlayerTest extends AnyFreeSpec with Matchers {
  val cards = List(Card(Club, Two), Card(Spade, King), Card(Heart, Ace))
  val name = "Chun Lok"

  "Player" - {
    "constructor should set the value correctly" in {
      val player = new Player(name, cards)
      player.name should be (name)
      player.hasMoreCard should be (true)
      player.wonCardsCount should be (0)
    }

    "when having more than 0 cards" - {
      "should be able to pop a card from the stack" in {
        val player = new Player(name, cards)
        player.popCard() should be (Some(cards.head))
        player.hasMoreCard should be (true)
        player.popCard() should be (Some(cards(1)))
        player.hasMoreCard should be (true)
        player.popCard() should be (Some(cards(2)))
      }
    }

    "when having no more card" - {
      "should not be able to pop a card from the stack" in {
        val player = new Player(name, Nil)
        player.hasMoreCard should be (false)
        player.popCard() should be (None)
      }
    }

    "should be able to add won cards count" in {
      val player = new Player(name, cards)
      player.wonCardsCount should be (0)
      player.addWonCardsCount(count = 123)
      player.wonCardsCount should be (123)
    }
  }


}

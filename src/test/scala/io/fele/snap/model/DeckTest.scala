package io.fele.snap.model

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

import io.fele.snap.model.CardSuit._

class DeckTest extends AnyFreeSpec with Matchers {
  "A single deck should contains correct cards" in {
    Deck.cards.size should be (52)
    Deck.cards.toSet.size should be (52)
    Seq(Diamond, Club, Heart, Spade).foreach { suit =>
      Deck.cards.count(_.suit == suit) should be (13)
    }
  }
}

package io.fele.snap.model

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers
import io.fele.snap.model.CardSuit._
import io.fele.snap.model.CardValue._

class CardTest extends AnyFreeSpec with Matchers{
  "Card should have correct string representation" in {
    Card(Club, Two).toString should be ("2 Club")
    Card(Spade, King).toString should be ("K Spade")
    Card(Heart, Ace).toString should be ("A Heart")
  }
}

package io.fele.snap.model

import io.fele.snap.model.CardSuit._
import io.fele.snap.model.CardValue._

object Deck {
  val suites = List(Spade, Heart, Club, Diamond)
  val values = List(Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King, Ace)

  val cards: List[Card] = for {
    s <- suites
    v <- values
  } yield Card(s, v)
}

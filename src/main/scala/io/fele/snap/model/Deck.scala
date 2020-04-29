package io.fele.snap.model

import CardSuit._
import CardValue._

object Deck {
  val suites = List(Spade, Heart, Club, Diamond)
  val values = List(Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King, Ace)

  val cards: List[Card] = for {
    s <- suites
    v <- values
  } yield Card(s, v)
}

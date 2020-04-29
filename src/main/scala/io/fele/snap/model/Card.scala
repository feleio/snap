package io.fele.snap.model

import io.fele.snap.model.CardSuit._
import io.fele.snap.model.CardValue._

case class Card(suit: CardSuit, value: CardValue) {
  override def toString: String = s"${CardValue.toString(value)} $suit"
}

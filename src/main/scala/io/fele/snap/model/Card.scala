package io.fele.snap.model

import CardSuit._
import CardValue._

case class Card(suit: CardSuit, value: CardValue) {
  override def toString: String = s"${CardValue.toString(value)} $suit"
}

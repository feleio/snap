package io.fele.snap.game

import io.fele.snap.model.Card

class Player(val name: String, private var cards: List[Card]) {
  var wonCardsCount: Int = 0

  def popCard(): Option[Card] = {
    val headOpt = cards.headOption
    if (headOpt.isDefined)
      cards = cards.tail
    headOpt
  }

  def hasMoreCard: Boolean = cards.nonEmpty

  def addWonCardsCount(count: Int): Unit = wonCardsCount += count
}

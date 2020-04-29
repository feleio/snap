package io.fele.snap.game

import io.fele.snap.game.MatchingRule.{Both, OnSuit, OnValue}
import io.fele.snap.model.{Card, Deck}

import scala.util.Random

class Game(
  cfg: Config
) {
  val config: Config = cfg
  val allCards: List[Card] = (1 to config.numberOfDecks).flatMap(_ => Deck.cards).toList
  val shuffled: List[Card] = Random.shuffle(allCards)
  val players = Vector(
    new Player(name = config.player0Name, cards = shuffled.take(shuffled.size/2)),
    new Player(name = config.player1Name, cards = shuffled.drop(shuffled.size/2)),
  )

  // randomly select a player to start first
  var curPlayerId: Int = if (Random.nextBoolean()) 0 else 1
  var pile: List[Card] = Nil

  def start(): Unit = {
    while(players.exists(_.hasMoreCard)) {
      val card: Card = players(curPlayerId).popCard().get
      pile = card :: pile
    }
  }

  private def shouldMatch(pile: List[Card]): Boolean = {
    if (pile.size >= 2) {
      val topCard: Card = pile.head
      val secondCard: Card = pile.tail.head
      config.matchingRule match {
        case OnSuit =>
          topCard.suit == secondCard.suit
        case OnValue =>
          topCard.value == secondCard.value
        case Both =>
          topCard.suit == secondCard.suit && topCard.value == secondCard.value
      }
    } else false
  }
}

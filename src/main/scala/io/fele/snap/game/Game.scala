package io.fele.snap.game

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

  def start(): Unit = ???

  private def shouldMatch(pile: List[Card]): Boolean = ???
}

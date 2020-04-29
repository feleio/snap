package io.fele.snap.game

import io.fele.snap.game.MatchingRule._
import io.fele.snap.model.{Card, Deck}

import scala.util.Random

class Game(
  cfg: Config,
  eventHandler: EventHandler = new DummyEventHandler,
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

  def start(): GameResult = {
    eventHandler.dispatch(GameStarted(players(curPlayerId)))
    while(players.exists(_.hasMoreCard)) {
      val card: Card = players(curPlayerId).popCard().get
      pile = card :: pile
      eventHandler.dispatch(PlaceCard(players(curPlayerId), card))

      if(shouldMatch(pile)) {
        // randomly select a player who is the first one to shout snap!
        val fasterPlayerId: Int = if (Random.nextBoolean()) 0 else 1
        players(fasterPlayerId).addWonCardsCount(pile.size)
        eventHandler.dispatch(Snap(players(fasterPlayerId), pile.head, pile.tail.head, pile.size))
        pile = Nil
        curPlayerId = if (players(fasterPlayerId).hasMoreCard) fasterPlayerId else (fasterPlayerId + 1) % 2
      } else {
        val nextPlayerId: Int = (curPlayerId + 1) % 2
        if (players(nextPlayerId).hasMoreCard)
          curPlayerId = nextPlayerId
      }
    }

    if (players(0).wonCardsCount == players(1).wonCardsCount) {
      eventHandler.dispatch(GameFinishedWithDrawGame)
      GameFinishedWithDrawGame
    } else {
      val winner: Player = players.maxBy(_.wonCardsCount)
      val loser: Player = players.minBy(_.wonCardsCount)
      val event: GameResult = GameFinishedWithWinner(winner, loser, pile)
      eventHandler.dispatch(event)
      event
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

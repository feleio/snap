package io.fele.snap.game

import io.fele.snap.model.Card

sealed trait GameEvent
sealed trait GameResult extends GameEvent

case class GameStarted(firstPlayer: Player) extends GameEvent
case class PlaceCard(player: Player, card: Card) extends GameEvent
case class Snap(player: Player, card: Card, lastCard: Card, wonCardsNum: Int) extends GameEvent
case class GameFinishedWithWinner(winner: Player, loser: Player, pile: List[Card]) extends GameResult
case object GameFinishedWithDrawGame extends GameResult
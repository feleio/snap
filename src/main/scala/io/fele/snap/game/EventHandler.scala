package io.fele.snap.game

abstract class EventHandler {
  def dispatch(event: GameEvent): Unit
}

class DummyEventHandler extends EventHandler {
  override def dispatch(event: GameEvent): Unit = ()
}

class EventLogger extends EventHandler {
  override def dispatch(event: GameEvent): Unit = event match {
    case GameStarted(firstPlayer) =>
      log(s"Game Started, the first player is ${firstPlayer.name}.")
    case PlaceCard(player, card) =>
      log(s"${player.name}:  $card")
    case Snap(player, card, lastCard, wonCardsNum) =>
      log(s"${player.name}:  Snap!")
      log(s"$card matches $lastCard. ${player.name} won $wonCardsNum cards.")
    case GameFinishedWithWinner(winner, loser, pile) =>
      log(s"${winner.name} won the game with ${winner.wonCardsCount} cards, " +
        s"while ${loser.name} has ${loser.wonCardsCount} cards. " +
        s"There are still ${pile.size} cards on the pile.")
    case GameFinishedWithDrawGame =>
      log(s"Draw game.")
  }

  private def log(s: String): Unit = println(s)
}

package io.fele.snap.game

abstract class EventHandler {
  def dispatch(event: GameEvent): Unit
}

class DummyEventHandler extends EventHandler {
  override def dispatch(event: GameEvent): Unit = ()
}

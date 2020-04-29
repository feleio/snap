package io.fele.snap.model

object CardValue extends Enumeration {
  type CardValue = Value
  val Ace, Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King = Value

  def toString(v: CardValue): String = v match {
    case Ace   => "A"
    case Two   => "2"
    case Three => "3"
    case Four  => "4"
    case Five  => "5"
    case Six   => "6"
    case Seven => "7"
    case Eight => "8"
    case Nine  => "9"
    case Ten   => "10"
    case Jack  => "J"
    case Queen => "Q"
    case King  => "K"
  }
}

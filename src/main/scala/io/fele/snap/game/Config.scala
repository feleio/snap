package io.fele.snap.game

import MatchingRule.MatchingRule

case class Config(
  player0Name: String,
  player1Name: String,
  numberOfDecks: Int,
  matchingRule: MatchingRule
)

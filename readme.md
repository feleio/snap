# Snap game
- There is a fairness test in GameTest.scala which runs a lot of game to see if it is fair to both players.
- Game State and Game logic can be decoupled by having repo classes for state and service classes for business logic. But didn't spend time to do that
- Only added scalatest to dependencies
- When a player won a round. He/She will not put cards to their facing down deck. They will just put those won cards aside for score at the end, But it easy to edit the code to put cards to their facing down deck.
 
## Run the game
```shell script
sbt run
```
## Run the test
```shell script
sbt test
```



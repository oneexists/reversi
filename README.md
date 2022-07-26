# J2EE Reversi Game

Play a game of [Reversi (aka Othello)](https://en.wikipedia.org/wiki/Reversi)
against a friend!

Some basic rules of the game:
- Players choose whether they are playing as dark or light with the dark piece
  player going first
- Select a position on the board where at least one straight occupied line
  (horizontal, vertical or diagonal) exists between the new piece and another 
  player's piece. The spaces between must contain at least one of the other
  player's pieces to be flipped.
- If one player is unable to make a valid move, the "Pass" button will appear
  and the player can pass their turn.
- The game ends if neither player can move, which may happen before the board
  is filled if a player has no remaining pieces.
- The player with the most pieces on the board at the end of the game is the
  winner.

![New Game](https://github.com/oneexists/reversi/blob/main/img/new-game.png)

![Finished Game](https://github.com/oneexists/reversi/blob/main/img/finished-game.png)

### Functional Requirements
- JSP-based application using MVC architecture
- Displays last move, current player, current score, whether the game is over,
  and notifies player if a move is invalid
- JUnit testing ensures:
  - The correct pieces are placed on the board at the beginning of the game
  - Edges of the board are recognized
  - Rows are checked in both directions to ensure correct pieces are flipped

### Non-Functional Requirements
- Improved appearance with the addition of Bootstrap for styling of buttons and
  layout of game board
- Notifications are arranged to interfere minimally with the placement of the
  board on the page

GameBoard Project

Overview

The GameBoard Project is a Java-based console application that simulates a board game. It allows players to place, move, and interact with game pieces like Kings and Queens on an 8x8 grid. The program uses several design patterns to enhance maintainability, scalability, and readability.

This document explains the overall structure of the project and the design patterns implemented.

Features

8x8 Game Board: Simulates a chess-like grid for placing and moving pieces.

Dynamic Game Logic: Validates moves based on game piece types.

Notification System: Tracks and announces player actions and turn changes.

Turn Management: Alternates turns between Player 1 and Player 2.

Expandable Design: Easily add new piece types or rules.

Design Patterns Used

1. Singleton Pattern

Class: GameBoard

The GameBoard class uses the Singleton pattern to ensure that only one instance of the board exists throughout the program. This central instance is shared across different components to maintain consistency.

Why Singleton?

Ensures a single source of truth for the game state.

Simplifies access to the board from multiple parts of the program.

Implementation:

public static GameBoard getInstance() {
if (instance == null) {
instance = new GameBoard();
}
return instance;
}

2. Observer Pattern

Classes:

Subject (Interface)

GameBoard (Concrete Subject)

Observer (Interface)

ConsoleObserver (Concrete Observer)

The Observer pattern is used to notify observers of events such as piece placement, removal, or turn changes. The GameBoard class acts as the subject, while ConsoleObserver listens for updates and outputs notifications to the console.

Why Observer?

Decouples the game logic from the notification system.

Allows adding more observers (e.g., logging to a file) without modifying the GameBoard.

Implementation:

Subject Interface:

public interface Subject {
void addObserver(Observer observer);
void removeObserver(Observer observer);
void notifyObservers(String message);
}

ConsoleObserver:

public class ConsoleObserver implements Observer {
@Override
public void update(String message) {
System.out.println("Notification: " + message);
}
}

3. Strategy Pattern

Class: GamePiece

The isValidMove method in GamePiece uses the Strategy pattern to implement piece-specific movement rules. Each type of piece (e.g., King, Queen) defines its own movement logic within this method.

Why Strategy?

Enables flexible addition of new piece types with unique movement rules.

Keeps the code modular and avoids a monolithic switch-case structure for move validation.

Implementation:

public boolean isValidMove(int startRow, int startCol, int endRow, int endCol, GameBoard board) {
if (type.equals("King")) {
if (Math.abs(startRow - endRow) <= 1 && Math.abs(startCol - endCol) <= 1) {
Tile targetTile = board.getTile(endRow, endCol);
return targetTile.isEmpty() || !targetTile.getPiece().getOwner().equals(this.owner);
}
}
// Add rules for other piece types
return false;
}

4. Composite Pattern

Interfaces:

BoardComponent (Interface)

GamePiece and Tile (Leaf Classes)

The Composite pattern is used to treat game pieces and tiles uniformly as BoardComponent. This abstraction allows tiles and pieces to be displayed and managed consistently, simplifying the rendering and logic.

Why Composite?

Provides a common interface for tiles and pieces.

Facilitates uniform operations like displaying board components.

Implementation:

public interface BoardComponent {
void display();
}

public class Tile implements BoardComponent {
public void display() {
if (piece == null) {
System.out.print("- ");
} else {
piece.display();
}
}
}

How to Run

Prerequisites

Java 17 or later.

IntelliJ IDEA or another Java IDE.

Steps:

Clone the repository or copy all provided files.

Compile and run Main.java.

Observe the console output for gameplay actions and board updates.

Example Gameplay

Input:

The program currently uses hardcoded moves in Main.java. For example:

int startRow = 0, startCol = 0, endRow = 1, endCol = 1;
if (king.isValidMove(startRow, startCol, endRow, endCol, board)) {
board.placePiece(endRow, endCol, king);
board.removePiece(startRow, startCol);
} else {
System.out.println("Invalid move!");
}

Output:

Notification: Player 1 placed King at (0, 0).
Notification: It's Player 2's turn.
Notification: Player 2 placed Queen at (7, 7).
Notification: It's Player 1's turn.
Notification: Player 1 placed King at (1, 1).
Notification: Piece removed from (0, 0).

Final Board:
- - - - - - - -
- K - - - - - -
- - - - - - - -
- - - - - - - -
- - - - - - - -
- - - - - - - -
- - - - - - - -
- - - - - - - Q

Future Enhancements

Add more pieces and their movement rules.

Implement user input for real-time gameplay.

Add victory conditions and game-end logic.

Save and load game states.

Enhance notifications with detailed logs or graphical output.

Conclusion

This project demonstrates the use of multiple design patterns to create a modular and extendable board game framework. It is a foundation for more complex game development or as an educational tool for understanding design patterns in Java.


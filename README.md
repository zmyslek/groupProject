# GameBoard Project

## Overview
The GameBoard Project is a Java-based console application that simulates a board game. It allows players to place, move, and interact with game pieces like Kings and Queens on an 8x8 grid. The program uses several design patterns to enhance maintainability, scalability, and readability.

This document explains the overall structure of the project and the design patterns implemented.

## Features
- **8x8 Game Board**: Simulates a chess-like grid for placing and moving pieces.
- **Dynamic Game Logic**: Validates moves based on game piece types.
- **Notification System**: Tracks and announces player actions and turn changes.
- **Turn Management**: Alternates turns between Player 1 and Player 2.
- **Expandable Design**: Easily add new piece types or rules.

## Design Patterns Used

### 1. Singleton Pattern
**Class:** `GameBoard`

The `GameBoard` class uses the Singleton pattern to ensure that only one instance of the board exists throughout the program. This central instance is shared across different components to maintain consistency.

#### Why Singleton?
- Ensures a single source of truth for the game state.
- Simplifies access to the board from multiple parts of the program.

#### Implementation:
```java
public static GameBoard getInstance() {
    if (instance == null) {
        instance = new GameBoard();
    }
    return instance;
}
```

### 2. Observer Pattern
**Classes:**
- `Subject` (Interface)
- `GameBoard` (Concrete Subject)
- `Observer` (Interface)
- `ConsoleObserver` (Concrete Observer)

The Observer pattern is used to notify observers of events such as piece placement, removal, or turn changes. The `GameBoard` class acts as the subject, while `ConsoleObserver` listens for updates and outputs notifications to the console.

#### Why Observer?
- Decouples the game logic from the notification system.
- Allows adding more observers (e.g., logging to a file) without modifying the `GameBoard`.

#### Implementation:
**Subject Interface:**
```java
public interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(String message);
}
```
**ConsoleObserver:**
```java
public class ConsoleObserver implements Observer {
    @Override
    public void update(String message) {
        System.out.println("Notification: " + message);
    }
}
```

### 3. Strategy Pattern
**Classes:**
- `MoveStrategy` (Interface)
- `KingMoveStrategy` (Concrete Strategy)
- `QueenMoveStrategy` (Concrete Strategy)
- `GamePiece` (Context)

The Strategy pattern is implemented to define movement rules for different game pieces. Each piece type (e.g., King, Queen) has a separate strategy class.

#### Why Strategy?
- Enables flexible addition of new piece types with unique movement rules.
- Keeps the code modular and avoids a monolithic switch-case structure for move validation.

#### Implementation:
**MoveStrategy Interface:**
```java
public interface MoveStrategy {
    boolean isValidMove(int startRow, int startCol, int endRow, int endCol, GameBoard board);
}
```
**KingMoveStrategy:**
```java
public class KingMoveStrategy implements MoveStrategy {
    @Override
    public boolean isValidMove(int startRow, int startCol, int endRow, int endCol, GameBoard board) {
        return Math.abs(startRow - endRow) <= 1 && Math.abs(startCol - endCol) <= 1;
    }
}
```

### 4. Composite Pattern
**Interfaces:**
- `BoardComponent` (Interface)
- `Tile` and `GamePiece` (Leaf Classes)
- `GameBoard` (Composite Class)

The Composite pattern is used to treat game pieces and tiles uniformly as `BoardComponent`. This abstraction allows tiles and pieces to be displayed and managed consistently, simplifying the rendering and logic.

#### Why Composite?
- Provides a common interface for tiles and pieces.
- Facilitates uniform operations like displaying board components.

#### Implementation:
**BoardComponent Interface:**
```java
public interface BoardComponent {
    void display();
}
```
**Tile Implementation:**
```java
public class Tile implements BoardComponent {
    private GamePiece piece;
    
    public void display() {
        if (piece == null) {
            System.out.print("- ");
        } else {
            piece.display();
        }
    }
}
```

### 5. Decorator Pattern (Newly Added)
**Classes:**
- `PieceDecorator` (Abstract Decorator)
- `ShieldedPiece` (Concrete Decorator)

The Decorator pattern is used to enhance game pieces dynamically. For example, a piece can gain a "shield" that makes it temporarily invulnerable.

#### Why Decorator?
- Allows dynamic modification of game pieces without changing their base class.
- Adds flexibility to introduce power-ups or special abilities.

#### Implementation:
**PieceDecorator:**
```java
public abstract class PieceDecorator extends GamePiece {
    protected GamePiece decoratedPiece;
    
    public PieceDecorator(GamePiece decoratedPiece) {
        super(decoratedPiece.getMoveStrategy());
        this.decoratedPiece = decoratedPiece;
    }
}
```
**ShieldedPiece:**
```java
public class ShieldedPiece extends PieceDecorator {
    public ShieldedPiece(GamePiece decoratedPiece) {
        super(decoratedPiece);
    }
    
    @Override
    public void display() {
        System.out.print("(S) ");
        decoratedPiece.display();
    }
}
```

### 6. Command Pattern (Newly Added)
**Classes:**
- `Command` (Interface)
- `MoveCommand` (Concrete Command)
- `GameInvoker` (Invoker)

The Command pattern is used to encapsulate game moves, allowing actions to be stored and undone if needed.

#### Why Command?
- Enables undo/redo functionality.
- Separates action logic from execution.

#### Implementation:
**Command Interface:**
```java
public interface Command {
    void execute();
    void undo();
}
```
**MoveCommand:**
```java
public class MoveCommand implements Command {
    private Tile from, to;
    private GamePiece piece;
    
    public MoveCommand(Tile from, Tile to, GamePiece piece) {
        this.from = from;
        this.to = to;
        this.piece = piece;
    }
    
    @Override
    public void execute() {
        to.placePiece(piece);
        from.removePiece();
    }
    
    @Override
    public void undo() {
        from.placePiece(piece);
        to.removePiece();
    }
}
```

## How to Run
**Prerequisites:**
- Java 17 or later.
- IntelliJ IDEA or another Java IDE.

**Steps:**
1. Clone the repository or copy all provided files.
2. Compile and run `Main.java`.
3. Observe the console output for gameplay actions and board updates.

## Conclusion
This project demonstrates multiple design patterns to create a modular and extendable board game framework. Future enhancements may include more pieces, real-time user input, and graphical output.


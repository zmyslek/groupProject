# Chess Game Project

## Overview

The Chess Game Project is a Java-based console application that implements a fully functional chess game. It allows players to move pieces according to standard chess rules, with support for basic chess piece movements and turn management. The project demonstrates the use of several design patterns to create a maintainable and extensible codebase.

## Features

- Complete Chess Board: Standard 8x8 chess board with all traditional pieces
- Move Validation: Piece-specific movement rules
- Turn Management: Alternates between White and Black players
- Command System: Supports move execution, undo, and redo operations
- Force Mode: Option to override turn order for testing or practice
- Multiple Input Support: Flexible input handling system
- Console Visualization: Clear board representation with piece positions

## Design Patterns Used

### Factory Method Pattern (Creational)
**Class**: `GamePieceFactory`

The Factory Method pattern is used to create different types of chess pieces (King, Queen, Bishop, etc.) dynamically. This centralizes piece creation and makes adding new piece types straightforward.

**Why Factory Method?**
- Encapsulates piece creation logic
- Makes the system extensible for new piece types
- Maintains consistency in piece creation

**Implementation:**
```java
public class GamePieceFactory {
    public static GamePiece createPiece(String type, Player player) {
        return switch (type.toLowerCase()) {
            case "king" -> new King(player);
            case "queen" -> new Queen(player);
            // Other pieces...
            default -> throw new IllegalArgumentException("Unknown piece type: " + type);
        };
    }
}
```

### Adapter Pattern (Structural)
**Classes**:
- `MoveInput` (Interface)
- `ConsoleInputAdapter`
- `FileInputAdapter`

The Adapter pattern standardizes different input formats into a common interface, allowing the game to handle moves from various sources uniformly.

**Why Adapter?**
- Enables multiple input methods without changing core game logic
- Standardizes move input processing
- Facilitates adding new input sources

**Implementation:**
```java
public interface MoveInput {
    Position getFrom();
    Position getTo();
}

public class ConsoleInputAdapter implements MoveInput {
    // Converts string input like "e2e4" to Position objects
}
```

### Command Pattern (Behavioral)
**Classes**:
- `Command` (Interface)
- `MoveCommand`
- `CommandHistory`

The Command pattern encapsulates move operations and provides undo/redo functionality.

**Why Command?**
- Enables move history tracking
- Implements undo/redo functionality
- Separates move execution from move logic

**Implementation:**
```java
public interface Command {
    void execute();
    void undo();
}

public class CommandHistory {
    private final Deque<Command> undoStack = new ArrayDeque<>();
    private final Deque<Command> redoStack = new ArrayDeque<>();
    // Methods for managing command history
}
```

## How to Run

### Prerequisites
- Java 17 or later
- IntelliJ IDEA or another Java IDE

### Setup Steps
1. Clone the repository
2. Open the project in your IDE
3. Build the project
4. Run the `Main` class

### Game Commands
- Move format: `e2e4` (source square to target square)
- `undo`: Take back the last move
- `redo`: Replay the last undone move
- `force`: Toggle force mode (ignore turn order)
- `quit`: Exit the game

### Example Gameplay
```
Welcome to Chess!
Current board state:
8 r n b q k b n r 
7 p p p p p p p p 
6 . . . . . . . . 
5 . . . . . . . . 
4 . . . . . . . . 
3 . . . . . . . . 
2 P P P P P P P P 
1 R N B Q K B N R 
  a b c d e f g h

Enter your move: e2e4
```

## Project Structure
```
src/
├── board/
│   └── GameBoard.java
├── command/
│   ├── Command.java
│   ├── CommandHistory.java
│   └── MoveCommand.java
├── factory/
│   └── GamePieceFactory.java
├── game/
│   ├── ChessGame.java
│   └── Main.java
├── input/
│   ├── ConsoleInputAdapter.java
│   ├── FileInputAdapter.java
│   └── MoveInput.java
└── model/
    ├── GamePiece.java
    ├── Player.java
    ├── Position.java
    └── pieces/
        ├── Bishop.java
        ├── King.java
        ├── Knight.java
        ├── Pawn.java
        ├── Queen.java
        └── Rook.java
```

## Future Enhancements
- Check and checkmate detection
- Castling and en passant moves
- Game state saving/loading
- Network play support
- GUI implementation
- Move validation considering piece obstruction
- Game history export

## Conclusion
This chess game implementation demonstrates the effective use of design patterns to create a maintainable and extensible application. The project serves as both a functional chess game and an educational example of object-oriented design principles and patterns in practice.

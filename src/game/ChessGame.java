package game;

import board.GameBoard;
import command.CommandHistory;
import command.MoveCommand;
import input.ConsoleInputAdapter;
import input.MoveInput;
import model.GamePiece;
import model.Player;
import model.Position;
import factory.GamePieceFactory;

public class ChessGame {
    private final GameBoard board;
    private final CommandHistory commandHistory;
    private Player currentPlayer;

    public ChessGame() {
        this.board = new GameBoard();
        this.commandHistory = new CommandHistory();
        this.currentPlayer = Player.WHITE;
        System.out.println("Chess game started!");
        printBoard();
    }

    public void handleMove(String input, boolean forceMode) {
        try {
            System.out.println("\nAttempting move: " + input);
            System.out.println("Current player: " + currentPlayer);

            MoveInput moveInput = new ConsoleInputAdapter(input);
            Position from = moveInput.getFrom();
            Position to = moveInput.getTo();

            if (!isValidMove(from, to, forceMode)) {
                throw new IllegalArgumentException("Invalid move: " + input);
            }

            MoveCommand moveCommand = new MoveCommand(board, from, to);
            commandHistory.execute(moveCommand);

            // Check for pawn promotion after move
            GamePiece movedPiece = board.getPiece(to);
            checkForPromotion(to, movedPiece);

            if (!forceMode) {
                switchPlayer();
            }
            System.out.println("Move successful!");
            printBoard();

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private void checkForPromotion(Position to, GamePiece piece) {
        if (piece != null && piece.getType().equals("Pawn")) {
            int promotionRank = (piece.getPlayer() == Player.WHITE) ? 7 : 0;
            if (to.getY() == promotionRank) {
                GamePiece promotedPiece = GamePieceFactory.promotePiece(piece, "Queen");
                board.setPiece(to, promotedPiece);
                System.out.println("Pawn promoted to Queen!");
            }
        }
    }

    private boolean isValidMove(Position from, Position to, boolean forceMode) {
        if (!board.isValidPosition(from) || !board.isValidPosition(to)) {
            return false;
        }

        GamePiece piece = board.getPiece(from);
        if (piece == null) {
            return false;
        }

        // Only check current player if not in force mode
        if (!forceMode && piece.getPlayer() != currentPlayer) {
            return false;
        }

        return piece.isValidMove(from, to, board);
    }

    private void printBoard() {
        System.out.println("\nCurrent board state:");
        System.out.println("BLACK (lowercase pieces)");
        for (int i = 7; i >= 0; i--) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < 8; j++) {
                GamePiece piece = board.getPiece(new Position(j, i));
                if (piece == null) {
                    System.out.print(". ");
                } else {
                    char symbol = piece.getType().charAt(0);
                    // Use 'N' for Knight to avoid confusion with King
                    if (piece.getType().equals("Knight")) {
                        symbol = 'N';
                    }
                    symbol = piece.getPlayer() == Player.WHITE ?
                            Character.toUpperCase(symbol) :
                            Character.toLowerCase(symbol);
                    System.out.print(symbol + " ");
                }
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
        System.out.println("WHITE (uppercase pieces)");
        System.out.println("Current turn: " + currentPlayer);
    }

    public void undo() {
        commandHistory.undo();
        switchPlayer();
        System.out.println("Move undone!");
        printBoard();
    }

    public void redo() {
        commandHistory.redo();
        switchPlayer();
        System.out.println("Move redone!");
        printBoard();
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == Player.WHITE) ? Player.BLACK : Player.WHITE;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}
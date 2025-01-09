package game;

import board.GameBoard;
import command.CommandHistory;
import command.MoveCommand;
import input.ConsoleInputAdapter;
import input.MoveInput;
import model.GamePiece;
import model.Player;
import model.Position;

public class ChessGame {
    private final GameBoard board;
    private final CommandHistory commandHistory;
    private Player currentPlayer;

    public ChessGame() {
        this.board = new GameBoard();
        this.commandHistory = new CommandHistory();
        this.currentPlayer = Player.WHITE;
    }

    public void handleMove(String input) {
        try {
            MoveInput moveInput = new ConsoleInputAdapter(input);
            Position from = moveInput.getFrom();
            Position to = moveInput.getTo();

            if (!isValidMove(from, to)) {
                throw new IllegalArgumentException("Invalid move");
            }

            MoveCommand moveCommand = new MoveCommand(board, from, to);
            commandHistory.execute(moveCommand);
            switchPlayer();

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private boolean isValidMove(Position from, Position to) {
        if (!board.isValidPosition(from) || !board.isValidPosition(to)) {
            return false;
        }

        GamePiece piece = board.getPiece(from);
        if (piece == null || piece.getPlayer() != currentPlayer) {
            return false;
        }

        return piece.isValidMove(from, to, board);
    }

    public void undo() {
        commandHistory.undo();
        switchPlayer();
    }

    public void redo() {
        commandHistory.redo();
        switchPlayer();
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == Player.WHITE) ? Player.BLACK : Player.WHITE;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}

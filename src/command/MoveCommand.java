package command;

import board.GameBoard;
import model.GamePiece;
import model.Position;

public class MoveCommand implements Command {
    private final GameBoard board;
    private final Position from;
    private final Position to;
    private GamePiece capturedPiece;

    public MoveCommand(GameBoard board, Position from, Position to) {
        this.board = board;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute() {
        GamePiece piece = board.getPiece(from);
        if (piece == null) {
            throw new IllegalStateException("No piece at source position");
        }

        capturedPiece = board.getPiece(to);
        board.movePiece(from, to);
    }

    @Override
    public void undo() {
        GamePiece piece = board.getPiece(to);
        if (piece == null) {
            throw new IllegalStateException("No piece at target position");
        }

        board.movePiece(to, from);
        if (capturedPiece != null) {
            board.setPiece(to, capturedPiece);
        }
    }
}
package model.decorator;

import board.GameBoard;
import model.GamePiece;
import model.Player;
import model.Position;

public class FirstMovePieceDecorator extends PieceDecorator {
    private boolean hasMoved = false;

    public FirstMovePieceDecorator(GamePiece piece) {
        super(piece);
    }

    @Override
    public boolean isValidMove(Position from, Position to, GameBoard board) {
        if (!hasMoved && decoratedPiece.getType().equals("Pawn")) {
            int direction = (decoratedPiece.getPlayer() == Player.WHITE) ? 1 : -1;
            if (to.getX() == from.getX() && to.getY() == from.getY() + (2 * direction)) {
                Position intermediate = new Position(from.getX(), from.getY() + direction);
                if (board.getPiece(intermediate) == null && board.getPiece(to) == null) {
                    hasMoved = true;
                    return true;
                }
            }
        }

        boolean validMove = decoratedPiece.isValidMove(from, to, board);
        if (validMove) {
            hasMoved = true;
        }
        return validMove;
    }

    public boolean hasMovedStatus() {
        return hasMoved;
    }
}
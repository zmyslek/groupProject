package model.decorator;

import board.GameBoard;
import model.GamePiece;
import model.Player;
import model.Position;

public abstract class PieceDecorator implements GamePiece {
    protected GamePiece decoratedPiece;

    public PieceDecorator(GamePiece piece) {
        this.decoratedPiece = piece;
    }

    @Override
    public String getType() {
        return decoratedPiece.getType();
    }

    @Override
    public Player getPlayer() {
        return decoratedPiece.getPlayer();
    }

    @Override
    public boolean isValidMove(Position from, Position to, GameBoard board) {
        return decoratedPiece.isValidMove(from, to, board);
    }
}
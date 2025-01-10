package model.decorator;

import board.GameBoard;
import model.GamePiece;
import model.Position;
import factory.GamePieceFactory;

public class PromotedPieceDecorator extends PieceDecorator {
    private final String promotedType;

    public PromotedPieceDecorator(GamePiece piece, String promotedType) {
        super(piece);
        this.promotedType = promotedType;
    }

    @Override
    public String getType() {
        return promotedType;
    }

    @Override
    public boolean isValidMove(Position from, Position to, GameBoard board) {
        GamePiece promotedPiece = GamePieceFactory.createPiece(promotedType, getPlayer());
        return promotedPiece.isValidMove(from, to, board);
    }
}
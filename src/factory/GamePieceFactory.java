package factory;

import model.GamePiece;
import model.Player;
import model.pieces.*;
import model.decorator.FirstMovePieceDecorator;
import model.decorator.PromotedPieceDecorator;

public class GamePieceFactory {
    public static GamePiece createPiece(String type, Player player) {
        GamePiece piece = switch (type.toLowerCase()) {
            case "king" -> new King(player);
            case "queen" -> new Queen(player);
            case "bishop" -> new Bishop(player);
            case "knight" -> new Knight(player);
            case "rook" -> new Rook(player);
            case "pawn" -> new FirstMovePieceDecorator(new Pawn(player));
            default -> throw new IllegalArgumentException("Unknown piece type: " + type);
        };
        return piece;
    }

    public static GamePiece promotePiece(GamePiece piece, String newType) {
        return new PromotedPieceDecorator(piece, newType);
    }
}
package factory;

import model.GamePiece;
import model.Player;
import model.pieces.*;

public class GamePieceFactory {
    public static GamePiece createPiece(String type, Player player) {
        return switch (type.toLowerCase()) {
            case "king" -> new King(player);
            case "queen" -> new Queen(player);
            case "bishop" -> new Bishop(player);
            case "knight" -> new Knight(player);
            case "rook" -> new Rook(player);
            case "pawn" -> new Pawn(player);
            default -> throw new IllegalArgumentException("Unknown piece type: " + type);
        };
    }
}
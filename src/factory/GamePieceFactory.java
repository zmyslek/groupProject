package factory;

import model.GamePiece;
import model.Player;
import model.pieces.King;
import model.pieces.Queen;

public class GamePieceFactory {
    public static GamePiece createPiece(String type, Player player) {
        return switch (type.toLowerCase()) {
            case "king" -> new King(player);
            case "queen" -> new Queen(player);
            default -> throw new IllegalArgumentException("Unknown piece type: " + type);
        };
    }
}
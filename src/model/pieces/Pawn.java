package model.pieces;

import model.GamePiece;
import model.Player;
import model.Position;
import board.GameBoard;

public class Pawn implements GamePiece {
    private final Player player;

    public Pawn(Player player) {
        this.player = player;
    }

    @Override
    public String getType() {
        return "Pawn";
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public boolean isValidMove(Position from, Position to, GameBoard board) {
        int dx = to.getX() - from.getX();
        int dy = to.getY() - from.getY();

        // White pawns move up (positive dy), black pawns move down (negative dy)
        int direction = (player == Player.WHITE) ? 1 : -1;

        // Basic one square forward move
        if (dx == 0 && dy == direction) {
            return board.getPiece(to) == null;
        }

        // Initial two square move
        if (dx == 0 && dy == 2 * direction) {
            int startRank = (player == Player.WHITE) ? 1 : 6;
            return from.getY() == startRank &&
                    board.getPiece(to) == null &&
                    board.getPiece(new Position(from.getX(), from.getY() + direction)) == null;
        }

        return false;
    }
}
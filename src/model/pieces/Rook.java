package model.pieces;

import model.GamePiece;
import model.Player;
import model.Position;
import board.GameBoard;

public class Rook implements GamePiece {
    private final Player player;

    public Rook(Player player) {
        this.player = player;
    }

    @Override
    public String getType() {
        return "Rook";
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public boolean isValidMove(Position from, Position to, GameBoard board) {
        int dx = Math.abs(to.getX() - from.getX());
        int dy = Math.abs(to.getY() - from.getY());
        // Rook moves horizontally or vertically (one of dx or dy must be 0)
        return (dx == 0 && dy != 0) || (dx != 0 && dy == 0);
    }
}
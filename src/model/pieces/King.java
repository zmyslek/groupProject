package model.pieces;

import model.GamePiece;
import model.Player;
import model.Position;
import board.GameBoard;

public class King implements GamePiece {
    private final Player player;

    public King(Player player) {
        this.player = player;
    }

    @Override
    public String getType() {
        return "King";
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public boolean isValidMove(Position from, Position to, GameBoard board) {
        int dx = Math.abs(to.getX() - from.getX());
        int dy = Math.abs(to.getY() - from.getY());
        return dx <= 1 && dy <= 1;
    }
}
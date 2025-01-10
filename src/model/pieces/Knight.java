package model.pieces;

import model.GamePiece;
import model.Player;
import model.Position;
import board.GameBoard;

public class Knight implements GamePiece {
    private final Player player;

    public Knight(Player player) {
        this.player = player;
    }

    @Override
    public String getType() {
        return "Knight";
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public boolean isValidMove(Position from, Position to, GameBoard board) {
        int dx = Math.abs(to.getX() - from.getX());
        int dy = Math.abs(to.getY() - from.getY());
        // Knight moves in L-shape (2 squares in one direction and 1 in another)
        return (dx == 2 && dy == 1) || (dx == 1 && dy == 2);
    }
}
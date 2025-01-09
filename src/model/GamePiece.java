package model;

import board.GameBoard;

public interface GamePiece {
    String getType();
    boolean isValidMove(Position from, Position to, GameBoard board);
    Player getPlayer();
}
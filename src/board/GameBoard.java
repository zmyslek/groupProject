package board;

import model.GamePiece;
import model.Player;
import model.Position;
import factory.GamePieceFactory;

public class GameBoard {
    private final GamePiece[][] board = new GamePiece[8][8];

    public GameBoard() {
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = null;
            }
        }
        setupInitialPosition();
    }

    private void setupInitialPosition() {
        // Setup kings
        setPiece(new Position(4, 0), GamePieceFactory.createPiece("king", Player.WHITE));
        setPiece(new Position(4, 7), GamePieceFactory.createPiece("king", Player.BLACK));

        // Setup queens
        setPiece(new Position(3, 0), GamePieceFactory.createPiece("queen", Player.WHITE));
        setPiece(new Position(3, 7), GamePieceFactory.createPiece("queen", Player.BLACK));
    }

    public GamePiece getPiece(Position pos) {
        return board[pos.getY()][pos.getX()];
    }

    public void setPiece(Position pos, GamePiece piece) {
        board[pos.getY()][pos.getX()] = piece;
    }

    public void movePiece(Position from, Position to) {
        GamePiece piece = getPiece(from);
        setPiece(from, null);
        setPiece(to, piece);
    }

    public boolean isValidPosition(Position pos) {
        return pos.getX() >= 0 && pos.getX() < 8 &&
                pos.getY() >= 0 && pos.getY() < 8;
    }
}
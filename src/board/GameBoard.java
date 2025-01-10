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
        // Setup Rooks
        setPiece(new Position(0, 0), GamePieceFactory.createPiece("rook", Player.WHITE));
        setPiece(new Position(7, 0), GamePieceFactory.createPiece("rook", Player.WHITE));
        setPiece(new Position(0, 7), GamePieceFactory.createPiece("rook", Player.BLACK));
        setPiece(new Position(7, 7), GamePieceFactory.createPiece("rook", Player.BLACK));

        // Setup Knights
        setPiece(new Position(1, 0), GamePieceFactory.createPiece("knight", Player.WHITE));
        setPiece(new Position(6, 0), GamePieceFactory.createPiece("knight", Player.WHITE));
        setPiece(new Position(1, 7), GamePieceFactory.createPiece("knight", Player.BLACK));
        setPiece(new Position(6, 7), GamePieceFactory.createPiece("knight", Player.BLACK));

        // Setup Bishops
        setPiece(new Position(2, 0), GamePieceFactory.createPiece("bishop", Player.WHITE));
        setPiece(new Position(5, 0), GamePieceFactory.createPiece("bishop", Player.WHITE));
        setPiece(new Position(2, 7), GamePieceFactory.createPiece("bishop", Player.BLACK));
        setPiece(new Position(5, 7), GamePieceFactory.createPiece("bishop", Player.BLACK));

        // Setup Queens
        setPiece(new Position(3, 0), GamePieceFactory.createPiece("queen", Player.WHITE));
        setPiece(new Position(3, 7), GamePieceFactory.createPiece("queen", Player.BLACK));

        // Setup Kings
        setPiece(new Position(4, 0), GamePieceFactory.createPiece("king", Player.WHITE));
        setPiece(new Position(4, 7), GamePieceFactory.createPiece("king", Player.BLACK));

        // Setup Pawns
        for(int i = 0; i < 8; i++) {
            setPiece(new Position(i, 1), GamePieceFactory.createPiece("pawn", Player.WHITE));
            setPiece(new Position(i, 6), GamePieceFactory.createPiece("pawn", Player.BLACK));
        }
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
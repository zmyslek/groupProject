import java.util.ArrayList;
import java.util.List;

public class GameBoard implements Subject {
  private static GameBoard instance;
  private Tile[][] board;
  private List<Observer> observers;
  private String currentPlayer;

  private GameBoard() {
    initializeBoard();
    observers = new ArrayList<>();
    currentPlayer = "Player 1";
  }

  public static GameBoard getInstance() {
    if (instance == null) {
      instance = new GameBoard();
    }
    return instance;
  }

  private void initializeBoard() {
    board = new Tile[8][8];
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        board[i][j] = new Tile();
      }
    }
  }

  public Tile getTile(int row, int col) {
    if (row >= 0 && row < board.length && col >= 0 && col < board[0].length) {
      return board[row][col];
    }
    throw new IllegalArgumentException("Invalid board position: (" + row + ", " + col + ")");
  }

  public void placePiece(int row, int col, GamePiece piece) {
    if (board[row][col].isEmpty()) {
      board[row][col].placePiece(piece);
      notifyObservers(piece.getOwner() + " placed " + piece.getType() + " at (" + row + ", " + col + ").");
      switchPlayer();
    } else {
      System.out.println("Tile at (" + row + ", " + col + ") is already occupied!");
    }
  }

  public void removePiece(int row, int col) {
    board[row][col].removePiece();
    notifyObservers("Piece removed from (" + row + ", " + col + ").");
  }

  private void switchPlayer() {
    currentPlayer = currentPlayer.equals("Player 1") ? "Player 2" : "Player 1";
    notifyObservers("It's " + currentPlayer + "'s turn.");
  }

  public void printBoard() {
    for (Tile[] row : board) {
      for (Tile tile : row) {
        tile.display();
      }
      System.out.println();
    }
  }

  @Override
  public void addObserver(Observer observer) {
    observers.add(observer);
  }

  @Override
  public void removeObserver(Observer observer) {
    observers.remove(observer);
  }

  @Override
  public void notifyObservers(String message) {
    for (Observer observer : observers) {
      observer.update(message);
    }
  }
}

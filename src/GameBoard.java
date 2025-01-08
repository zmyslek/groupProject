import java.util.ArrayList;
import java.util.List;

public class GameBoard implements Subject {
  private static GameBoard instance;
  private Tile[][] board;
  private final List<Observer> observers; // List of observers

  private GameBoard() {
    initializeBoard();
    observers = new ArrayList<>();
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

  public void placePiece(int row, int col, GamePiece piece) {
    if (board[row][col].isEmpty()) {
      board[row][col].placePiece(piece);
      notifyObservers("Placed " + piece + " at (" + row + ", " + col + ").");
    } else {
      System.out.println("Tile at (" + row + ", " + col + ") is already occupied!");
    }
  }

  public void removePiece(int row, int col) {
    if (!board[row][col].isEmpty()) {
      board[row][col].removePiece();
      notifyObservers("Removed piece from (" + row + ", " + col + ").");
    } else {
      System.out.println("Tile is already empty!");
    }
  }

  public void printBoard() {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        board[i][j].display();
        System.out.print(" ");
      }
      System.out.println();
    }
  }
}

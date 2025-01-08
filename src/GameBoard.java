public class GameBoard {
  // Static instance for Singleton
  private static GameBoard instance;

  // Board representation (2D array or list of tiles)
  private String[][] board;

  // Private constructor to prevent instantiation
  private GameBoard() {
    initializeBoard();
  }

  // Public method to get the single instance
  public static GameBoard getInstance() {
    if (instance == null) {
      instance = new GameBoard();
    }
    return instance;
  }

  // Initialize the board (e.g., 8x8 for chess or checkers)
  private void initializeBoard() {
    board = new String[8][8]; // Example for an 8x8 grid
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        board[i][j] = "-"; // Empty tile representation
      }
    }
  }

  // Method to print the board
  public void printBoard() {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        System.out.print(board[i][j] + " ");
      }
      System.out.println();
    }
  }

  // Other game board management methods...
  public void updateTile(int row, int col, String value) {
    board[row][col] = value;
  }
}

public class Main {
  public static void main(String[] args) {
    GameBoard board = GameBoard.getInstance();

    // Add an observer
    Observer consoleObserver = new ConsoleObserver();
    board.addObserver(consoleObserver);

    // Place initial pieces
    GamePiece king = new GamePiece("King", "Player 1");
    GamePiece queen = new GamePiece("Queen", "Player 2");
    board.placePiece(0, 0, king);
    board.placePiece(7, 7, queen);

    // Validate and execute a move
    int startRow = 0, startCol = 0, endRow = 1, endCol = 1;
    if (king.isValidMove(startRow, startCol, endRow, endCol, board)) {
      board.placePiece(endRow, endCol, king);
      board.removePiece(startRow, startCol);
    } else {
      System.out.println("Invalid move!");
    }

    // Print the board
    System.out.println("\nFinal Board:");
    board.printBoard();
  }
}

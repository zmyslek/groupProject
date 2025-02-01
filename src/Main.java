public class Main {
  public static void main(String[] args) {
    GameBoard board = GameBoard.getInstance();

    // Add an observer
    Observer consoleObserver = new ConsoleObserver();
    board.addObserver(consoleObserver);

    // Place initial pieces with correct move strategies
    GamePiece king = new GamePiece("King", "Player 1", new KingMoveStrategy());
    GamePiece bishop = new GamePiece("Bishop", "Player 2", new BishopMoveStrategy());

    board.placePiece(0, 0, king);
    board.placePiece(7, 7, bishop);

    // Validate and execute a move
    int startRow = 0, startCol = 0, endRow = 1, endCol = 1;
    if (king.getType().equals("King")) {
      board.getTile(startRow, startCol).getPiece().move(board.getTile(startRow, startCol), board.getTile(endRow, endCol));
      board.removePiece(startRow, startCol);
    } else {
      System.out.println("Invalid move!");
    }

    // Print the board
    System.out.println("\nFinal Board:");
    board.printBoard();
  }
}

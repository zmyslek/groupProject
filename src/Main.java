public class Main {
    public static void main(String[] args) {
        GameBoard board = GameBoard.getInstance();

        // Create and register an observer
        Observer consoleObserver = new ConsoleObserver();
        board.addObserver(consoleObserver);

        // Perform some actions
        board.placePiece(0, 0, new GamePiece("King"));
        board.placePiece(7, 7, new GamePiece("Queen"));
        board.removePiece(0, 0);

        // Print the board
        System.out.println("\nFinal Board:");
        board.printBoard();
    }
}

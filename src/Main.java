public class Main {
    public static void main(String[] args) {
        // Get the Singleton instance
        GameBoard board1 = GameBoard.getInstance();
        GameBoard board2 = GameBoard.getInstance();

        // Test if both instances are the same
        System.out.println(board1 == board2); // Should print true

        // Print the initial board
        board1.printBoard();

        // Update a tile and print the board again
        board1.updateTile(0, 0, "X");
        board1.printBoard();

        // Access the same instance through board2
        board2.updateTile(7, 7, "O");
        board2.printBoard();
    }
}

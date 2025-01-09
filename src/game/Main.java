package game;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ChessGame game = new ChessGame();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Chess!");
        System.out.println("Enter moves in format 'e2e4' or type 'quit' to exit");
        System.out.println("Commands:");
        System.out.println(" - 'undo': Take back last move");
        System.out.println(" - 'redo': Replay undone move");
        System.out.println(" - 'force': Toggle force mode (ignore turn order)");
        System.out.println(" - 'quit': Exit the game");
        System.out.println("\nPiece notation:");
        System.out.println(" - WHITE pieces are UPPERCASE (P, K, Q)");
        System.out.println(" - BLACK pieces are lowercase (p, k, q)");

        boolean forceMode = false;

        while (true) {
            System.out.print("\nEnter your move: ");
            String input = scanner.nextLine().trim().toLowerCase();

            switch (input) {
                case "quit":
                    System.out.println("Thanks for playing!");
                    return;
                case "undo":
                    game.undo();
                    break;
                case "redo":
                    game.redo();
                    break;
                case "force":
                    forceMode = !forceMode;
                    System.out.println("Force mode " + (forceMode ? "enabled" : "disabled"));
                    System.out.println("You can now move " + (forceMode ? "any piece" : "only your pieces"));
                    break;
                default:
                    game.handleMove(input, forceMode);
            }
        }
    }
}
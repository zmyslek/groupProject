package game;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ChessGame game = new ChessGame();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Chess!");
        System.out.println("Enter moves in format 'e2e4' or type 'quit' to exit");
        System.out.println("Commands: 'undo', 'redo', 'quit'");

        while (true) {
            System.out.print("\nEnter your move: ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("quit")) {
                break;
            } else if (input.equals("undo")) {
                game.undo();
            } else if (input.equals("redo")) {
                game.redo();
            } else {
                game.handleMove(input);
            }
        }

        scanner.close();
        System.out.println("Thanks for playing!");
    }
}
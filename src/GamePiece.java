public class GamePiece implements BoardComponent {
  private String type; // e.g., "King", "Queen", "Pawn"

  public GamePiece(String type) {
    this.type = type;
  }

  @Override
  public void display() {
    System.out.print(type.charAt(0)); // Display the first letter (e.g., K for King)
  }
}

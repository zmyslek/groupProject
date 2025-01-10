public class Tile {
  private GamePiece piece;

  public boolean isEmpty() {
    return piece == null;
  }

  public void placePiece(GamePiece piece) {
    this.piece = piece;
  }

  public void removePiece() {
    this.piece = null;
  }

  public GamePiece getPiece() {
    return piece;
  }

  public void display() {
    if (piece == null) {
      System.out.print("- ");
    } else {
      piece.display();
      System.out.print(" ");
    }
  }
}

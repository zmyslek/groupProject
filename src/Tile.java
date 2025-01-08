public class Tile implements BoardComponent {
  private GamePiece piece; // Can hold a GamePiece or be empty

  public Tile() {
    this.piece = null; // Default is empty
  }

  public void placePiece(GamePiece piece) {
    this.piece = piece; // Place a piece on the tile
  }

  public void removePiece() {
    this.piece = null; // Remove the piece
  }

  public boolean isEmpty() {
    return piece == null;
  }

  @Override
  public void display() {
    if (piece == null) {
      System.out.print("-"); // Empty tile representation
    } else {
      piece.display(); // Display the piece
    }
  }
}

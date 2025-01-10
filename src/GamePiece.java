public class GamePiece implements BoardComponent {
  private String type; // e.g., "King", "Queen", "Pawn"
  private String owner; // "Player 1" or "Player 2"

  public GamePiece(String type, String owner) {
    this.type = type;
    this.owner = owner;
  }

  public String getType() {
    return type;
  }

  public String getOwner() {
    return owner;
  }

  @Override
  public void display() {
    System.out.print(type.charAt(0)); // Display the first letter (e.g., K for King)
  }

  public boolean isValidMove(int startRow, int startCol, int endRow, int endCol, GameBoard board) {
    if (type.equals("King")) {
      if (Math.abs(startRow - endRow) <= 1 && Math.abs(startCol - endCol) <= 1) {
        Tile targetTile = board.getTile(endRow, endCol);
        return targetTile.isEmpty() || !targetTile.getPiece().getOwner().equals(this.owner);
      }
    }
    return false;
  }
}

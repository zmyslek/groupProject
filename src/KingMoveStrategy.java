public class KingMoveStrategy implements MoveStrategy {
  @Override
  public void move(Tile from, Tile to) {
    System.out.println("King moves one step in any direction.");
    to.placePiece(from.getPiece());
    from.removePiece();
  }
}

public class BishopMoveStrategy implements MoveStrategy {
  @Override
  public void move(Tile from, Tile to) {
    System.out.println("Bishop moves diagonally.");
    to.placePiece(from.getPiece());
    from.removePiece();
  }
}

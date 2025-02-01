public class GamePiece {
  private String type;
  private String owner;
  private MoveStrategy moveStrategy;

  public GamePiece(String type, String owner, MoveStrategy moveStrategy) {
    this.type = type;
    this.owner = owner;
    this.moveStrategy = moveStrategy;
  }

  public String getType() {
    return type;
  }

  public String getOwner() {
    return owner;
  }

  public void move(Tile from, Tile to) {
    moveStrategy.move(from, to);
  }

  public void display() {
    System.out.print(type.charAt(0)); // Display first letter of type (e.g., "K" for King)
  }
}

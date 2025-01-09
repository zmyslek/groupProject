package input;

import model.Position;
import java.util.Map;

public class FileInputAdapter implements MoveInput {
    private final Map<String, Integer> moveData;

    public FileInputAdapter(Map<String, Integer> moveData) {
        this.moveData = moveData;
    }

    @Override
    public Position getFrom() {
        return new Position(
                moveData.get("fromX"),
                moveData.get("fromY")
        );
    }

    @Override
    public Position getTo() {
        return new Position(
                moveData.get("toX"),
                moveData.get("toY")
        );
    }
}
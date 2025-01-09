package input;

import model.Position;

public class ConsoleInputAdapter implements MoveInput {
    private final String input;

    public ConsoleInputAdapter(String input) {
        this.input = input;
    }

    @Override
    public Position getFrom() {
        int[] pos = parsePosition(input.substring(0, 2));
        return new Position(pos[0], pos[1]);
    }

    @Override
    public Position getTo() {
        int[] pos = parsePosition(input.substring(2, 4));
        return new Position(pos[0], pos[1]);
    }

    private int[] parsePosition(String pos) {
        int x = pos.charAt(0) - 'a';
        int y = Character.getNumericValue(pos.charAt(1)) - 1;
        return new int[]{x, y};
    }
}
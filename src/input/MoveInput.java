package input;

import model.Position;

public interface MoveInput {
    Position getFrom();
    Position getTo();
}
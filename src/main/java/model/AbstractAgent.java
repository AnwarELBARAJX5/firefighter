package model;

import model.BoardContext;
import util.Position;


public abstract class AbstractAgent {
    protected Position position;

    public AbstractAgent(Position startPosition) {
        this.position = startPosition;
    }

    public Position getPosition() {
        return position;
    }
    public abstract void update(BoardContext context);
    public abstract ModelElement getType();
}
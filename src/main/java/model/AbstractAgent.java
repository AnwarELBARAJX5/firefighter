package model;

import util.Position;


public abstract class AbstractAgent implements Element{
    protected Position position;

    public AbstractAgent(Position startPosition) {
        this.position = startPosition;
    }

    public Position getPosition() {
        return position;
    }
    public abstract void update(BoardContext context);
    public abstract ModelElement getType();
    public abstract boolean isBlocking();
}
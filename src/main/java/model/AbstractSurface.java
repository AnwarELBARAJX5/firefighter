package model;

import util.Position;

public abstract class AbstractSurface implements Element{
    protected Position position;

    public AbstractSurface(Position startPosition) {
        this.position = startPosition;
    }
    public abstract ModelElement getType();
}

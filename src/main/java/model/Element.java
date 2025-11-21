package model;

import util.Position;

public interface Element {
    public Position getPosition();
    public abstract ModelElement getType();
}

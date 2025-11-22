package model;

import util.Position;

public class Mountain extends AbstractSurface{
    public Mountain(Position startPosition){
        super(startPosition);
    }
    public Position getPosition() {
        return position;
    }

    @Override
    public ModelElement getType() {
        return ModelElement.MOUNTAIN;
    }
    public boolean isTraversable() {
        return false;
    }

    @Override
    public boolean isFlammable() {
        return false;
    }
}

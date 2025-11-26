package model;

import util.Position;

public class Road extends AbstractSurface{
    public Road(Position position){
        super(position);
    }
    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public ModelElement getType() {
        return ModelElement.ROAD;
    }

    @Override
    public boolean isTraversable() {
        return true;
    }

    @Override
    public boolean canAccept(ModelElement type) {
        return type != ModelElement.FIRE;
    }
}

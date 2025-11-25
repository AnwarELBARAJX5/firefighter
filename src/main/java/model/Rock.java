package model;

import util.Position;

public class Rock extends AbstractSurface{
    private int resistance=2; //car le feu attaque tous les 2 tours
    public Rock(Position position){
        super(position);
    }
    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public ModelElement getType() {
        return ModelElement.ROCK;
    }

    @Override
    public boolean isTraversable() {
        return true;
    }

    @Override
    public boolean canAccept(ModelElement type) {
        if (type == ModelElement.FIRE) {
            if (resistance > 0) {
                resistance--;
                return false;
            }
            return true;
        }
        return true;
    }
}

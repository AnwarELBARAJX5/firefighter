package model;

import util.Position;
import java.util.List;

public class Fire extends AbstractAgent {
    public Fire(Position startPosition){
        super(startPosition);
    }

    @Override
    public boolean isBlocking() {
        return false;
    }

    @Override
    public void update(BoardContext context) {
        if (context.stepNumber() % 2 == 0) {
            List<Position> myNeighbours = context.getNeighbors(position);
            for (Position neighborsPos : myNeighbours) {
                context.spawn(ModelElement.FIRE,neighborsPos);
            }

        }
    }

    @Override
    public ModelElement getType() {
        return ModelElement.FIRE;
    }
}
package model;

import util.Position;

import java.util.List;
import java.util.Random;
public class Cloud extends AbstractAgent{
    Random randomNumbers = new Random();
    public Cloud(Position startPosition){
        super(startPosition);
    }

    @Override
    public ModelElement getType() {
        return ModelElement.CLOUD;
    }

    @Override
    public void update(BoardContext context) {
        this.position=context.randomPosition();
        List<Position> neighbors=context.getNeighbors(this.position);
        for(Position neighborsPos:neighbors){
            if(context.getFirePositions().contains(neighborsPos)){
                context.extinguish(neighborsPos);
            }
        }

    }
}

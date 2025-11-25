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
    public boolean isBlocking() {
        return false;
    }

    @Override
    public void update(BoardContext context) {
        List<Position> neighbors = context.getNeighbors(this.position);
        if (!neighbors.isEmpty()) {
            this.position = neighbors.get(randomNumbers.nextInt(neighbors.size()));
        }
        List<Position> newNeighbors = context.getNeighbors(this.position);
        for(Position neighborPos : newNeighbors){
            if(context.getFirePositions().contains(neighborPos)){
                context.kill(ModelElement.FIRE,neighborPos);
            }
        }
        if(context.getFirePositions().contains(this.position)){
            context.kill(ModelElement.FIRE,this.position);
        }
    }
}

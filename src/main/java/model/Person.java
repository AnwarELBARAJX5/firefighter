package model;

import util.Position;

import java.util.List;
import java.util.Random;

public class Person extends AbstractAgent{
    Random randomNumbers = new Random();
    public Person(Position position){
        super(position);
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
                context.kill(neighborPos);
            }
        }
        if(context.getFirePositions().contains(this.position)){
            context.kill(this.position);
        }
    }

    @Override
    public ModelElement getType() {
        return ModelElement.PERSON;
    }

    @Override
    public boolean isBlocking() {
        return false;
    }
}

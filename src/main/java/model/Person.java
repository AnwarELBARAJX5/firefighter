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
            Position target = neighbors.get(randomNumbers.nextInt(neighbors.size()));
            if (!context.isOccupied(target)) {
                this.position = target;
            }
        }
    }

    @Override
    public ModelElement getType() {
        return ModelElement.PERSON;
    }

    @Override
    public boolean isBlocking() {
        return true;
    }
}

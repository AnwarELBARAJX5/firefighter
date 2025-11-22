package model;

import util.Position;

import java.util.List;
import util.TargetStrategy;

public class MotorizedFireFighter extends AbstractAgent{
    TargetStrategy targetStrategy=new TargetStrategy();
    public MotorizedFireFighter(Position startPosition){
        super(startPosition);
    }
    @Override
    public void update(BoardContext context) {
        Position firstFirefighterPosition = targetStrategy.neighborClosestToFire(this.position,context.getFirePositions(), context.getNeighborsMap());
        this.position=firstFirefighterPosition;
        context.extinguish(this.position);
        List<Position> firstNeighbors=context.getNeighbors(this.position);
        for(Position neighborsPos:firstNeighbors){
            if(context.getFirePositions().contains(neighborsPos)){
                context.extinguish(neighborsPos);
            }
        }
        Position secondFirefighterPosition = targetStrategy.neighborClosestToFire(this.position,context.getFirePositions(), context.getNeighborsMap());
        if (!secondFirefighterPosition .equals(this.position) && context.isOccupied(secondFirefighterPosition )) {
        } else {
            this.position = secondFirefighterPosition ;
        }
        context.extinguish(this.position);
        List<Position> secondNeighbors=context.getNeighbors(this.position);
        for(Position neighborsPos:secondNeighbors){
            if(context.getFirePositions().contains(neighborsPos)){
                context.extinguish(neighborsPos);
            }
        }

    }

    @Override
    public ModelElement getType() {
        return ModelElement.MOTORIZEDFIREFIGHTER;
    }
}

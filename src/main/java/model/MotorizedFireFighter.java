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
    public boolean isBlocking() {
        return true;
    }

    @Override
    public void update(BoardContext context) {
        step(context);
        step(context);

    }
    private void step(BoardContext context) {
        Position target = targetStrategy.neighborClosestToFire(
                this.position,
                context.getFirePositions(),
                context.getNeighborsMap()
        );
        if (!target.equals(this.position) && !context.isOccupied(target)) {
            this.position = target;
        }
        context.extinguish(this.position);
        List<Position> neighbors = context.getNeighbors(this.position);
        for (Position neighbor : neighbors) {
            if (context.getFirePositions().contains(neighbor)) {
                context.extinguish(neighbor);
            }
        }
    }

    @Override
    public ModelElement getType() {
        return ModelElement.MOTORIZEDFIREFIGHTER;
    }
}

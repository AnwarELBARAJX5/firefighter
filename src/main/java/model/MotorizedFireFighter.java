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
                context.getPositions(ModelElement.FIRE),
                context.getNeighborsMap()
        );
        if (!target.equals(this.position) && !context.isOccupied(target)) {
            this.position = target;
        }
        context.kill(ModelElement.FIRE,this.position);
        List<Position> neighbors = context.getNeighbors(this.position);
        for (Position neighbor : neighbors) {
            if (context.getPositions(ModelElement.FIRE).contains(neighbor)) {
                context.kill(ModelElement.FIRE,neighbor);
            }
        }
    }

    @Override
    public ModelElement getType() {
        return ModelElement.MOTORIZEDFIREFIGHTER;
    }
}

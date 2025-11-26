package model;

import util.Position;
import util.TargetStrategy;

import java.util.List;
import java.util.Random;
import java.util.Set;

public class Doctor extends AbstractAgent {
    private final TargetStrategy strategy = new TargetStrategy();
    Random randomNumbers=new Random();

    public Doctor(Position position) {
        super(position);
    }

    @Override
    public void update(BoardContext context) {
        Set<Position> targets = context.getPositions(ModelElement.VIRUS);
        Position lastPos=this.position;
        Position targetPosition = strategy.neighborClosestToFire(
                this.position,
                targets,
                context.getNeighborsMap()
        );
        if (!targetPosition.equals(this.position) && !context.isOccupied(targetPosition)) {
            this.position = targetPosition;
        }

        List<ModelElement> contentOnCase = context.getState(this.position);

        if (contentOnCase.contains(ModelElement.VIRUS) && randomNumbers.nextDouble()<0.5 ) {
            context.kill(ModelElement.VIRUS, this.position);
            context.spawn(ModelElement.DOCTOR,lastPos);

        }
    }

    @Override
    public ModelElement getType() {
        return ModelElement.DOCTOR;
    }

    @Override
    public boolean isBlocking() {
        return true;
    }
}
package model;
import util.Position;
import util.TargetStrategy;
import java.util.*;


public class FireFighter extends AbstractAgent{
    private final TargetStrategy targetStrategy = new TargetStrategy();
    public FireFighter(Position startPosition) {
        super(startPosition);
    }

    @Override
    public boolean isBlocking() {
        return true;
    }

    @Override
    public void update(BoardContext context) {
        Position newFirefighterPosition = targetStrategy.neighborClosestToFire(this.position,context.getPositions(ModelElement.FIRE), context.getNeighborsMap());
        if (!newFirefighterPosition .equals(this.position) && context.isOccupied(newFirefighterPosition )) {
        } else {
            this.position = newFirefighterPosition ;
        }
        context.kill(ModelElement.FIRE,this.position);
        List<Position> neighbors=context.getNeighbors(this.position);
        for(Position neighborsPos:neighbors){
                context.kill(ModelElement.FIRE,neighborsPos);

        }
    }

    @Override
    public ModelElement getType() {
        return ModelElement.FIREFIGHTER;
    }
}

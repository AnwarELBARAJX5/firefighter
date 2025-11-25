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
        Position newFirefighterPosition = targetStrategy.neighborClosestToFire(this.position,context.getFirePositions(), context.getNeighborsMap());
        if (!newFirefighterPosition .equals(this.position) && context.isOccupied(newFirefighterPosition )) {
        } else {
            this.position = newFirefighterPosition ;
        }
        context.kill(this.position);
        List<Position> neighbors=context.getNeighbors(this.position);
        for(Position neighborsPos:neighbors){
            if(context.getFirePositions().contains(neighborsPos)){
                context.kill(neighborsPos);
            }
        }
    }

    @Override
    public ModelElement getType() {
        return ModelElement.FIREFIGHTER;
    }
}

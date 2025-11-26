package model;

import util.Position;
import util.TargetStrategy;

import java.util.List;
import java.util.Random;
import java.util.Set;

public class Virus extends AbstractAgent {
    private final TargetStrategy strategy = new TargetStrategy();
    Random randomNumber=new Random();
    public Virus(Position position) {
        super(position);
    }

    @Override
    public void update(BoardContext context) {
        Set<Position> targets = context.getPositions(ModelElement.PERSON);
        Position nextPos = strategy.neighborClosestToFire(
                this.position,
                targets,
                context.getNeighborsMap()
        );
        if (!nextPos.equals(this.position) && !context.isOccupied(nextPos)) {
            this.position = nextPos;
        }


            List<Position> neighbors = context.getNeighbors(this.position);
            for (Position neighbor : neighbors) {
                List<ModelElement> content = context.getState(neighbor);
                if (content.contains(ModelElement.PERSON)) {
                    context.kill(ModelElement.PERSON,neighbor);
                    context.spawn(ModelElement.VIRUS, neighbor);
                }else if (content.contains(ModelElement.DOCTOR) && randomNumber.nextDouble()<0.1) {
                    context.kill(ModelElement.DOCTOR, neighbor);
                    context.spawn(ModelElement.PERSON, neighbor);
                }

        }
    }

    @Override
    public ModelElement getType() {
        return ModelElement.VIRUS;
    }

    @Override
    public boolean isBlocking() {
        return false; // On peut marcher sur un virus (pour le soigner)
    }
}
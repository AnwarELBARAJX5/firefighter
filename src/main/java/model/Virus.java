package model;

import util.Position;
import java.util.List;

public class Virus extends AbstractAgent {

    public Virus(Position position) {
        super(position);
    }

    @Override
    public void update(BoardContext context) {
        if (context.stepNumber() % 2 == 0) {
            List<Position> neighbors = context.getNeighbors(this.position);
            for (Position neighbor : neighbors) {
                List<ModelElement> content = context.getState(neighbor);
                if (content.contains(ModelElement.PERSON)) {
                    context.kill(ModelElement.PERSON,neighbor);
                    context.spawn(ModelElement.VIRUS, neighbor);
                }
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
package model;

import util.Position;
import util.TargetStrategy;
import java.util.Set;

public class Doctor extends AbstractAgent {
    private final TargetStrategy strategy = new TargetStrategy();

    public Doctor(Position position) {
        super(position);
    }

    @Override
    public void update(BoardContext context) {
        // 1. Chercher les cibles (les virus)
        // (Il faudra ajouter cette méthode dans BoardContext !)
        Set<Position> targets = context.getVirusPositions();

        // 2. Calculer le chemin
        Position target = strategy.neighborClosestToFire(
                this.position,
                targets,
                context.getNeighborsMap()
        );

        // 3. Se déplacer
        if (!target.equals(this.position) && !context.isOccupied(target)) {
            this.position = target;
        }

        // 4. Action : Soigner (si on est sur un virus)
        if (context.getState(this.position).contains(ModelElement.VIRUS)) {
            context.kill(ModelElement.VIRUS,this.position); // Élimine le virus
            // Optionnel : Le virus redevient une personne saine ?
            // context.spawn(ModelElement.PERSON, this.position);
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
package model;

import java.util.List;
import util.Position;
import java.util.*;
import java.util.stream.Collectors;


public abstract class AbstractBoard implements Board<List<ModelElement>>,BoardContext{
    final int columnCount;
    final int rowCount;
    private final Map<ModelElement, Integer> initialConfig;
    private final List<AbstractAgent> agents = new ArrayList<>();
    private final List<AbstractSurface> surfaces = new ArrayList<>();
    private final List<AbstractAgent> agentsToAdd = new ArrayList<>();
    private final List<AbstractAgent> agentsToRemove = new ArrayList<>();
    private final Set<Position> firePositions = new HashSet<>();
    private final Set<Position> fireToCreate = new HashSet<>();
    Map<Position, List<Position>> neighbors = new HashMap();
    final Position[][] positions;
    private int step = 0;
    private final Random randomGenerator = new Random();
    public AbstractBoard(int columnCount, int rowCount, Map<ModelElement, Integer> config) {
        this.columnCount = columnCount;
        this.rowCount = rowCount;
        this.initialConfig = config;

        this.positions = new Position[rowCount][columnCount];
        for (int column = 0; column < columnCount; column++)
            for (int row = 0; row < rowCount; row++)
                positions[row][column] = new Position(row, column);
        initializeNeighbors();
        initializeElements();
    }
    public void initializeElements() {
        agents.clear();
        firePositions.clear();
        surfaces.clear();
        for (Map.Entry<ModelElement, Integer> entry : initialConfig.entrySet()) {
            ModelElement type = entry.getKey();
            int count = entry.getValue();
            for (int i = 0; i < count; i++) {
                Element element = ElementFactory.create(type, randomPosition());
                if (element instanceof AbstractAgent agent) {
                    addAgent(agent);
                } else if (element instanceof AbstractSurface surface) {
                    addSurface(surface);
                }
            }
        }

    }
    private void addAgent(AbstractAgent agent) {
        agents.add(agent);
        if (agent instanceof Fire) {
            firePositions.add(agent.getPosition());
        }
    }
    private void addSurface(AbstractSurface surface){
        surfaces.add(surface);
    }

    public Position randomPosition() {
        return new Position(randomGenerator.nextInt(rowCount), randomGenerator.nextInt(columnCount));
    }

    @Override
    public List<ModelElement> getState(Position position) {
        List<ModelElement> result = agents.stream()
                .filter(a -> a.getPosition().equals(position))
                .map(Element::getType)
                .collect(Collectors.toList());
        surfaces.stream()
                .filter(s -> s.getPosition().equals(position))
                .map(Element::getType)
                .forEach(result::add);

        return result;
    }

    @Override
    public int rowCount() {
        return rowCount;
    }

    @Override
    public int columnCount() {
        return columnCount;
    }

    public List<Position> updateToNextGeneration() {
        List<Position> modifiedPositions = new ArrayList<>();
        fireToCreate.clear();
        agentsToAdd.clear();
        agentsToRemove.clear();
        for (AbstractAgent agent : agents) {
            Position oldPos = agent.getPosition();
            agent.update(this);
            if (!agent.getPosition().equals(oldPos)) {
                modifiedPositions.add(oldPos);
                modifiedPositions.add(agent.getPosition());
            }
        }
        agents.removeAll(agentsToRemove);
        agents.addAll(agentsToAdd);
        firePositions.clear();
        for (Element agent : agents) {
            if (agent instanceof Fire) firePositions.add(agent.getPosition());
        }
        for (AbstractAgent a : agentsToAdd) modifiedPositions.add(a.getPosition());
        for (AbstractAgent a : agentsToRemove) modifiedPositions.add(a.getPosition());
        step++;
        return modifiedPositions;
    }




    @Override
    public int stepNumber() {
        return step;
    }

    @Override
    public void reset() {
        step = 0;
        initializeElements();
    }

    @Override
    public void kill(ModelElement type, Position position) {
        for (AbstractAgent agent : agents) {
            if (agent.getPosition().equals(position) && agent.getType() == type) {
                agentsToRemove.add(agent);
            }
        }
    }

    @Override
    public void spawn(ModelElement type, Position position) {
        for (AbstractAgent a : agentsToAdd) {
            if (a.getPosition().equals(position) && a.getType() == type) return;
        }
        for (AbstractAgent a : agents) {
            if (a.getPosition().equals(position) && a.getType() == type) return;
        }
        for (AbstractSurface surface : surfaces) {
            if (surface.getPosition().equals(position)) {
                if (!surface.canAccept(type)) {
                    return;
                }
                break;
            }
        }
        Element e = ElementFactory.create(type, position);
        if (e instanceof AbstractAgent agent) {
            agentsToAdd.add(agent);
        }
    }

    @Override
    public void setState(List<ModelElement> state, Position position) {
        agents.removeIf(a -> a.getPosition().equals(position));
        surfaces.removeIf(s -> s.getPosition().equals(position));
        firePositions.remove(position);
        for (ModelElement type : state) {
            Element element = ElementFactory.create(type, position);
            if (element instanceof AbstractAgent agent) {
                addAgent(agent);
            } else if (element instanceof AbstractSurface surface) {
                addSurface(surface);
            }
        }
    }

    @Override
    public Map<Position, List<Position>> getNeighborsMap() {
        Map<Position, List<Position>> filteredMap = new HashMap<>();
        for (Position p : this.neighbors.keySet()) {
            filteredMap.put(p, getNeighbors(p));
        }

        return filteredMap;
    }
    @Override
    public List<Position> getNeighbors(Position p) {
        List<Position> allNeighbors = this.neighbors.get(p);
        List<Position> validNeighbors = new ArrayList<>();
        for (Position neighbor : allNeighbors) {
            boolean blocked = false;
            for (AbstractSurface surface : surfaces) {
                if (surface.getPosition().equals(neighbor) && !surface.isTraversable()) {
                    blocked = true;
                    break;
                }
            }
            if (!blocked) {
                validNeighbors.add(neighbor);
            }
        }
        return validNeighbors;
    }



    @Override
    public Set<Position> getFirePositions() {
        return new HashSet<>(firePositions);
    }

    @Override
    public boolean isOccupied(Position position) {
        for (AbstractAgent agent : agents) {
            if (agent.getPosition().equals(position)&&agent.isBlocking()) {

                return true;
            }
        }
        return false;
    }
    @Override
    public Set<Position> getPositions(ModelElement type) {
        Set<Position> positions = new HashSet<>();
        for (AbstractAgent agent : agents) {
            if (agent.getType() == type) {
                positions.add(agent.getPosition());
            }
        }

        return positions;
    }
    public abstract void initializeNeighbors();
}


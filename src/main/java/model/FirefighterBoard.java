package model;

import util.Position;
import util.TargetStrategy;
import java.util.*;


public class FirefighterBoard implements Board<List<ModelElement>>,BoardContext{
  private final int columnCount;
  private final int rowCount;
  private final int initialFireCount;
  private final int initialFirefighterCount;
  private final int initialCloudCount;
  private final List<AbstractAgent> agents = new ArrayList<>();
  private final List<AbstractAgent> agentsToAdd = new ArrayList<>();
  private final List<AbstractAgent> agentsToRemove = new ArrayList<>();
  private final Set<Position> firePositions = new HashSet<>();
  private Map<Position, List<Position>> neighbors = new HashMap();
  private final Position[][] positions;
  private int step = 0;
  private final Random randomGenerator = new Random();

  public FirefighterBoard(int columnCount, int rowCount, int initialFireCount, int initialFirefighterCount,int initialCloudCount) {
    this.columnCount = columnCount;
    this.rowCount = rowCount;
    this.positions = new Position[rowCount][columnCount];
    for (int column = 0; column < columnCount; column++)
      for (int row = 0; row < rowCount; row++)
        positions[row][column] = new Position(row, column);
    for (int column = 0; column < columnCount; column++)
      for (int row = 0; row < rowCount; row++) {
        List<Position> list = new ArrayList<>();
        if (row > 0) list.add(positions[row - 1][column]);
        if (column > 0) list.add(positions[row][column - 1]);
        if (row < rowCount - 1) list.add(positions[row + 1][column]);
        if (column < columnCount - 1) list.add(positions[row][column + 1]);
        neighbors.put(positions[row][column], list);
      }
    this.initialFireCount = initialFireCount;
    this.initialFirefighterCount = initialFirefighterCount;
    this.initialCloudCount=initialCloudCount;
    initializeElements();
  }

  public void initializeElements() {
    agents.clear();
    firePositions.clear();
    for (int index = 0; index < initialFireCount; index++)
      addAgent(new Fire(randomPosition()));
    for (int index = 0; index < initialFirefighterCount; index++)
      addAgent(new FireFighter(randomPosition()));
    for (int index = 0; index < initialCloudCount; index++)
      addAgent(new Cloud(randomPosition()));
  }
  private void addAgent(AbstractAgent agent) {
    agents.add(agent);
    if (agent instanceof Fire) {
      firePositions.add(agent.getPosition());
    }
  }

  public Position randomPosition() {
    return new Position(randomGenerator.nextInt(rowCount), randomGenerator.nextInt(columnCount));
  }

  @Override
  public List<ModelElement> getState(Position position) {
    List<ModelElement> result = new ArrayList<>();
    for (FireFighter firefighter : firefighters) {
      if (firefighter.getPosition().equals(position)) {
        result.add(ModelElement.FIREFIGHTER);
        break;
      }
    }
    for (Fire fire : fires) {
      if (fire.getPosition().equals(position)) {
        result.add(ModelElement.FIRE);
        break;
      }
    }
    for (Cloud cloud : clouds) {
      if (cloud.getPosition().equals(position)) {
        result.add(ModelElement.CLOUD);
        break;
      }
    }
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
    for (AbstractAgent agent : agents) {
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
  public void extinguish(Position position) {
    for (AbstractAgent agent : agents) {
      if (agent.getPosition().equals(position) && agent instanceof Fire) {
        agentsToRemove.add(agent);
      }
    }
  public void createFire(Position position) {firesToCreate.add(position);}

  @Override
  public void setState(List<ModelElement> state, Position position) {
    fires.removeIf(fire -> fire.getPosition().equals(position));
    firefighters.removeIf(ff -> ff.getPosition().equals(position));
    clouds.removeIf(cloud -> cloud.getPosition().equals(position));

    for (ModelElement element : state) {
      switch (element) {
        case FIRE -> fires.add(new Fire(position));
        case FIREFIGHTER -> firefighters.add(new FireFighter(position));
        case CLOUD -> clouds.add(new Cloud(position));
      }
    }
  }
  public Map<Position, List<Position>> getNeighborsMap() {
    return this.neighbors;
  }
  @Override
  public List<Position> getNeighbors(Position p) {
    return this.neighbors.get(p);
  }

  @Override
  public Set<Position> getFirePositions() {
    Set<Position> positions = new HashSet<>();
    for (Fire fire : fires) {
      positions.add(fire.getPosition());
    }
    return positions;
  }

}
package model;

import util.Position;
import util.TargetStrategy;
import java.util.*;
import java.util.stream.Collectors;

import static view.ViewElement.MOTORIZEDFIREFIGHTER;


public class FirefighterBoard implements Board<List<ModelElement>>,BoardContext{
  private final int columnCount;
  private final int rowCount;
  private final int initialFireCount;
  private final int initialFirefighterCount;
  private final int initialCloudCount;
  private final int initialMotorizedFireFighterCount;
  private final int initialMountainCount;
  private final int initialRoadCount;
  private final int initialRockCount;
  private final List<AbstractAgent> agents = new ArrayList<>();
  private final List<AbstractSurface> surfaces = new ArrayList<>();
  private final List<AbstractAgent> agentsToAdd = new ArrayList<>();
  private final List<AbstractAgent> agentsToRemove = new ArrayList<>();
  private final Set<Position> firePositions = new HashSet<>();
  private final Set<Position> fireToCreate = new HashSet<>();
  private Map<Position, List<Position>> neighbors = new HashMap();
  private final Position[][] positions;
  private int step = 0;
  private final Random randomGenerator = new Random();

  public FirefighterBoard(int columnCount, int rowCount, int initialFireCount, int initialFirefighterCount,int initialCloudCount,int initialMotorizedFireFighterCount,int initialMountainCount,int initialRoadCount,int initialRockCount) {
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
    this.initialMotorizedFireFighterCount=initialMotorizedFireFighterCount;
    this.initialMountainCount=initialMountainCount;
    this.initialRoadCount=initialRoadCount;
    this.initialRockCount=initialRockCount;
    initializeElements();
  }

  public void initializeElements() {
    agents.clear();
    firePositions.clear();
    surfaces.clear();
    for (int index = 0; index < initialFireCount; index++)
      addAgent(new Fire(randomPosition()));
    for (int index = 0; index < initialFirefighterCount; index++)
      addAgent(new FireFighter(randomPosition()));
    for (int index = 0; index < initialCloudCount; index++)
      addAgent(new Cloud(randomPosition()));
    for (int index = 0; index < initialMotorizedFireFighterCount; index++)
      addAgent(new MotorizedFireFighter(randomPosition()));
    for (int index = 0; index < initialMountainCount; index++)
      addSurface(new Mountain(randomPosition()));
    for (int index = 0; index < initialRoadCount; index++)
      addSurface(new Road(randomPosition()));
    for (int index = 0; index < initialRockCount; index++)
      addSurface(new Rock(randomPosition()));


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
  public void extinguish(Position position) {
    for (AbstractAgent agent : agents) {
      if (agent.getPosition().equals(position) && agent instanceof Fire) {
        agentsToRemove.add(agent);
      }
    }
  }

  @Override
  public void createFire(Position position) {
    if (firePositions.contains(position) || fireToCreate.contains(position)) {
      return;
    }

    boolean canIgnite = true;
    for (AbstractSurface surface : surfaces) {
      if (surface.getPosition().equals(position)) {
        canIgnite = surface.tryToIgnite();
        break;
      }
    }

    if (canIgnite) {
      AbstractAgent newFire = new Fire(position);
      agentsToAdd.add(newFire);
      fireToCreate.add(position);
    }
  }

  @Override
  public void setState(List<ModelElement> state, Position position) {
      agents.removeIf(a -> a.getPosition().equals(position));
      surfaces.removeIf(s -> s.getPosition().equals(position));
      firePositions.remove(position);
      for (ModelElement element : state) {
        switch (element) {
          case FIRE -> addAgent(new Fire(position));
          case FIREFIGHTER -> addAgent(new FireFighter(position));
          case CLOUD -> addAgent(new Cloud(position));
          case MOTORIZEDFIREFIGHTER -> addAgent(new MotorizedFireFighter(position));
          case MOUNTAIN -> addSurface(new Mountain(position));
          case ROAD -> addSurface(new Road(position));
          case ROCK -> addSurface(new Rock(position));
        }
      }
  }

  public Map<Position, List<Position>> getNeighborsMap() {
    return this.neighbors;
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

}
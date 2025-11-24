package model;

import java.util.List;
package model;

import util.Position;
import util.TargetStrategy;
import java.util.*;
import java.util.stream.Collectors;
import static view.ViewElement.MOTORIZEDFIREFIGHTER;


public class AbstractBoard implements Board<List<ModelElement>>,BoardContext{
    private final int columnCount;
    private final int rowCount;
    private  Map<ModelElement, Integer> initialConfig=new HashMap<>();
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
}

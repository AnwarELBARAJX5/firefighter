package model;

import util.Position;
import util.TargetStrategy;
import java.util.*;


public interface BoardContext {
	public int stepNumber();
	List<Position> getNeighbors(Position p);
	public Map<Position, List<Position>> getNeighborsMap() ;
	Set<Position> getFirePositions();
	public void kill(ModelElement type,Position position);
	public void  spawn(ModelElement type,Position position);
	public int rowCount();
	public int columnCount();
	public Position randomPosition();
	boolean isOccupied(Position position);
	List<ModelElement> getState(Position position);
	Set<Position> getPositions(ModelElement type);

}
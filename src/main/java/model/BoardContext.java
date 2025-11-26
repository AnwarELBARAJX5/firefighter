package model;

import util.Position;
import util.TargetStrategy;
import java.util.*;


public interface BoardContext {
	int stepNumber();
	List<Position> getNeighbors(Position p);
	Map<Position, List<Position>> getNeighborsMap() ;
	Set<Position> getFirePositions();
	void kill(ModelElement type,Position position);
	void  spawn(ModelElement type,Position position);
	int rowCount();
	int columnCount();
	Position randomPosition();
	boolean isOccupied(Position position);
	List<ModelElement> getState(Position position);
	Set<Position> getPositions(ModelElement type);

}
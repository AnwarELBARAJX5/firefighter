package model;

import util.Position;
import util.TargetStrategy;
import java.util.*;


public interface BoardContext {
	public List<Position> update();
	public int stepNumber();
	public void extinguish(Position position);
	public void  createFire(Position position);

}
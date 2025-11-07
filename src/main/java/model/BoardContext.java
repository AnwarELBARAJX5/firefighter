public interface BoardContext {
	public List<Position> update();
	public int stepNumber();
	public void extinguish(Position position);
	public void  createFire(Position);

}
import java.awt.Graphics;

public interface Snake {

	public boolean hasCollided(BoardDot d);
	
	public void paintSnake(Graphics g);
	
	public void setDirection(Direction d);
	
	public Direction getDirection();
	
	public BoardDot getHead();
	
	public void addHead(BoardDot d);
	
	public void removeTail();

}

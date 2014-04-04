import java.awt.Graphics;
import java.util.LinkedList;

abstract class BasicSnake implements Snake {
	
	protected LinkedList<BoardDot> snake;
	private Direction direction;

	public BoardDot getHead() {
		return snake.getFirst();
	}
	
	public void addHead(BoardDot d) {
		snake.addFirst(d);
	}
	
	public void removeTail() {
		snake.removeLast();
	}
	
	public boolean addNewHead() {
		BoardDot newHead = new BoardDot(snake.getFirst());
		snake.addFirst(newHead);
		if (direction == Direction.UP) {
			return newHead.decY();
		} if (direction == Direction.DOWN) {
			return newHead.incY();
		} if (direction == Direction.LEFT) {
			return newHead.decX();
		} if (direction == Direction.RIGHT) {
			return newHead.incX();
		}
		return false;
	}
	
	public boolean hasCollided(BoardDot d) {
		for (BoardDot body : snake) {
			if (d.equals(body)) {
				return true;
			}
		}
		return false;
	}
	
	public void setDirection(Direction d) {
		this.direction = d;
	}
	public Direction getDirection() {
		return this.direction;
	}
	
}

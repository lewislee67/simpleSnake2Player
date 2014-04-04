import java.awt.Graphics;
import java.util.LinkedList;

public class Snake1 extends BasicSnake {
	
	public Snake1() {
		snake = new LinkedList<BoardDot>();
		for (int i = 0; i < 3; i++) {
			addHead(new BoardDot(i, Board.BOARD_HEIGHT/4));
			setDirection(Direction.RIGHT);
		}
	}

	public void paintSnake(Graphics g) {
		for (BoardDot d : snake) {
			d.paintDot(g, DotType.SNAKE1);
		}
	}
}

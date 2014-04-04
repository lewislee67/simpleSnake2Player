import java.awt.Graphics;
import java.util.LinkedList;

public class Snake2 extends BasicSnake {
	
	public Snake2() {
		snake = new LinkedList<BoardDot>();
		for (int i = 0; i < 3; i++) {
			snake.addFirst(new BoardDot(i, Board.BOARD_HEIGHT*3/4));
			setDirection(Direction.RIGHT);
		}
	}

	public void paintSnake(Graphics g) {
		for (BoardDot d : snake) {
			d.paintDot(g, DotType.SNAKE2);
		}
	}
}

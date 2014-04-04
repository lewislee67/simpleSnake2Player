import java.awt.Graphics;

public class BoardDot {

	private int x;
	private int y;
	
	public BoardDot(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public BoardDot(BoardDot d) {
		this.x = d.getX();
		this.y = d.getY();
	}
	
	public boolean equals(BoardDot d) {
		if (d.getX() == this.getX() && d.getY() == this.getY()) {
			return true;
		}
		return false;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean incX() {
		x++;
		if (x >= Board.BOARD_WIDTH) {
			return false;
		}
		return true;
	}
	public boolean decX() {
		x--;
		if (x < 0) {
			return false;
		}
		return true;
	}
	public boolean incY() {
		y++;
		if (y >= Board.BOARD_HEIGHT) {
			return false;
		}
		return true;
	}
	public boolean decY() {
		y--;
		if (y < 0) {
			return false;
		}
		return true;
	}
	
	public void paintDot(Graphics g, DotType d) {
		if (d == DotType.SNAKE1) {
			g.drawOval(x*10, y*10, 10, 10);
		}
		if (d == DotType.SNAKE2) {
			g.drawRect(x*10, y*10, 10, 10);
		}
		if (d == DotType.COIN_TOKEN) {	
			g.drawRoundRect(x*10, y*10, 10, 10, 5, 5);
		}
	}
	
}

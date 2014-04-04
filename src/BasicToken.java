import java.awt.Graphics;
import java.util.Random;


public class BasicToken implements Token {
	
	private BoardDot token;
	private DotType type;
	private Random random;
	
	public BasicToken() {
		random = new Random();
		token = new BoardDot(0, 0);
		newToken();
	}
	
	public BoardDot getToken() {
		return token;
	}
	public DotType getType() {
		return type;
	}
	
	public void newToken() {
		token.setX(random.nextInt(Board.BOARD_WIDTH));
		token.setY(random.nextInt(Board.BOARD_HEIGHT));
		if (random.nextInt(20) < 20) {
			type = DotType.COIN_TOKEN;
		}
	}
	
	public void setToken(BoardDot d, DotType t) {
		token = d;
		type = t;
	}

	public void paintToken(Graphics g) {
		token.paintDot(g, type);
	}
	
}

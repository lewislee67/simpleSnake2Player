import java.awt.Graphics;


public interface Token {

	public void paintToken(Graphics g);
	
	public void setToken(BoardDot d, DotType t);
	
}

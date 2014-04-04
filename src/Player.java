import java.awt.Dimension;

import javax.swing.JPanel;


public class Player {
	
	private boolean turned;
	private int score;
	private DotType power;
	
	public Player() {
		score = 0;
		init();
	}
	public void init() {
		turned = false;
		power = null;
	}
	public boolean getTurned() {
		return turned;
	}
	public void setTurned(boolean b) {
		turned = b;
	}
	public void incScore() {
		score++;
	}
	public int getScore() {
		return score;
	}
	
}

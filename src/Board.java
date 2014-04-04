import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Board extends JPanel {

	private Snake1 player1;
	private Snake2 player2;
	private Player data1;
	private Player data2;
	private ArrayList<BasicToken> tokens;
	private Timer gameTimer;
	private JPanel infoPanel;
	private JLabel player1Info;
	private JLabel player2Info;
	
	public Board() {
		this.setLayout(new BorderLayout());
		this.setSize(new Dimension(BOARD_WIDTH*12+DATA_WIDTH, BOARD_HEIGHT*12));
		this.addKeyListener(new KeyboardListener());
		setFocusable(true);
		player1Info = new JLabel();
		player1Info.setFont(new Font("Serif", Font.PLAIN, 22));
		player2Info = new JLabel();
		player2Info.setFont(new Font("Serif", Font.PLAIN, 22));
		infoPanel = new JPanel();
		infoPanel.setLayout(new BorderLayout());
		infoPanel.add(player1Info, BorderLayout.NORTH);
		infoPanel.add(player2Info, BorderLayout.SOUTH);
		add(infoPanel, BorderLayout.EAST);
		gameTimer = new Timer(DEFAULT_SPEED, new TimerListener());
		data1 = new Player();
		data2 = new Player();
		tokens = new ArrayList<BasicToken>(4);
		for (int i = 0; i < 4; i++) {
			tokens.add(new BasicToken());
		}
		initialise();
	}
	
	public void initialise() {
		data1.init();
		data2.init();
		setText();
		player1 = new Snake1();
		player2 = new Snake2();
		for (BasicToken t : tokens) {
			newToken(t);
		}
		repaint();
		gameTimer.start();
	}
	private void setText() {
		player1Info.setText("Player 1:  " + data1.getScore());
		player2Info.setText("Player 2:  " + data2.getScore());
	}
	
	private void newToken(BasicToken t) {
		t.newToken();
		while (player1.hasCollided(t.getToken()) || player2.hasCollided(t.getToken()) || tokenCollision(t)) {
			t.newToken();
		}
	}
	private boolean tokenCollision(BasicToken t) {
		for (BasicToken token : tokens) {
			if (t.getToken().equals(token.getToken())) {
				if (t != token) {
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean checkCollisions() {
		if (player1.hasCollided(player2.getHead())) {
			data1.incScore();
			gameOver("Player 1");
			return true;
		}
		if (player2.hasCollided(player1.getHead())) {
			data2.incScore();
			gameOver("Player 2");
			return true;
		}
		return false;
	}
	
	private void checkTokens(char player) {
		DotType type = null;
		if (player == '1') {
			for (BasicToken t : tokens) {
				if (player1.getHead().equals(t.getToken())) {
					type = t.getType();
					newToken(t);
				}
			}
			if (type == DotType.COIN_TOKEN) {
				return;
			}
			player1.removeTail();
		} else {
			for (BasicToken t : tokens) {
				if (player2.getHead().equals(t.getToken())) {
					type = t.getType();
					newToken(t);
				}
			}
			if (type == DotType.COIN_TOKEN) {
				return;
			}
			player2.removeTail();
		}
	}
	
	
	public void move() {
		boolean bool1 = player1.addNewHead();
		boolean bool2 = player2.addNewHead();
		if (!bool1) {
			data2.incScore();
			gameOver("Player 2");
			return;
		} if (!bool2) {
			data1.incScore();
			gameOver("Player 1");
			return;
		}
		if (checkCollisions()) {
			return;
		}
		checkTokens('1');
		checkTokens('2');
		data1.setTurned(false);
		data2.setTurned(false);
		repaint();
	}
	
	public void gameOver(String player) {
		gameTimer.stop();
		JOptionPane.showMessageDialog(this, player + " wins");
		initialise();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.drawRect(0, 0, BOARD_WIDTH*10, BOARD_HEIGHT*10);
		player1.paintSnake(g);
		player2.paintSnake(g);
		for (BasicToken t : tokens) {
			t.paintToken(g);
		}
	}
	
	private class TimerListener implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			move();
		}	
	}
	private class KeyboardListener extends KeyAdapter {

		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_UP && player1.getDirection() != Direction.DOWN && !data1.getTurned()) {
				player1.setDirection(Direction.UP);
				data1.setTurned(true);
			} if (e.getKeyCode() == KeyEvent.VK_DOWN && player1.getDirection() != Direction.UP && !data1.getTurned()) {
				player1.setDirection(Direction.DOWN);
				data1.setTurned(true);
			} if (e.getKeyCode() == KeyEvent.VK_LEFT && player1.getDirection() != Direction.RIGHT && !data1.getTurned()) {
				player1.setDirection(Direction.LEFT);
				data1.setTurned(true);
			} if (e.getKeyCode() == KeyEvent.VK_RIGHT && player1.getDirection() != Direction.LEFT && !data1.getTurned()) {
				player1.setDirection(Direction.RIGHT);
				data1.setTurned(true);
			}
			if (e.getKeyCode() == KeyEvent.VK_W && player2.getDirection() != Direction.DOWN && !data2.getTurned()) {
				player2.setDirection(Direction.UP);
				data2.setTurned(true);
			} if (e.getKeyCode() == KeyEvent.VK_S && player2.getDirection() != Direction.UP && !data2.getTurned()) {
				player2.setDirection(Direction.DOWN);
				data2.setTurned(true);
			} if (e.getKeyCode() == KeyEvent.VK_A && player2.getDirection() != Direction.RIGHT && !data2.getTurned()) {
				player2.setDirection(Direction.LEFT);
				data2.setTurned(true);
			} if (e.getKeyCode() == KeyEvent.VK_D && player2.getDirection() != Direction.LEFT && !data2.getTurned()) {
				player2.setDirection(Direction.RIGHT);
				data2.setTurned(true);
			}
			if (e.getKeyCode() == KeyEvent.VK_P) {
				toggleTimer();
			}
		}
	}
	private void toggleTimer() {
		if (gameTimer.isRunning()) {
			gameTimer.stop();
		} else {
			gameTimer.start();
		}
	}
	
	public static final int BOARD_WIDTH = 30;
	public static final int BOARD_HEIGHT = 30;
	public static final int DATA_WIDTH = 100;
	public static final int DATA_HEIGHT = BOARD_HEIGHT*12;
	private final int DEFAULT_SPEED = 100;
}

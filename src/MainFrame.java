import java.awt.Dimension;

import javax.swing.JFrame;


public class MainFrame extends JFrame {
	public MainFrame() {
		setSize(new Dimension(Board.BOARD_WIDTH*12+Board.DATA_WIDTH, Board.BOARD_HEIGHT*12));
		add(new Board());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	public static void main (String[] args) {
		new MainFrame();
	}
}

package main;

import game.Board;
import game.PlayMaker;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class GameCenter extends JFrame{

	private static final long serialVersionUID = 1L;
	public static final int INFINITE=1000;
	
	private Board board;
	private PlayMaker playMaker;
	private int HEIGHT, WIDTH, tileSIZE;
	private Color[] colors;
	
	public GameCenter(String name, int maxLevel, int maxTime, boolean prune, boolean tree){
		super("Azulejos");
		board = BoardReader.startGame(name);
		//board.createRandomBoard(20, 20, 3);
		calculateWidthAndHeight();
		initColors();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		setResizable(false);
		
		//setUndecorated(true);
		setSize(WIDTH,HEIGHT);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		playMaker = new PlayMaker(board, maxLevel, maxTime, prune, tree);
		
		BoardPanel boardPanel=new BoardPanel(playMaker,colors,tileSIZE, board.getWidth(), board.getHeight());
		boardPanel.setBounds(0, 40, WIDTH, board.getHeight()*tileSIZE);
		add(boardPanel);
		
		InfoPanel infoPanel=new InfoPanel(playMaker, WIDTH);
		infoPanel.setBounds(0, 0, WIDTH, 40);
		add(infoPanel);
		
		
		setVisible(true);
	}
	
	private void calculateWidthAndHeight(){
		int maxWidth = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()-100)/board.getWidth();
		int maxHeight = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()-100)/board.getHeight();
		tileSIZE = Math.min(maxWidth, maxHeight);
		
		WIDTH = tileSIZE * board.getWidth();
		HEIGHT = tileSIZE * board.getHeight() + 40;
	}
	
	
	
	private void initColors(){
		colors = new Color[8];
		colors[0] = Color.WHITE;
		colors[1] = Color.RED;
		colors[2] = Color.BLUE;
		colors[3] = Color.GREEN;
		colors[4] = Color.MAGENTA;
		colors[5] = Color.CYAN;
		colors[6] = Color.PINK;
		colors[7] = Color.ORANGE;
	}
	
	public static void main(String[] args) {
		new GameCenter("Tableros/tablero2.txt", 4, INFINITE, true,false);
	}
	
}

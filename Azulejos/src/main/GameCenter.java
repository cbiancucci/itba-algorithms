package main;

import exceptions.UnsupportedSyntaxException;
import game.Board;
import game.Options;
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
	
	public GameCenter(String name, Options options){
		super("Azulejos");
		board = BoardReader.startGame(name);
		//board.createRandomBoard(20, 20, 3);
		calculateWidthAndHeight();
		initColors();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		setResizable(false);
		setUndecorated(true);
		setSize(WIDTH,HEIGHT);
		
		
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2-10);
		
		playMaker = new PlayMaker(board, options);
		
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
		
		if(args[0].equals("-file") && args[1] != null){
				
			String fileName = args[1].toString();
				
			int maxTime = INFINITE;
			int depth = 1;
			boolean visual = false;
			boolean prune = false;
			boolean tree = false;
				
			if(args[2] != null && args[2].equals("-maxtime") && args[3] != null)
				maxTime = Integer.valueOf(args[3]);
			else if(args[2] != null && args[2].equals("-depth") && args[3] != null)
				depth = Integer.valueOf(args[3]);
			else
				throw new UnsupportedSyntaxException();
			
			if(args[4].equals("-visual"))
				visual = true;
			else if(args[4].equals("-console"))
				visual = false;
			else
				throw new UnsupportedSyntaxException();
				
			if(args[5] != null){
				if(args[5].equals("-prune"))
					prune = true;
				else if(args[5].equals("-tree"))
					tree = true;
				else
					throw new UnsupportedSyntaxException();
			}
				
			if(visual)
				new GameCenter(fileName, new Options(depth, maxTime, prune, tree));
		}
	}
	
}

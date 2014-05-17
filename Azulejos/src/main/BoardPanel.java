package main;

import game.PlayMaker;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class BoardPanel extends JLayeredPane{
	
	private static final long serialVersionUID = 1L;
	
	private PlayMaker playMaker;
	private Cursor humanTurn, pcTurn;
	private JLabel gameOver;
	
	public BoardPanel(PlayMaker pm, Color[] colors, int tileSIZE, int sizeX, int sizeY){
		setLayout(null);
		setSize(tileSIZE*sizeX,tileSIZE*sizeY);
	//	Toolkit toolkit = Toolkit.getDefaultToolkit(); 
		humanTurn = Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
		pcTurn = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
	//	Image pcTurnImg = toolkit.getImage("Source/cursor-1.gif");
		
	//	pcTurn = toolkit.createCustomCursor(pcTurnImg, new Point(16,16), "PC Turn");  
		
		setCursor(humanTurn);
		this.playMaker = pm;
		
		playMaker.setBoardPanel(this);
		
		gameOver = new JLabel("");
		gameOver.setFont(gameOver.getFont().deriveFont((float)30));
		gameOver.setBounds(getWidth()/2 -100,getHeight()/2 -20, 400, 40);
		add(gameOver,new Integer(2));
		
		
		for(int i = 0 ; i < sizeX ; i++){
			for(int j = 0 ; j < sizeY ; j++){
				TilePanel tile = new TilePanel(playMaker, tileSIZE,i,j,colors);
				tile.setBounds(i * tileSIZE, (sizeY-1-j) * tileSIZE, tileSIZE, tileSIZE);
				add(tile,new Integer(1));
			}
		}
		
	}
	
	public void moveMade(){
		for(Component c: getComponents())
			c.repaint();
		
		if(playMaker.getBoard().isPCturn()) setCursor(pcTurn);
		else setCursor(humanTurn);
	}
	
	public void gameOver(){
		int winner = playMaker.getBoard().getPoints(0) - playMaker.getBoard().getPoints(1);
		gameOver.setText(winner == 0 ? "Draw" : ("Player " + (winner > 0 ? 1:2) + " wins!"));
	}

}

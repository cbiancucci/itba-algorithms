package main;

import game.Board;
import game.PlayMaker;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class InfoPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private PlayMaker playMaker;
	private int WIDTH;
	
	public InfoPanel(PlayMaker pm, int WIDTH) {
		setLayout(null);
		setSize(WIDTH, 40);
		this.playMaker = pm;
		this.WIDTH = WIDTH;
		
		playMaker.setInfoPanel(this);
		
		JButton close = new JButton(new ImageIcon("Source/close-cross.jpg",  "Close Game"));
		close.setBounds(WIDTH-16, 0, 16, 16);
		close.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		add(close);
		
		setVisible(true);
	}
	
	public void moveMade(){
		repaint();
	}

	public void paint(Graphics g){
		Board board = playMaker.getBoard();
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, WIDTH, 40);
		g.setColor(Color.BLACK);
		g.fillRect(0, 37, WIDTH, 3);
		g.setColor(Color.BLACK);
		g.setFont(g.getFont().deriveFont((float) 15));
		g.drawString("Player 1: " + board.getPoints(0), WIDTH/4, 20);
		g.drawString("Player 2: " + board.getPoints(1), WIDTH/4*3, 20);
		if(board.isPCturn()){
			g.setFont(g.getFont().deriveFont((float)12));
			g.drawString("Thinking...", WIDTH*3/4, 34);
		}
	}
	
}

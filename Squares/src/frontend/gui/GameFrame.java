package frontend.gui;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import backend.GameListener;
import backend.cell.Cell;
import backend.element.Element;
import frontend.Game;

public class GameFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final int CELL_SIZE = 64;
	
	private Game game;
	private BoardPanel bp;
	private GameListener listener;
	private ImageManager images;
	private Point point;
	
	public GameFrame(Game game) throws IOException {
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.game = game;
		
		images = new ImageManager();
		
		setLayout(null);
		setResizable(false);
		setSize(game.getRows() * CELL_SIZE, game.getCols() * CELL_SIZE + 20);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

		bp = new BoardPanel(game.getRows(), game.getCols(), CELL_SIZE);
		add(bp);

		game.initGame();
		game.addGameListener(listener = new GameListener() {
			@Override
			public void gridUpdated() {
				try {
					for (int i = 0; i < getGame().getRows(); i++) {
						for (int j = 0; j < getGame().getCols(); j++) {
							setImageCell(i, j, false);
						}
					}
					bp.paintImmediately(bp.getBounds());
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}

			@Override
			public void cellExplosion(Element e) {
			}
		});
		
		listener.gridUpdated();
		
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				
				point = translateCoords(event.getX(), event.getY());
				System.out.println("Punto: " + point);
				
				getGame().tryMove(point.x, point.y);
				
				String message = ((Long) getGame().getScore()).toString();
				
				if (getGame().isFinished()) {
					if (getGame().playerWon()) {
						message = message + " Finished - Player Won!";
					} else {
						message = message + " Finished - Loser !";
					}
				}
			}
		});
		
	}
	
	private void setImageCell(int i, int j, boolean hint) {
		try {
			Cell cell = GameFrame.this.game.getCell(i, j);
			Element element = cell.getContent();
			
			bp.clearImage(i, j);
			Image nothing= ImageIO.read(new File("resources/images/nothing.png"));
			bp.appendImage(i, j, nothing);
			
			Image image = images.getImage(element);
			bp.appendImage(i, j, image);
						
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private Game getGame(){
		return game;
	}
	
	private Point translateCoords(int x, int y) {
		int i = x / CELL_SIZE;
		int j = y / CELL_SIZE;
		return (i >= 0 && i < game.getRows() && j >= 0 && j < game.getCols()) ? new Point(j, i) : null;
	}
}

package frontend;

import java.io.IOException;

import frontend.gui.GameFrame;

public class GameApp {

	public static void main(String[] args) throws IOException {
		
		int[][] numbers = {{1, 2, 3}, {1, 3, 2}, {3, 3, 2}};
		Game game = new Game(3, 3, numbers);
		
		GameFrame gameFrame = new GameFrame(game);
		gameFrame.setVisible(true);
	}
	
}

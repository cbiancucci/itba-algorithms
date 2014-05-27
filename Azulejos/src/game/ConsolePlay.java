package game;

import main.BoardReader;
import minimax.Minimax;

public class ConsolePlay implements PlayMaker{

	private Board board;
	
	public ConsolePlay(String fileName, Options options){
		board = BoardReader.startGame(fileName);
		if(board.gameOver()){
			System.out.println("No hay ninguna jugada disponible");
		}else{
			new Minimax(this, board, options);
		}
	}
	
	public void recievePCmovement(Board b){
		board = b;
		for(int i = b.getHeight() - 1 ; i>=0 ; i--){
			for(int j = 0; j < board.getWidth() ; j++){
				int tile = board.getBoard()[j][i].getColor();
				System.out.print(tile != 0 ? tile : " ");
			}
			System.out.println();
		}
	}
}
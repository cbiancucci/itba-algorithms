package game;

import main.BoardPanel;
import main.InfoPanel;
import minimax.Minimax;

public class VisualPlay implements PlayMaker{

	private Board board;
	private BoardPanel boardPanel;
	private InfoPanel infoPanel;
	private Options options;
	private long start;
	
	public VisualPlay(Board board, Options options){
		this.board = board;
		this.options = options;
	}
	
	public void makeMove(int x, int y){
		if(board.isPCturn()) return;
		boolean moved = board.touched(0, x, y, null);
		if(moved){
			moveMade();
			/**
			 * TODO: Find a better way to send variables. Board, options are already in PlayMarker.
			 */
			new Minimax(this, board, options);
			start = System.currentTimeMillis();
		}
	}
	
	public void recievePCmovement(Board b){
		int elapsed = (int) (System.currentTimeMillis() - start);
		if(elapsed < 1000){
			try{
				Thread.sleep(1000 - elapsed);
			}catch(Exception e){
				/**
				 * TODO: Verify exceptions.
				 */
			}
		}
		
		board = b;
		board.PCfinished();
		moveMade();
	}
	
	private void moveMade(){
		boardPanel.moveMade();
		infoPanel.moveMade();
		if(board.gameOver()){
			boardPanel.gameOver();
		}
	}
	
	public void setBoardPanel(BoardPanel bp){
		this.boardPanel = bp;
	}
	
	public void setInfoPanel(InfoPanel ip){
		this.infoPanel = ip;
	}
	
	public Board getBoard(){
		return board;
	}
}

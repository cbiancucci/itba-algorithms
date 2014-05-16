package frontend;

import backend.GameListener;
import backend.cell.Cell;
import backend.element.Element;
import backend.Grid;

public class Game implements GameListener{

	private Grid grid;
	private int[][] colors;
	//private GameState state;
	
	public Game(int rows, int cols, int[][] colors){
		
		this.grid = new Grid(rows, cols);
		this.colors = colors;
	}
	
	public void initGame(){
		grid.initialize(colors);
		addGameListener(this);
	}
	
	
	public boolean isFinished(){
		return false;
	}
	
	public boolean playerWon(){
		return false;
	}
	
	public int getRows(){
		return grid.getRows();
	}
	
	public int getCols(){
		return grid.getCols();
	}

	public Cell getCell(int i, int j) {
		return grid.getCell(i, j);
	}
	
	public void addGameListener(GameListener listener) {
		//grid.addListener(listener);
	}

	@Override
	public void gridUpdated() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cellExplosion(Element e) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean tryMove(int x, int y) {
		return grid.tryMove(x, y);
	}
	
	public long getScore() {
		//return state.getScore();
		return 0;
	}
}
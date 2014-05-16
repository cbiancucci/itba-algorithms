package backend;

import backend.cell.Cell;
import backend.element.Element;

public class Grid {

	private int rows;
	private int cols;

	protected Cell[][] cells;
	private GameState state;
	
	public Grid(int rows, int cols){
		this.rows = rows;
		this.cols = cols;
		this.cells = new Cell[rows][cols];
	}
	
	public int getRows(){
		return rows;
	}
	
	public int getCols(){
		return cols;
	}

	protected GameState state() {
		return state;
	}

	public void initialize(int[][] colors) {
		
		fillCells(colors);
		setAround();
		//fallElements();
		
	}

	private void fillCells(int[][] colors){
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < cols; j++){
				cells[i][j] = new Cell(colors[i][j]);
			}
		}
	}
	
	private void setAround(){
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < cols; j++){
				
				if(i == 0){
					if(j == 0)
						cells[i][j].setAround(null, cells[i+1][j], null, cells[i][j+1]);
					else if(j == (cols - 1 ))
						cells[i][j].setAround(null, cells[i+1][j], cells[i][j-1], null);
					else
						cells[i][j].setAround(null, cells[i+1][j], cells[i][j-1], cells[i][j+1]);
				}
				
				else if(i == (rows -1)){
					if(j == 0)
						cells[i][j].setAround(cells[i-1][j], null, null, cells[i][j+1]);
					else if(j == (cols - 1 ))
						cells[i][j].setAround(cells[i-1][j], null, cells[i][j-1], null);
					else
						cells[i][j].setAround(cells[i-1][j], null, cells[i][j-1], cells[i][j+1]);
				}
				
				else{
					if(j == 0)
						cells[i][j].setAround(cells[i-1][j], cells[i+1][j], null, cells[i][j+1]);
					else if(j == (cols - 1 ))
						cells[i][j].setAround(cells[i-1][j], cells[i+1][j], cells[i][j-1], null);
					else
						cells[i][j].setAround(cells[i-1][j], cells[i+1][j], cells[i][j-1], cells[i][j+1]);
				}
			}
		}
	}
	
	
	protected void preInitialize() {
	}

	protected void postInitialize() {
	}

	public Element get(int i, int j) {
		return cells[i][j].getContent();
	}

	public Cell getCell(int i, int j) {
		return cells[i][j];
	}

	
	public void clearContent(int i, int j) {
		cells[i][j].clearContent();
	}

	public void setContent(int i, int j, Element e) {
		cells[i][j].setContent(e);
	}
	
	public boolean tryMove(int x, int y){
		boolean explosion = cells[x][y].explode();
		System.out.println(explosion);
		return explosion;
	}
	
}
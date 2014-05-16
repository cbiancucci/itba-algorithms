package backend.cell;

import backend.element.Color;
import backend.element.Element;
import backend.element.Nothing;
import backend.element.Square;
import backend.move.Direction;

public class Cell {

	private Cell[] around = new Cell[Direction.values().length];
	private Element content;
	
	public Cell(int color){
		if(color == 0){
			this.content = new Nothing();
		} else {
			this.content = new Square(Color.values()[color]);
		}
	}
	
	public Element getContent(){
		return content;
	}
	
	public boolean isEmpty(){
		return content == null;
	}
	
	public void setAround(Cell up, Cell down, Cell left, Cell right) {
		this.around[Direction.UP.ordinal()] = up;
		this.around[Direction.DOWN.ordinal()] = down;
		this.around[Direction.LEFT.ordinal()] = left;
		this.around[Direction.RIGHT.ordinal()] = right;
	}
	
	public Cell[] getAround(){
		return around;
	}
	
	// Mando a explotar a mi alrededor.
	public boolean explode(){
		return explode(Direction.UP) || explode(Direction.DOWN) || explode(Direction.LEFT) || explode(Direction.RIGHT);
	}
	
	// Mando a explotar una direccion en particular.
	private boolean explode(Direction d) {
		if (this.around[d.ordinal()] != null)
			return this.around[d.ordinal()].explosion(this, getColor());
		
		return false;
	}

	// Recibo la notificacion para que explote. Si soy del mismo color. Exploto y llamo a mi alrededor.
	public boolean explosion(Cell cell, Color color){
		if(getColor().equals(color)){
			explode();
			return true;
		} else {
			return false;
		}
	}
	
	/*
	public Element getAndClearContent() {
		if (!content.isEmpty()) {
			Element ret = content;
			this.content = new Nothing();
			return ret;
		}
		return null;
	}
	
	public boolean fallContent (Cell cell)
	{
		if (this.isEmpty() && !cell.isEmpty() && !cell.isEmpty()) {
			this.content = cell.getAndClearContent();
		}
		return false;
	}
	
	*/
	
	public void setContent(Element content) {
		this.content = content;
	}
	
	public void clearContent(){
		this.content = new Nothing();
	}

	public Color getColor() {
		try {
			return ((Square) getContent()).getColor();
		} catch (Exception e) {
			return null;
		}
	}

	public Cell getRight() {
		return around[Direction.RIGHT.ordinal()];
	}

	public Cell getLeft() {
		return around[Direction.LEFT.ordinal()];
	}

	public Cell getDown() {
		return around[Direction.DOWN.ordinal()];
	}

	public Cell getUp() {
		return around[Direction.UP.ordinal()];
	}
	
}

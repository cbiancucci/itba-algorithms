package backend.element;

public class Square extends Element {
	
	private Color color;
	
	public Square(Color color){
		this.color = color;
	}
	
	@Override
	public String getKey(){
		return color.toString();
	}

	@Override
	public boolean isEmpty() {
		return false;
	}
	
	public Color getColor(){
		return color;
	}

}

package backend.element;

public class Nothing extends Element {

	@Override
	public boolean isEmpty() {
		return true;
	}

	@Override
	public String getKey() {
		return "NOTHING";
	}

}

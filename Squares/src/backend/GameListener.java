package backend;

import backend.element.Element;

public interface GameListener {
	
	public void gridUpdated();
	
	public void cellExplosion(Element e);
	
}
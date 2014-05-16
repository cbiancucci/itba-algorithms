package frontend.gui;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import backend.element.Color;
import backend.element.Element;
import backend.element.Nothing;
import backend.element.Square;

public class ImageManager {

	private Map<String, Image> images;

	public ImageManager() {
		
		images = new HashMap<String, Image>();

		try {
			
			images.put(new Nothing().getKey(), ImageIO.read(new File("resources/images/nothing.png")));
			
			
			for (Color cc: Color.values()) {
				images.put(new Square(cc).getKey(), ImageIO.read(new File("resources/images/" + cc.toString().toLowerCase() + ".png")));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Image getImage(Element e) {
		return images.get(e.getKey());
	}
}

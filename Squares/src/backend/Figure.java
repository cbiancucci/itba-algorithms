package backend;

import backend.element.Square;
import backend.element.Color;
import backend.element.Element;

import java.awt.Point;

public enum Figure {
	
	F1(new Point[]{new Point(0,1), new Point(0,2)}, 48),
	F2(new Point[]{new Point(0,-1), new Point(0,1)}, 80),
	F3(new Point[]{new Point(0,-1), new Point(0,-2)}, 192),
	F10(new Point[]{ new Point(1,0), new Point(2,0)}, 12),	
	F11(new Point[]{ new Point(-1,0), new Point(1,0)}, 5),	
	F12(new Point[]{ new Point(-2,0), new Point(-1,0)}, 3);
	
	
	private Point[] points;
	private int value;
	private Class<?> replacementClass;
	private boolean isCandyRepl = true;
	
	private Figure(Point[] points, int value, Class<?> replacementClass) {
		this.points = points;
		this.value = value;
		this.replacementClass = replacementClass;
	}
	
	private Figure(Point[] points, int value, Class<?> replacementClass, boolean isCandyRepl) {
		this.points = points;
		this.value = value;
		this.replacementClass = replacementClass;
		this.isCandyRepl = isCandyRepl;
	}
	
	private Figure(Point[] points, int value) {
		this.points = points;
		this.value = value;
		this.replacementClass = null;
	}
	
	public Point[] getPoints() {
		return points;
	}
	
	public int size() {
		return points.length;
	}
	
	public Class<?> getReplacementClass() {
		return replacementClass;
	}
	
	public boolean hasReplacement() {
		return replacementClass != null;
	}
	
	public boolean matches(int acum) {
		return value == (value & acum);
	}
	
	public Element generateReplacement(Color color) {
		try {
			Element e;
			e = (Element) replacementClass.newInstance();
			if (isCandyRepl) {
				((Square)e).setColor(color);
			} 
			return e;
		} catch(Exception e) {
		}
		return null;
	}	
}

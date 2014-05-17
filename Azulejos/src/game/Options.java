package game;

public class Options {

	private int maxLevel, maxTime;
	private boolean prune, tree;
	
	public Options(int maxLevel, int maxTime, boolean prune, boolean tree){
		this.maxLevel = maxLevel;
		this.maxTime = maxTime;
		this.prune = prune;
		this.tree = tree;
	}
	
	public int getMaxLevel(){
		return maxLevel;
	}
	
	public int getMaxTime(){
		return maxTime;
	}
	
	public boolean getPrune(){
		return prune;
	}
	
	public boolean getTree(){
		return tree;
	}
}

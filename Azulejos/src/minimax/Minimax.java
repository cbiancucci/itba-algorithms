package minimax;

import game.Board;
import game.PlayMaker;

import java.awt.Point;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import main.GameCenter;

public class Minimax implements Runnable{

	private PlayMaker playMaker;
	private BoardState root;
	private int maxLevel, maxTime;
	private boolean prune, time, tree;
	
	public Minimax(PlayMaker pm, Board board, int maxLevel, int maxTime, boolean prune, boolean tree){
		root = new StateMax(board.getCopy(), new Point(-1, -1));
		this.playMaker = pm;
		this.maxLevel = maxLevel;
		this.maxTime = maxTime;
		this.prune = prune;
		this.tree = tree;
		time = true;
		Thread t = new Thread(this, "Minimax");
		t.start();
	}
	
	public void run() {
		if(maxTime != GameCenter.INFINITE){
			new myTimer(this, maxTime);
		}
		runAlgorithm(root, maxLevel, GameCenter.INFINITE);
		returnMove();
	}
	
	private int runAlgorithm(BoardState state, int deep, int parentScore){
		if(deep == 0 || !time){
			state.calculateScore();
			return state.getScore();
		}
		
		BoardState chosen = null;
		for(BoardState child: state.getBoard().possibleMoves(state.isMax())){
			state.addChild(child);
			if(!prune || state.analizeState(parentScore)){
				int childScore = runAlgorithm(child, deep-1, state.getScore());
				if(state.updateScore(childScore)){
					if(chosen != null) chosen.chosen(false);
					chosen = child;
					chosen.chosen(true);
				}
			}else{
				child.pruned();
			}
			
		}
		return state.getScore();
	}
	
	private void returnMove(){
		Board optimal = root.getOptimalState().getBoard();
		if(tree) drawTree();
		playMaker.recievePCmovement(optimal);
	}
	
	private void drawTree(){
		BufferedWriter writer = null;

		try {
		    writer = new BufferedWriter(new OutputStreamWriter( new FileOutputStream("tree.dot"), "utf-8"));
		    writer.write("digraph Tree{");
		    writer.newLine();
		    writer.write(root.hashCode() + " [label=\"START " + root.getScore() +"\"" + " style=filled  fillcolor=brown" +"]");
			writer.newLine();
		    root.drawTree(writer);
		    writer.write("}");
		} catch (IOException e) {
			/**
			 * Verify exceptions
			 */
		}
		finally {
		   try {
			   writer.close();
		   } catch (Exception e) {
			   /**
			    * TODO: Verify exceptions.
			    */
		   }
		}
	}
	
	public void timeUp(){
		time = false;
	}
}

package minimax;

import game.Board;
import game.Options;
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
	private Options options;
	private boolean time;
	
	public Minimax(PlayMaker pm, Board board, Options options){
		root = new StateMax(board.getCopy(), new Point(-1, -1));
		this.playMaker = pm;
		this.options = options;
		time = true;
		Thread t = new Thread(this, "Minimax");
		t.start();
	}
	
	public void run() {
		if(options.getMaxTime() != GameCenter.INFINITE){
			new myTimer(this, options.getMaxTime());
		}
		runAlgorithm(root, options.getMaxLevel(), GameCenter.INFINITE);
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
			if(!options.getPrune() || state.analizeState(parentScore)){
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
		if(options.getTree()) drawTree();
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

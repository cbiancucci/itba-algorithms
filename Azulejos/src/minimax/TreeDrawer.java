package minimax;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class TreeDrawer {

	public static void drawMovement(BoardState root){
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

	public static void drawTree(BoardState bs, BufferedWriter writer) throws IOException{
		
		for(BoardState child: bs.getChilds()){
			writer.write(child.hashCode() + " [label=" + child.getLabel());
			if(!child.isMax()) writer.write(" shape=box ");
			if(child.isPruned() || child.isChosen()) writer.write(" style=filled ");
			if(child.isChosen()) writer.write(" fillcolor=brown ");
			writer.write("]");
			writer.newLine();
			writer.write(bs.hashCode()+"->"+child.hashCode());
			writer.newLine();
		}
		for(BoardState child: bs.getChilds()){
			TreeDrawer.drawTree(child, writer);
		}
	}
}

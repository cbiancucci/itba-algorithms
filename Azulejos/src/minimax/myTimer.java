package minimax;

public class myTimer implements Runnable {

	private int maxTime;
	private Minimax minimax;
	
	public myTimer(Minimax minimax, int maxTime){
		this.maxTime = maxTime;
		this.minimax = minimax;
		Thread t = new Thread(this,"My Timer");
		t.start();
	}
	
	public void run() {
		try{
			Thread.sleep((int)(maxTime*1000*0.9));
		}catch(InterruptedException e){
			/**
			 * TODO: Verify exceptions.
			 */
		}
		minimax.timeUp();
	}
}

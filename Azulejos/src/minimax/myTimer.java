package minimax;

public class MyTimer implements Runnable{
	
	private long finish;
	private Minimax minimax;
	
	public MyTimer(Minimax minimax, int maxTime){
		finish = maxTime*1000 + System.currentTimeMillis();
		this.minimax = minimax;
		Thread t = new Thread(this, "My Timer");
		t.start();
	}
	
	public void run() {
		try{
			while(System.currentTimeMillis() < finish){
				System.out.println(-finish + System.currentTimeMillis() +  3*1000);
				Thread.sleep(100);
			}
			minimax.timeUp();
		}catch(InterruptedException e){
			/**
			* TODO: Verify exceptions.
			*/
		}
	}

}

package testcodes.base;

public class ThreadTest {

	public static void main(String[] args) {
		testState();
	}
	
	public static void testState(){
		final Thread t = new Thread(){
			public void run(){
				for(int i=0; i<100; i++){
					LOG.log("current i= " + i);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		t.setPriority(Thread.MIN_PRIORITY);
		//t.start();
		Thread t2 = new Thread(){
			public void run(){
				while(true){
					LOG.log(t.isAlive() + ", " + t.isDaemon() + ", " + t.isInterrupted());
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		
		t2.start();
		t.start();
	}
	
	
	
	/**
	 * if call sleep in thread when run in Loop;
	 * 1. call Thread.interrupt in other Thread;
	 * 2. break the Loop in catch(InterruptedException e);
	 * 
	 * 
	 * @param args
	 */
	public static void testStopThread(){
		//final Object lock = new Object();
		final Thread t = new Thread(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				int i = 0;
				for(;;){
					//synchronized (lock) {
						System.out.println("i = " + i++);
					/*	try {
							lock.wait(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}*/
					//}
					try {
						sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						break;
					}
				}
			}
		};
		t.start();
		
		Thread t2 = new Thread(){
			@Override
			public void run() {
				try {
					sleep(1000);
					t.stop();
					//t.interrupt();
					//t.suspend();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		t2.start();
		
	}
}

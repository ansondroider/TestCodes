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
	 **/
	public static void testStopThread(){
		//final Object lock = new Object();
		final Thread t = new Thread(){
			@Override
			public void run() {
				int i = 0;
				for(;!this.isInterrupted();){
					//synchronized (lock) {
						android.util.Log.d("ThreadTest","i = " + i++);
					/*	try {
							lock.wait(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}*/
					//}
					try {
						sleep(1000);
					} catch (InterruptedException e) {
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
					sleep(15500);
					//t.stop();
                    android.util.Log.d("ThreadTest","interrupt()");
					t.interrupt();
					//t.suspend();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		t2.start();
	}

    /**
     * Thread will call CheckNotStart when start...
     * but, the state will change to TERMINAL after start() call.
     * and NEVER change to idle....
     */
    public static void testRestartThread(){
        final Thread t = new Thread(){
            @Override
            public void run() {
                int i = 0;
                for(;!isInterrupted();){
                    android.util.Log.d("ThreadTest","i = " + i++);
                    try{sleep(500);}catch (Exception e){e.printStackTrace();}
                }
            }
        };

        Thread t2 = new Thread(){
            @Override
            public void run() {
                t.start();
                try{sleep(5500);}catch (Exception e){e.printStackTrace();}
                t.interrupt();

                try{sleep(1000);}catch (Exception e){e.printStackTrace();}

                /**
                 * FATAL EXCEPTION: Thread-3217
                 java.lang.IllegalThreadStateException: Thread already started.
                 at java.lang.Thread.start(Thread.java:1045)
                 */
                t.start();
                try{sleep(5500);}catch (Exception e){e.printStackTrace();}
                t.interrupt();
            }
        };
        t2.start();


    }
}

package testcodes.base;

public class TestThread {
	public static void main(String[] args){
		new MThread("thread 1").start();
	}
	
	static class MThread extends Thread{
		String na;
		public MThread(String name){
			na = name;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			for(int i=10; i>0; i--){
				System.out.println(na + " i = " + i);
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			new MThread("thread 2").start();
		}
	}
}

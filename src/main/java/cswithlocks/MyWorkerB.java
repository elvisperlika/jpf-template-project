package cswithlocks;

import java.util.concurrent.locks.Lock;

import static gov.nasa.jpf.util.test.TestJPF.assertFalse;

public class MyWorkerB extends Worker {
	
	private Lock lock;
	
	public MyWorkerB(String name, Lock lock){
		super(name);
		this.lock = lock;
	}

	public void run(){
		while (true){
		  try {
			  lock.lockInterruptibly();
			  assertFalse(isCriticalSectionBusy());
			  setInCriticalSection();
			  b1();
			  b2();
		  } catch (InterruptedException ex) {
		  } finally {
			  lock.unlock();
			  setOutCriticalSection();
		  }
		  b3();
		}
	}
	
	protected void b1(){
		println("b1");
		// wasteRandomTime(0,1000);
	}
	
	protected void b2(){
		println("b2");
		// wasteRandomTime(100,200);
	}
	protected void b3(){
		println("b3");
		// wasteRandomTime(1000,2000);
	}
}

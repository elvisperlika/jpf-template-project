package cswithlocks;

import java.util.*;

public abstract class Worker extends Thread {

	protected boolean criticalSection = false;
	protected boolean bad;
	private Random rand;
	
	public Worker(String name){
		super(name);
		rand = new Random();
	}

	protected void wasteTime(long ms){
		try {
			sleep(ms);
		} catch (InterruptedException ex){
			ex.printStackTrace();
		}
	}

	protected void wasteRandomTime(long min, long max){
		try {
			double value = rand.nextDouble();
			double delay = min + value*(max-min);
			sleep((int)delay);
		} catch (InterruptedException ex){
			ex.printStackTrace();
		}
	}

	protected void print(String msg){
		synchronized (System.out){
			System.out.print(msg);
		}
	}

	protected void println(String msg){
		synchronized (System.out){
			System.out.println(msg);
		}
	}

	protected void setOutCriticalSection() {
		this.criticalSection = false;
	}

	protected void setInCriticalSection() {
		this.criticalSection = true;
	}

	protected boolean isCriticalSectionBusy() {
		return this.criticalSection;
	}

}

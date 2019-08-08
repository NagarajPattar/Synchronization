package com.myzee.static_syncd_method;

public class StaticSynchronedMethod {

	public static void main(String[] args) {
		SharedResource s = new SharedResource();
		UserThread t1 = new UserThread(s);	// start executing normal synchronized method
		UserThread1 t2 = new UserThread1();	// starts executing static synchronized method
		/* 	Both threads executes simultaneously, 
			because t1 gets 'object level lock' whereas t2 gets 'class level lock'
		*/
		t1.start();
		t2.start();	
	}

}

class UserThread extends Thread{
	
	SharedResource s;
	public UserThread(SharedResource s) {
		this.s = s;
	}
	
	@Override
	public void run() {
		s.show();
	}
}

class UserThread1 extends Thread{
	
	@Override
	public void run() {
		SharedResource.display();
	}
}

class SharedResource {
	synchronized public void show() {
		for(int i = 0; i < 10; i++) {
			System.out.println(Thread.currentThread().getName() + " - sync method - " + i);
		}
	}
	
	synchronized public static void display() {
		for(int i = 0; i < 10; i++) {
			System.out.println(Thread.currentThread().getName() + " - static sync method - " + i);
		}
	}
}

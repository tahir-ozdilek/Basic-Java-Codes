package p1;
class Synch {
	public static void main(String args[]) {
		Callme target = new Callme();
		Caller ob1 = new Caller(target, "Hello");
		Caller ob2 = new Caller(target, "Synchronized");
		Caller ob3 = new Caller(target, "World");
		try {
			ob1.t.join();
			ob2.t.join();
			ob3.t.join();
		} 
		catch(InterruptedException e) {
			System.out.println("Interrupted");
		}
	}
}

class Callme {
	synchronized void  call(String msg) {
			System.out.print("[");
			System.out.print("(");
			System.out.print(msg);
			System.out.print(")");
			System.out.println("]");
	}
}

class Caller implements Runnable {
	String msg;
	Callme target;
	Thread t;
	public Caller(Callme targ, String s) {
		target = targ;
		msg = s;
		t = new Thread(this);
		t.start();
	}
	public void run() {
		//synchronized (target)
		{ 
			target.call(msg);
		}
	}
}
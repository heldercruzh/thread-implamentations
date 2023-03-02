package error;

/*
	Deadlock in java is a programming situation where two or more threads are blocked forever.
	
	Follow simple program that will cause java deadlock scenario and then we will see how to analyze it
	
	program SyncThread is implementing Runnable interface and it works on two Objects by acquiring lock on 
	each one of them one by one using synchronized block. In main method, I have three threads running for 
	SyncThread and there is a shared resource between each of the threads. The threads are run in such a way 
	that it will be able to acquire lock on the first object but when it’s trying to acquire lock on second 
	object, it goes on wait state because it’s already locked by another thread. This forms a cyclic dependency 
	for resource between Threads causing deadlock. When I execute the above program, here is the output generated 
	but program never terminates because of deadlock in java threads.
	
	How to avoid deadlock in java
	
	Avoid Nested Locks: 
	another implementation of run() method without nested lock and program runs successfully without 
	deadlock situation.
	
	
		public void run() {
	        String name = Thread.currentThread().getName();
	        System.out.println(name + " acquiring lock on " + obj1);
	        synchronized (obj1) {
	            System.out.println(name + " acquired lock on " + obj1);
	            work();
	        }
	        System.out.println(name + " released lock on " + obj1);
	        System.out.println(name + " acquiring lock on " + obj2);
	        synchronized (obj2) {
	            System.out.println(name + " acquired lock on " + obj2);
	            work();
	        }
	        System.out.println(name + " released lock on " + obj2);
	
	        System.out.println(name + " finished execution.");
	    }
	
	Lock Only What is Required: ou should acquire lock only on the resources you have to work on
	Avoid waiting indefinitely: If your thread has to wait for another thread to finish, it’s always 
	best to use join with maximum time you want to wait for thread to finish.
	
	https://www.digitalocean.com/community/tutorials/deadlock-in-java-example
*/

public class Deadlock {

    public static void main(String[] args) throws InterruptedException {
        Object obj1 = new Object();
        Object obj2 = new Object();
        Object obj3 = new Object();
    
        Thread t1 = new Thread(new SyncThread(obj1, obj2), "t1");
        Thread t2 = new Thread(new SyncThread(obj2, obj3), "t2");
        Thread t3 = new Thread(new SyncThread(obj3, obj1), "t3");
        
        t1.start();
        Thread.sleep(5000);
        t2.start();
        Thread.sleep(5000);
        t3.start();
        
    }

}

class SyncThread implements Runnable{
    private Object obj1;
    private Object obj2;

    public SyncThread(Object o1, Object o2){
        this.obj1=o1;
        this.obj2=o2;
    }
    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println(name + " acquiring lock on "+obj1);
        synchronized (obj1) {
         System.out.println(name + " acquired lock on "+obj1);
         work();
         System.out.println(name + " acquiring lock on "+obj2);
         synchronized (obj2) {
            System.out.println(name + " acquired lock on "+obj2);
            work();
        }
         System.out.println(name + " released lock on "+obj2);
        }
        System.out.println(name + " released lock on "+obj1);
        System.out.println(name + " finished execution.");
    }
    private void work() {
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
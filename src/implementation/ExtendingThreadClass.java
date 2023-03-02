package implementation;

/*
	Here is a java thread example by implementing Runnable interface.
	
	1) we need to create a object of this Thread class 
	2) then call start() method to execute the run() method in a separate thread
	
	https://www.digitalocean.com/community/tutorials/java-thread-example
*/

class JavaThreadExtendingThreadClass extends Thread {

    public JavaThreadExtendingThreadClass(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println("MyThread - START "+Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
            //Get database connection, delete unused data from DB
            doDBProcessing();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("MyThread - END "+Thread.currentThread().getName());
    }

    private void doDBProcessing() throws InterruptedException {
        Thread.sleep(5000);
    }
    
}

public class ExtendingThreadClass {

    public static void main(String[] args){        
        System.out.println("Runnable Threads has been started");
        Thread t3 = new JavaThreadExtendingThreadClass("t3");
        Thread t4 = new JavaThreadExtendingThreadClass("t4");
        System.out.println("Starting MyThreads");
        t3.start();
        t4.start();
        System.out.println("MyThreads has been started");
        
    }
}
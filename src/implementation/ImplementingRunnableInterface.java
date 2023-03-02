package implementation;

/*
	Here is a java thread example by implementing Runnable interface.
	
	1) we need to create a Thread object by passing object of this runnable class 
	2) then call start() method to execute the run() method in a separate thread
	
	https://www.digitalocean.com/community/tutorials/java-thread-example
*/
class JavaThreadImplementingRunnableInterface implements Runnable {

    @Override
    public void run() {
        System.out.println("Doing heavy processing - START "+Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
            //Get database connection, delete unused data from DB
            doDBProcessing();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Doing heavy processing - END "+Thread.currentThread().getName());
    }

    private void doDBProcessing() throws InterruptedException {
        Thread.sleep(5000);
    }
        
}


public class ImplementingRunnableInterface {

    public static void main(String[] args){
        Thread t1 = new Thread(new JavaThreadImplementingRunnableInterface(), "t1");
        Thread t2 = new Thread(new JavaThreadImplementingRunnableInterface(), "t2");
        System.out.println("Starting Runnable threads");
        t1.start();
        t2.start();        
    }
    
}
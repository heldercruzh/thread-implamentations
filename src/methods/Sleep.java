package methods;

/*

	Thread.sleep can be used to pause the execution of 
	the current thread for a specified time in milliseconds.
	
	This code stores the current system time in milliseconds. 
	Then it sleeps for 2000 milliseconds. Finally, 
	this code prints out the new current system time minus the previous current system time:
	
	https://www.digitalocean.com/community/tutorials/thread-sleep-java

*/

public class Sleep {

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();

        Thread.sleep(2000);

        System.out.println("Sleep time in ms = " + (System.currentTimeMillis() - start));
    }
}
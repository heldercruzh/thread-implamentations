package methods;

/*
	Concepts:
	- wait: 
		- one which waits indefinitely for any other thread to call notify or notifyAll method on the object to wake up the current thread.
		- two variances puts the current thread in wait for specific amount of time before they wake up.
	- notify: wakes up only one thread waiting on the object and that thread starts execution.		
	- notifyAll: wakes up all the threads waiting on the object
	
	https://www.digitalocean.com/community/tutorials/java-thread-wait-notify-and-notifyall-example
*/

// A java bean class on which threads will work and call wait and notify methods.
class Message {
    private String msg;
    
    public Message(String str){
        this.msg=str;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String str) {
        this.msg=str;
    }

}

/*
	A class that will wait for other threads to invoke notify methods to complete it’s processing. 
	Notice that Waiter thread is owning monitor on Message object using synchronized block.
*/
class Waiter implements Runnable{
    
    private Message msg;
    
    public Waiter(Message m){
        this.msg=m;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        synchronized (msg) {
            try{
                System.out.println(name+" waiting to get notified at time:"+System.currentTimeMillis());
                msg.wait();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(name+" waiter thread got notified at time:"+System.currentTimeMillis());
            //process the message now
            System.out.println(name+" processed: "+msg.getMsg());
        }
    }

}

/*
	A class that will process on Message object and then invoke notify method to wake up threads waiting 
	for Message object. Notice that synchronized block is used to own the monitor of Message object.
*/
class Notifier implements Runnable {

    private Message msg;
    
    public Notifier(Message msg) {
        this.msg = msg;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println(name+" started");
        try {
            Thread.sleep(1000);
            synchronized (msg) {
                msg.setMsg(name+" Notifier work done");
                msg.notify();
                // msg.notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
    }

}

/*
 
	Test class that will create multiple threads of Waiter and Notifier and start them.
	
	When we will invoke the above program, we will see below output but program will not complete 
	because there are two threads waiting on Message object and notify() method has wake up only 
	one of them, the other thread is still waiting to get notified.
	
	If we comment the notify() call and uncomment the notifyAll() call in Notifier class, 
	below will be the output produced.
	
	Since notifyAll() method wake up both the Waiter threads and program completes and 
	terminates after execution. That’s all for wait, notify and notifyAll in java.

*/
public class WaitNotify {

    public static void main(String[] args) {
        Message msg = new Message("process it");
        Waiter waiter = new Waiter(msg);
        new Thread(waiter,"waiter").start();
        
        Waiter waiter1 = new Waiter(msg);
        new Thread(waiter1, "waiter1").start();
        
        Notifier notifier = new Notifier(msg);
        new Thread(notifier, "notifier").start();
        System.out.println("All the threads are started");
    }

}




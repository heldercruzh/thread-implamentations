package threadsafe;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


/*
Producer Consumer Service using Blocking Queue Thread Safe GFG
 
We don’t need to worry about waiting for the space to be available for producer or object

Java BlockingQueue doesn’t accept null values and throw NullPointerException 
if you try to store null value in the queue. Java BlockingQueue implementations are 
thread-safe. All queuing methods are atomic in nature and use internal locks or other 
forms of concurrency control. Java BlockingQueue interface is part of java collections 
framework and it’s primarily used for implementing producer consumer problem. 
We don’t need to worry about waiting for the space to be available for producer or object 
to be available for consumer in BlockingQueue because it’s handled by implementation classes
of BlockingQueue. Java provides several BlockingQueue implementations 
such as ArrayBlockingQueue, LinkedBlockingQueue, PriorityBlockingQueue, SynchronousQueue etc. 
While implementing producer consumer problem in BlockingQueue, we will use ArrayBlockingQueue 
implementation.
 
https://www.digitalocean.com/community/tutorials/java-blockingqueue-example
*/

class Message {
    private String msg;
    
    public Message(String str){
        this.msg=str;
    }

    public String getMsg() {
        return msg;
    }
}

class Producer implements Runnable {

    private BlockingQueue<Message> queue;
    
    public Producer(BlockingQueue<Message> q){
        this.queue=q;
    }
    @Override
    public void run() {
        //produce messages
        for(int i=0; i<100; i++){
            Message msg = new Message(""+i);
            try {
                Thread.sleep(i);
                queue.put(msg);
                System.out.println("Produced "+msg.getMsg());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //adding exit message
        Message msg = new Message("exit");
        try {
            queue.put(msg);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

class Consumer implements Runnable{

private BlockingQueue<Message> queue;
    
    public Consumer(BlockingQueue<Message> q){
        this.queue=q;
    }

    @Override
    public void run() {
        try{
            Message msg;
            //consuming messages until exit message is received
            while((msg = queue.take()).getMsg() !="exit"){
            Thread.sleep(10);
            System.out.println("Consumed "+msg.getMsg());
            }
        }catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}

//ProducerConsumerService
public class ProducerConsumerUsingBlockingQueue {

    public static void main(String[] args) {
        //Creating BlockingQueue of size 10
        BlockingQueue<Message> queue = new ArrayBlockingQueue<>(10);
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);
        //starting producer to produce messages in queue
        new Thread(producer).start();
        //starting consumer to consume messages from queue
        new Thread(consumer).start();
        System.out.println("Producer and Consumer has been started");
    }

}

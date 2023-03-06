<h1 align="center">Java multithreading implementation examples<h1> 

	
# Concepts: 
        - Thread is the application can create a small unit of tasks to execute in parallel.
	- Once we start any thread, it’s execution depends on the OS implementation of time 
	slicing and we can’t control heir execution. However we can set threads priority 
	But even then it doesn’t guarantee that higher priority thread will be executed first.
	
# Types 
	- user thread  (ex: main method)
	- daemon thread
	
# Implamentations:
	
	- Extending "Thread" class. (can't return values)
	- Implementing "Runnable" interface. (can't return values) is preferred than Thread, because java supports implementing multiple interfaces.
	- Implementing "Callable" interface. (return some values) 
		- return "Future" object, we can find out the status of the Callable task and get the returned Object

# Life Cycle
  
	- New (When we create a new Thread -> thread is not alive)
	- Runnable (When we call start() function on Thread -> the control is given to Thread scheduler to finish it’s execution)
	- Running (When thread is executing -> Thread scheduler picks one of the thread from the runnable thread pool and change it’s state to Running)
	- Blocked/Waiting (A thread can be waiting for other thread to finish using thread join or it can be waiting for some resources to available.)
	- Dead (Once the thread finished executing, it’s considered to be not alive)
	
# Methods
  
	- sleep: can be used to pause the execution of the current thread for a specified time in milliseconds.
	- join: to wait until the thread on which it’s called is dead.
	- wait: 
		- one which waits indefinitely for any other thread to call notify or notifyAll method on the object to wake up the current thread.
		- two variances puts the current thread in wait for specific amount of time before they wake up.
	- notify: wakes up only one thread waiting on the object and that thread starts execution.		
	- notifyAll: wakes up all the threads waiting on the object
	- shutdown: para terminar a execução de todas as tarefas enviadas e encerrar o pool de threads. 

# Safety
  
	- Blocking Queue: We don’t need to worry about waiting for the space to be available for producer or object
	- Synchronization: JVM guarantees that synchronized code will be executed by only one thread at a time. 
	- Use of Atomic Wrapper classes:  from java.util.concurrent.atomic package. For example AtomicInteger
	- Use of locks from java.util.concurrent.locks package. (recomendations: Avoid Nested Locks, Lock Only What is Required, Avoid waiting 	
  indefinitely).
	- Using thread safe collection classes
	     - ConcurrentHashMap: is similar to HashMap, except that it’s thread-safe and allows modification while iteration.
	- Using volatile keyword with variables to make every thread read the data from memory, not read from thread cache.
	- Safe Singleton: restrict the object created by applications, used wisely to avoid any resource crunch. (use Synchronized block inside the if 	 		  loop and volatile variable)

# PoolExecutor: manages the pool of worker threads. It contains a queue that keeps tasks waiting to get executed.
		
    - interface Executor
		- class Executors: provide simple implementation of ExecutorService using. 
		- ThreadPoolExecutor: provides much more feature, especificar o número de threads que estarão ativos quando criamos a instância.
		- ScheduledThreadPoolExecutor: If you want to schedule a task to run with delay or periodically

package threadsafe;

/*
Singleton Thread Safe

If the Singleton aren't Thread safe multiple threads can access it at the same time. For the first 
few threads when the instance variable is not initialized, multiple threads can enter the if loop and 
create multiple instances. It will break our singleton implementation.

There are three ways through which we can achieve thread safety.

	1) Create the instance variable at the time of class loading. 
		Pros:
			Thread safety without synchronization
			Easy to implement
		Cons:
			Early creation of resource that might not be used in the application.
			The client application can’t pass any argument, so we can’t reuse it. For example, having a generic singleton class for database connection where client application supplies database server properties.
		
	2) Synchronize the getInstance() method. 
		Pros:
			Thread safety is guaranteed.
			Client application can pass parameters
			Lazy initialization achieved
		Cons:
			Slow performance because of locking overhead.
			Unnecessary synchronization that is not required once the instance variable is initialized.
		
	3) Use synchronized block inside the if loop and volatile variable 
		Pros:
			Thread safety is guaranteed
			Client application can pass arguments
			Lazy initialization achieved
			Synchronization overhead is minimal and applicable only for first few threads when the variable is null.
		Cons:
			Extra if condition

*/


// Implementation of Singleton Thread Safe use Synchronized block inside the if loop and volatile variable
public class ASingleton {

	private static volatile ASingleton instance;
	private static Object mutex = new Object();

	private ASingleton() {
	}

	public static ASingleton getInstance() {
		ASingleton result = instance;
		if (result == null) {
			synchronized (mutex) {
				result = instance;
				if (result == null)
					instance = result = new ASingleton();
			}
		}
		return result;
	}

}

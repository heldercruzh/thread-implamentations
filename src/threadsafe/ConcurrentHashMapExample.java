package threadsafe;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*
	ConcurrentHashMap operations are thread-safe. doesn’t allow null for keys and values.
	It's similar to HashMap, except that it’s thread-safe and allows modification while iteration.
	
		ConcurrentHashMap before iterator: {1=1, 5=1, 6=1, 3=1, 4=1, 2=1}
		ConcurrentHashMap after iterator: {1=1, 3new=new3, 5=1, 6=1, 3=1, 4=1, 2=1}
		HashMap before iterator: {3=1, 2=1, 1=1, 6=1, 5=1, 4=1}
		Exception in thread "main" java.util.ConcurrentModificationException
			at java.util.HashMap$HashIterator.nextEntry(HashMap.java:793)
			at java.util.HashMap$KeyIterator.next(HashMap.java:828)
			at com.test.ConcurrentHashMapExample.main(ConcurrentHashMapExample.java:44)
	
	It’s clear from the output that ConcurrentHashMap takes care of the new entry in the map 
	while iteration whereas HashMap throws ConcurrentModificationException. Let’s look at the 
	exception stack trace closely. The following statement has thrown Exception.
	
	
	https://www.digitalocean.com/community/tutorials/concurrenthashmap-in-java

*/

public class ConcurrentHashMapExample {

	public static void main(String[] args) {

		//ConcurrentHashMap
		Map<String,String> myMap = new ConcurrentHashMap<String,String>();
		myMap.put("1", "1");
		myMap.put("2", "1");
		myMap.put("3", "1");
		myMap.put("4", "1");
		myMap.put("5", "1");
		myMap.put("6", "1");
		System.out.println("ConcurrentHashMap before iterator: "+myMap);
		Iterator<String> it = myMap.keySet().iterator();

		while(it.hasNext()){
			String key = it.next();
			if(key.equals("3")) myMap.put(key+"new", "new3");
		}
		System.out.println("ConcurrentHashMap after iterator: "+myMap);

		//HashMap
		myMap = new HashMap<String,String>();
		myMap.put("1", "1");
		myMap.put("2", "1");
		myMap.put("3", "1");
		myMap.put("4", "1");
		myMap.put("5", "1");
		myMap.put("6", "1");
		System.out.println("HashMap before iterator: "+myMap);
		Iterator<String> it1 = myMap.keySet().iterator();

		while(it1.hasNext()){
			String key = it1.next();
			if(key.equals("3")) myMap.put(key+"new", "new3");
			//All we need to do is add a break statement after the put call.
			//break;
			
			//E se não adicionarmos uma nova entrada, mas atualizarmos o par chave-valor existente? Vai lançar exceção?
			//myMap.put(key, "new3");
		}
		System.out.println("HashMap after iterator: "+myMap);
	}

}
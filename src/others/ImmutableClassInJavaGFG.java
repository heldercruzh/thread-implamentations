package others;

/*
Immutable class in Java
--------------------------
https://www.geeksforgeeks.org/create-immutable-class-java/

Em java significa que, uma vez que um objeto é criado, não podemos alterar seu conteúdo.

Question: What classes in Java standard API are immutable?
Em Java, todas as classes wrapper (como Integer, Boolean, Byte, Short) e a classe String são imutáveis.

---Seguem os requisitos para criar nossa própria classe imutável---

	final 
		- A classe deve ser declarada como final para que classes filhas não possam ser criadas.
		- Os membros de dados na classe devem ser declarados como finais para que não possamos alterar o valor dela após a criação do objeto.
		
	private 
		- Os membros de dados na classe devem ser declarados privados para que o acesso direto não seja permitido
	
	construtor parametrizado
		- Um construtor parametrizado deve inicializar todos os campos executando uma cópia profunda para que os membros de dados não possam ser modificados com uma referência de objeto.
	
	getter retornar cópia em vez da referência real do objeto
		- A cópia profunda de objetos deve ser executada nos métodos getter para retornar uma cópia em vez de retornar a referência real do objeto)
	
	Não deve haver setters
		- não deve haver nenhuma opção para alterar o valor da variável de instância.
 */


//Java Program to Create An Immutable Class

//Importing required classes
import java.util.HashMap;
import java.util.Map;

//Class 1
//An immutable class
final class Student {

	// Member attributes of final class
	private final String name;
	private final int regNo;
	private final Map<String, String> metadata;

	// Constructor of immutable class
	// Parameterized constructor
	public Student(String name, int regNo,
				Map<String, String> metadata)
	{

		// This keyword refers to current instance itself
		this.name = name;
		this.regNo = regNo;

		// Creating Map object with reference to HashMap
		// Declaring object of string type
		Map<String, String> tempMap = new HashMap<>();

		// Iterating using for-each loop
		for (Map.Entry<String, String> entry :
			metadata.entrySet()) {
			tempMap.put(entry.getKey(), entry.getValue());
		}

		this.metadata = tempMap;
	}

	// Method 1
	public String getName() { return name; }

	// Method 2
	public int getRegNo() { return regNo; }

	// Note that there should not be any setters

	// Method 3
	// User -defined type
	// To get meta data
	public Map<String, String> getMetadata()
	{

		// Creating Map with HashMap reference
		Map<String, String> tempMap = new HashMap<>();

		for (Map.Entry<String, String> entry :
			this.metadata.entrySet()) {
			tempMap.put(entry.getKey(), entry.getValue());
		}
		return tempMap;
	}
}

//Class 2
//Main class
public class ImmutableClassInJavaGFG {

	// Main driver method
	public static void main(String[] args)
	{

		// Creating Map object with reference to HashMap
		Map<String, String> map = new HashMap<>();

		// Adding elements to Map object
		// using put() method
		map.put("1", "first");
		map.put("2", "second");

		Student s = new Student("ABC", 101, map);

		// Calling the above methods 1,2,3 of class1
		// inside main() method in class2 and
		// executing the print statement over them
		System.out.println(s.getName());
		System.out.println(s.getRegNo());
		System.out.println(s.getMetadata());

		// Uncommenting below line causes error
		// s.regNo = 102;

		map.put("3", "third");
		// Remains unchanged due to deep copy in constructor
		System.out.println(s.getMetadata());
		s.getMetadata().put("4", "fourth");
		// Remains unchanged due to deep copy in getter
		System.out.println(s.getMetadata());
	}
}


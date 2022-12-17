package com.kirangs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Java provides a new feature called method reference in Java 8. Method
 * reference is used to refer method of the functional interface. It is a
 * compact and easy form of a lambda expression. Each time when you are using a
 * lambda expression to just referring a method, you can replace your lambda
 * expression with a method reference.
 * 
 * Types of Method Reference
 * 
 * 
 * 1. Reference to a static method
 * 
 * syntax : ClassName::StaticMethodName
 * 
 * 2. Reference to an instance method of a particular object
 * 
 * syntax : ObjectName::InstanceMethodName
 * 
 * 3. Reference to an instance method of an arbitrary object
 * 
 * syntax : ObjectName::InstanceMethodName
 * 
 * 3. Reference to a constructor
 * 
 * syntax : ClassName::new
 */

@FunctionalInterface
interface Printable {

	void printMessage(String message);
}

class Test {
	
	public Test() {
		
	}
	
	public Test(String message) {
		System.out.println(message);
	}

	public void display(String message) {

		message = message.toUpperCase();
		System.out.println(message);
	}

	public static String sayHello(String string) {

		return "Hi, " + string;
	}

	public static int addition(int a, int b) {

		return a + b;
	}

}

public class MethodReferencesDemo {

	public static void main(String[] args) {

		/**
		 * Reference to a static method
		 */
		Function<Integer, Double> function = number -> Math.sqrt(number);
		System.out.println(function.apply(256));

		// let's refer static method of some predefined class

		Function<Integer, Double> function2 = Math::sqrt;
		System.out.println(function2.apply(256));

		// for the implementation of apply() in Function, we are referring
		// same such static method of another class (i.e a method that takes one
		// parameter
		// and return result)

		Function<String, String> function3 = Test::sayHello;
		System.out.println(function3.apply("Kiran"));

		// referring a method in lambda implementation

		BiFunction<Integer, Integer, Integer> biFunction = (a, b) -> Test.addition(a, b);
		System.out.println(biFunction.apply(10, 15));

		BiFunction<Integer, Integer, Integer> biFunction2 = Test::addition;
		System.out.println(biFunction2.apply(10, 15));

		/**
		 * Reference to an instance method of a particular object
		 */

		Test test = new Test();
		test.display("Kiran");

		// lambda expression to provide implementation for functional interface
		// Printable

		Printable printable = message -> test.display(message);
		printable.printMessage("Kumar");

		// method reference

		Printable printable2 = test::display;
		printable2.printMessage("g s");

		
		/**
		 * Reference to an instance method of an arbitrary object
		 */

		Function<String, String> function4 = string -> string.toLowerCase();
		System.out.println(function4.apply("KIRAN"));

		Function<String, String> function5 = String::toLowerCase;
		System.out.println(function5.apply("KIRAN"));
		
		String[] strArray= {"E","D","C","B","A"};
		
		//lambda
		
		Arrays.sort(strArray, (ele1,ele2)-> ele1.compareToIgnoreCase(ele2));
		
		for(String str:strArray) {
			System.out.print(str+" ");
		}
		
		System.out.println();
		
		//method reference
		
		Arrays.sort(strArray, String::compareToIgnoreCase );
		
		for(String str:strArray) {
			System.out.print(str+" ");
		}
		System.out.println();
		
		

		/**
		 * Reference to an constructor
		 */
		
		List<String> fruits=new ArrayList<>();
		fruits.add("Banana");
		fruits.add("Mango");
		fruits.add("Apples");
		fruits.add("watermelon");
		
		//lambda
		
		Function<List<String>, Set<String>> function6=fruitsList-> new HashSet<String>(fruits);
		System.out.println(function6.apply(fruits));
		
		//method reference
		
		Function<List<String>, Set<String>> function7=HashSet::new;
		System.out.println(function7.apply(fruits));
		

	}

}

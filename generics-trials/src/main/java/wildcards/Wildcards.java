package wildcards;

import java.util.ArrayList;
import java.util.List;

public class Wildcards {

	/* PECS - Producer Extends, Consumer Super:
	 * ----------------------------------------
	 * Producer Extends: 
	 * Upper bounded wildcards ensure that the actual value is at least as derived as the "extends X" type.
	 * For example, ? extends Integer means that the type is Integer or a more dervied type, 
	 * but it can't be Number or Object. It ensures that the provided object has an API of that type. 
	 * In the former example, the object will at least have the API of Integer. (It may be a more derived type, 
	 * with a more extended API, but we can't know it. We only know it has the Integer API).
	 * This way we can have a List<? extends Integer>, and we can get an object from it and use it with the Integer API, 
	 * or use it as a parameter to a method that require Integer.
	 * On the other hand, we can't add any object to the List<? extends Integer> since we don't know the actual type of
	 * its members. It may be Integers or a collection of a much more derived type - and we can't know how much derived.
	 * That's why it's called "Producer Extends", because this form of "extends" is used to produce objects for our use,
	 * and it can't consume objects.
	 * 
	 * Consumer Super:
	 * Lower bounded wildcards ensure that the actual value is at least as basic as the "super X" type.
	 * It may be X or one its ancestors - it may even be "Object".
	 * If we have a List<? super Integer>, the list may be of Integers or of Numbers or of Object. We can't get an
	 * object from this list as: Integer i = list.get(0);  since this list may contain Numbers or Objects (that are also 
	 * Integer's super types). 
	 * [Note: we can use the produced object as Object, since all objects are Object, but we can't use any specific type.] 
	 * We can only add to it any objects that are Integers (or more derived), since a derived object 
	 * may be assigned to a collection of a less derived type:
	 * 
	 * List<Object> objects = new ArrayList<Object>();
	 * objects.add(new Object());
	 * objects.add(5);
	 * 
	 * That's why it's called "Consumer Super", since the usage of the "super" bound enables this collection only to consume!	
	 * 	
	 */
	
	public void upperBoundedWildcard(List<? super Integer> list) {
		Object o = list.get(0);
		List<Object> objects = new ArrayList<Object>();
		objects.add(new Object());
		objects.add(5);
	}
}

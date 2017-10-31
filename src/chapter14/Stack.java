package chapter14;
/* 
  	W.P. Iverson
 	Bellevue College CS211
  	January, 2017 	
 	
 	Provided as a Stack Class alternative
 	Limits user to actual Stack methods
 	so Vector<E> is not available
*/
public class Stack<E> {
	// avoid blanked import of java.util
	private java.util.Stack<E> secret;
	
	// default constructor
	public Stack() {
		secret = new java.util.Stack<>();
	} 
	
	// empty that collection
	public void clear() {
		secret.clear();
	}
	
	// should be order constant
	public int size() {
		return secret.size();
	}

	// simply have push call push from API
	public E push(E a) {
		secret.push(a);
		return a;
	}

	// And, empty calls empty from API
	public boolean empty() {
		return secret.empty();
	}

	// And my pop() uses pop() from JAVA API
	public E pop() {
		return secret.pop();
	}

	// My peek uses their peek
	public E peek() {
		return secret.peek();
	}
	
	// Following are not basic Stack operations
	// but needed to do some simple testing
	
	// toString is probably not O(constant)
        @Override
	public String toString() {
		return secret.toString();
	}

}

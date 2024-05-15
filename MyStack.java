import java.util.ArrayList;

/**
 * Stack class
 * 
 * @param <T> data type
 * @author Vivian Dang
 * 
 */
public class MyStack<T> implements StackInterface<T> {

    private int top;
    private T[] data; 

    /**
     * Constructor that takes an int as the size of the Stack
     * @param size the size of the Stack
     */
    @SuppressWarnings("unchecked")
    public MyStack(int size) {
        data = (T[]) new Object[size]; 
        top = -1;
    }
    /**
     * Default constructor with a default size of 10
     */
    public MyStack() {
        this(10); 
    }

    /**
     * Constructor that takes an ArrayList as a parameter, and fills the stack with it
     * 
     * @param list an ArrayList
     */
    @SuppressWarnings("unchecked")
    public MyStack(ArrayList<T> list) {
        data = (T[]) new Object[list.size()]; // Initialize array with size of ArrayList
        top = list.size() - 1;
        for (int i = 0; i < list.size(); i++) {
            data[i] = list.get(i);
        }
    }

    /**
     * Determines if Stack is empty
     * @return true if Stack is empty, false if not
     */
    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * Determines if Stack is full
     * @return true if Stack is full, false if not
     */
    @Override
    public boolean isFull() {
        return top == data.length - 1;
    }

	/**
	 * Deletes and returns the element at the top of the Stack
	 * @return the element at the top of the Stack
	 * @throws StackUnderflowException if stack is empty
	 */
    @Override
    public T pop() throws StackUnderflowException {
        if (!isEmpty()) {
            return data[top--];
        } else { 
            throw new StackUnderflowException();
        }
    }

    /**
	 * Returns the element at the top of the Stack, does not pop it off the Stack
	 * @return the element at the top of the Stack
	 * @throws StackUnderflowException if stack is empty
	 */
    @Override
    public T top() throws StackUnderflowException {
        if (!isEmpty()) {
            return data[top];
        } else {
            throw new StackUnderflowException();
        }
    }

    /**
     * Number of elements in the Stack
     * @return the number of elements in the Stack
     */
    @Override
    public int size() {
        return top + 1;
    }

    /**
	 * Adds an element to the top of the Stack
	 * @param e the element to add to the top of the Stack
	 * @return true if the add was successful, false if not
	 * @throws StackOverflowException if stack is full
	 */
    @Override
    public boolean push(T e) throws StackOverflowException {
        if (!isFull()) {
            data[++top] = e;
            return true;
        } else {
            throw new StackOverflowException();
        }
    }

    /**
	 * Returns the elements of the Stack in a string from bottom to top, the beginning of the String is the bottom of the stack	 * @return an string which represent the Objects in the Stack from bottom to top
	 */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= top; i++) {
            sb.append(data[i].toString());
        }
        return sb.toString();
    }

    /**
	 * Returns the string representation of the elements in the Stack, the beginning of the string is the bottom of the stack
	 * Places the delimiter between all elements of the Stack
	 * @return string representation of the Stack from bottom to top with elements 
	 * separated with the delimiter
	 */
    public String toString(String delimiter) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= top; i++) {
            sb.append(data[i].toString());
            if (i < top) {
                sb.append(delimiter);
            }
        }
        return sb.toString();
    }
    /**
	  * Fills the Stack with the elements of the ArrayList, First element in the ArrayList
	  * is the first bottom element of the Stack
	  * @param list elements to be added to the Stack from bottom to top
	  * @throws StackOverflowException if stack gets full
	  */
    @SuppressWarnings("unchecked")
	@Override
    public void fill(ArrayList<T> list)  throws StackOverflowException {
        data = (T[]) new Object[list.size()]; 
        top = list.size() - 1;
        for (int i = 0; i < list.size(); i++) {
            data[i] = list.get(i);
        }
    }
}
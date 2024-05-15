import java.util.ArrayList;

/**
 * Queue data structure
 * 
 *  @param <T> data type
 *  @author Vivian Dang
 */
public class MyQueue<T> implements QueueInterface<T> {
    private T[] queue;
    private int front;
    private int end;
    private int size;

    /**
     * Constructor that takes an int as the size of the queue
     * @param size the size of the queue
     */
    @SuppressWarnings("unchecked")
    public MyQueue(int size) {
        queue = (T[]) new Object[size];
        front = 0;
        end = -1;
        this.size = 0;
    }

    /**
     * Default constructor with a default size of 10
     */
    public MyQueue() {
        this(10); 
    }

    /** 
     * Determines if Queue is empty
	 * @return true if Queue is empty, false if not
	 */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    
    /**
	 * Determines of the Queue is Full
	 * @return true if Queue is full, false if not
	 */
    @Override
    public boolean isFull() {
        return size == queue.length;
    }

    /**
	 * Deletes and returns the element at the front of the Queue
	 * @return the element at the front of the Queue
	 * @throws QueueUnderflowException if queue is empty
	 */
    @Override
	public T dequeue() throws QueueUnderflowException {
		T t;
		if(isEmpty())
			throw new QueueUnderflowException();
		else {
			t = queue[front];
			queue[front] = null;
			front = (front +1) % queue.length;
		}
		return t;
	}

    /**
	 * Returns number of elements in the Queue
	 * @return the number of elements in the Queue
	 */
    @Override
    public int size() {
        return this.size;
    }

    /**
	 * Adds an element to the end of the Queue
	 * @param e the element to add to the end of the Queue
	 * @return true if the add was successful
	 * @throws QueueOverflowException if queue is full
	 */
    @Override
	public boolean enqueue(T e) throws QueueOverflowException {
		if(isFull())
			throw new QueueOverflowException();
		else {
			queue[end + 1] = e;
			end = (end + 1) % queue.length;
			if(front == -1)
				front = 0;
			return true;
		}
	}


	/**
	 * Returns the string representation of the elements in the Queue, 
	 * the beginning of the string is the front of the queue
	 * @return string representation of the Queue with elements
	 */
    @Override
    public String toString() {
        return toString(", ");
    }
    
    /**
	 * Returns the string representation of the elements in the Queue, 
	 * the beginning of the string is the front of the queue
	 * Place the delimiter between all elements of the Queue
	 * @return string representation of the Queue with elements separated with the delimiter
	 */
    @Override
    public String toString(String delimiter) {
        if (isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = front;
        for (int i = 0; i < size; i++) {
            sb.append(queue[index]);
            if (i < size - 1) {
                sb.append(delimiter);
            }
            index = (index + 1) % queue.length;
        }
        return sb.toString();
    }
    
    /**
	  * Fills the Queue with the elements of the ArrayList, First element in the ArrayList is the first element in the Queue
	  * @param list elements to be added to the Queue
	  * @throws QueueOverflowException if queue is full
	 
	  */
    @Override
	public void fill(ArrayList<T> list) {
		for (int i = 0; i < list.size(); i++)
			queue[i] = list.get(i);
	}
}

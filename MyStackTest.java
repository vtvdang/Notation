import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class MyStackTest {
    public MyStack<String> stringS;
    public String a="a", b="b", c="c", d="d", e="e", f="f";
    public ArrayList<String> fill = new ArrayList<String>();

    // STUDENT: student tests will use the doubleS
    public MyStack<Double> doubleS;

    @BeforeEach
    public void setUp() throws Exception {
        stringS = new MyStack<String>(5);
        stringS.push(a);
        stringS.push(b);
        stringS.push(c);
        // STUDENT: add setup for doubleS for student tests
        doubleS = new MyStack<Double>(5);
        doubleS.push(1.0);
        doubleS.push(2.0);
        doubleS.push(3.0);
    }

    @AfterEach
    public void tearDown() throws Exception {
        stringS = null;
        doubleS = null;
    }

    @Test
    public void testIsEmpty() throws StackUnderflowException {
        assertEquals(false,stringS.isEmpty());
        stringS.pop();
        stringS.pop();
        stringS.pop();
        assertEquals(true, stringS.isEmpty());
    }

    @Test
    public void testIsFull() throws StackOverflowException {
        assertEquals(false, stringS.isFull());
        stringS.push(d);
        stringS.push(e);
        assertEquals(true, stringS.isFull());
    }

    @Test
    public void testPop() {
        try {
            assertEquals(c, stringS.pop());
            assertEquals(b, stringS.pop());
            assertEquals(a, stringS.pop());
            //Queue is empty, next statement should cause QueueUnderFlowException
            stringS.pop();
            assertTrue(false, "This should have caused an StackUnderflowException");
        }
        catch (StackUnderflowException e){
            assertTrue(true, "This should have caused an StackUnderflowException");
        }
        catch (Exception e){
            assertTrue(false, "This should have caused an StackUnderflowException");
        }
    }

    @Test
    public void testPopStudent() {
        try {
            assertEquals(3.0, doubleS.pop());
            assertEquals(2.0, doubleS.pop());
            assertEquals(1.0, doubleS.pop());
            //Stack is empty, next statement should cause StackUnderflowException
            doubleS.pop();
            assertTrue(false, "This should have caused an StackUnderflowException");
        } catch (StackUnderflowException e) {
            assertTrue(true, "This should have caused an StackUnderflowException");
        } catch (Exception e) {
            assertTrue(false, "This should have caused an StackUnderflowException");
        }
    }

    @Test
    public void testTop() throws StackUnderflowException, StackOverflowException {
        assertEquals(c, stringS.top());
        stringS.push(d);
        assertEquals(d, stringS.top());
        stringS.pop();
        stringS.pop();
        assertEquals(b, stringS.top());       
    }

    @Test
    public void testSize() throws StackOverflowException, StackUnderflowException {
        assertEquals(3, stringS.size());
        stringS.push(d);
        assertEquals(4, stringS.size());
        stringS.pop();
        stringS.pop();
        assertEquals(2, stringS.size());
    }

    @Test
    public void testPush() {
        try {
            assertEquals(3, stringS.size());
            assertEquals(true, stringS.push(d));
            assertEquals(4, stringS.size());
            assertEquals(true, stringS.push(e));
            assertEquals(5, stringS.size());
            //Queue is full, next statement should cause QueueOverFlowException
            stringS.push(f);
            assertTrue(false, "This should have caused an StackOverflowException");
        }
        catch (StackOverflowException e){
            assertTrue(true, "This should have caused an StackOverflowException");
        }
        catch (Exception e){
            assertTrue(false, "This should have caused an StackOverflowException");
        }
    }

    @Test
    public void testPushStudent() {
        try {
            assertEquals(3.0, doubleS.top());
            assertEquals(true, doubleS.push(4.0));
            assertEquals(4.0, doubleS.top());
            assertEquals(true, doubleS.push(5.0));
            assertEquals(5.0, doubleS.top());
            //Stack is full, next statement should cause StackOverflowException
            doubleS.push(6.0);
            assertTrue(false, "This should have caused an StackOverflowException");
        }
        catch (StackOverflowException e){
            assertTrue(true, "This should have caused an StackOverflowException");
        }
        catch (Exception e){
            assertTrue(false, "This should have caused an StackOverflowException");
        }
    }

    @Test
    public void testToString() throws StackOverflowException {
        assertEquals("abc", stringS.toString());
        stringS.push(d);
        assertEquals("abcd", stringS.toString());
        stringS.push(e);
        assertEquals("abcde", stringS.toString());
    }

    @Test
    public void testToStringStudent() {
        assertEquals("1.02.03.0", doubleS.toString());
    }

    @Test
    public void testToStringDelimiter() throws StackOverflowException {
        assertEquals("a%b%c", stringS.toString("%"));
        stringS.push(d);
        assertEquals("a&b&c&d", stringS.toString("&"));
        stringS.push(e);
        assertEquals("a/b/c/d/e", stringS.toString("/"));
    }

    @Test
    public void testFill() throws StackOverflowException, StackUnderflowException {
        fill.add("apple");
        fill.add("banana");
        fill.add("carrot");
        //start with an empty queue
        stringS = new MyStack<String>(5);
        //fill with an ArrayList
        stringS.fill(fill);
        assertEquals(3,stringS.size());
        assertEquals("carrot", stringS.pop());
        assertEquals("banana", stringS.pop());
        assertEquals("apple", stringS.pop());       
    }

}

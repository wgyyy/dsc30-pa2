import org.junit.Test;

import static org.junit.Assert.*;

public class StringStackTest {

    @Test
    public void isEmpty() {
        StringStack test = new StringStack(6);
        assertEquals(true, test.isEmpty());
        test.push("hello");
        test.push("world");
        assertEquals(false, test.isEmpty());
    }

    @Test
    public void clear() {
        StringStack test = new StringStack(6);
        test.push("hello");
        test.push("world");
        test.clear();
        assertEquals(true, test.isEmpty());
    }

    @Test
    public void size() {
        StringStack test = new StringStack(6);
        assertEquals(0, test.size());
        test.push("hello");
        test.push("world");
        assertEquals(2, test.size());
        test.multiPush(new String[]{"DSC","30","!"});
        assertEquals(5, test.size());
    }

    @Test
    public void capacity() {
        StringStack test = new StringStack(6);
        test.push("hello");
        test.push("world");
        assertEquals(6, test.capacity());
        test.multiPush(new String[]{"DSC","30","!"});
        test.multiPush(new String[]{"what","is","this"});
        assertEquals(12, test.capacity());
    }

    @Test
    public void peek() {
        StringStack test = new StringStack(6);
        test.push("hello");
        test.push("world");
        assertEquals("world", test.peek());
        test.multiPush(new String[]{"DSC","30","!"});
        test.multiPush(new String[]{"what","is","this"});
        assertEquals("this", test.peek());
    }

    @Test
    public void push() {
        StringStack test = new StringStack(6);
        test.push("hello");
        test.push("world");
        assertEquals("world", test.peek());
        test.multiPush(new String[]{"DSC","30","!"});
        test.multiPush(new String[]{"what","is","this"});
        assertEquals("this", test.peek());
    }

    @Test
    public void pop() {
        StringStack test = new StringStack(6);
        test.push("hello");
        test.push("world");
        assertEquals("world", test.pop());
        test.multiPush(new String[]{"DSC","30","!"});
        test.multiPush(new String[]{"what","is","this"});
        assertEquals("this", test.pop());
        assertEquals("is", test.pop());
    }

    @Test
    public void multiPush() {
    }

    @Test
    public void multiPop() {
        StringStack test = new StringStack(6);
        test.push("hello");
        test.push("world");
        assertEquals(new String[] {"world","hello"}, test.multiPop(2));
        test.multiPush(new String[]{"DSC","30","!"});
        test.multiPush(new String[]{"what","is","this"});
        assertEquals(new String[] {"this","is","what"}, test.multiPop(3));
    }
}
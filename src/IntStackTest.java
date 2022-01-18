import org.junit.Test;

import java.util.EmptyStackException;

import static org.junit.Assert.*;

public class IntStackTest {

    @Test
    public void isEmptyTest() {
        IntStack test = new IntStack(5);
        boolean expected = true;
        assertEquals(expected,test.isEmpty());
        test.push(1);
        expected=false;
        assertEquals(expected,test.isEmpty());
        test.push(2);
        assertEquals(expected,test.isEmpty());
    }

    @Test
    public void clearTest() {
        IntStack test=new IntStack(5);
        test.push(1);
        test.clear();
        int expected=0;
        assertEquals(expected,test.size());
        test.push(2);
        test.clear();
        assertEquals(expected,test.size());
        test.multiPush(new int[]{1, 2, 3, 4,5,6});
        test.clear();
        expected=5;
        assertEquals(expected,test.capacity());

    }

    @Test
    public void sizeTest() {
        IntStack test=new IntStack(5);
        test.push(1);
        int expected=1;
        assertEquals(expected,test.size());
        test.push(0);
        expected=2;
        assertEquals(expected,test.size());
        test.clear();
        expected=0;
        assertEquals(expected,test.size());
    }

    @Test
    public void capacityTest() {
        IntStack test=new IntStack(5);
        int expected=5;
        assertEquals(expected,test.capacity());
        test.push(2);
        assertEquals(expected,test.capacity());
        test.multiPush(new int[]{1,2,3,4,5});
        expected=10;
        assertEquals(expected,test.capacity());
    }

    @Test
    public void peekTest() {
        IntStack test=new IntStack(5);
        test.push(1);
        int expected_value=1;
        assertEquals(expected_value,test.peek());
        test.push(0);
        expected_value=0;
        assertEquals(expected_value,test.peek());
        test.push(2);
        expected_value=2;
        assertEquals(expected_value,test.peek());

    }

    @Test
    public void pushTest() {
        IntStack test=new IntStack(5);
        test.push(1);
        int expected=1;
        assertEquals(expected,test.size());
        test.push(2);
        expected=2;
        assertEquals(expected,test.size());
        test.push(0);
        expected=3;
        assertEquals(expected,test.size());
    }

    @Test
    public void popTest() {
        IntStack test=new IntStack(5);
        test.push(1);
        int expected=1;
        assertEquals(expected,test.pop());
        test.push(2);
        expected=2;
        assertEquals(expected,test.pop());
        test.push(0);
        expected=0;
        assertEquals(expected,test.pop());
    }

    @Test
    public void multiPushTest() {
        IntStack test=new IntStack(5);
        test.multiPush(new int[] {1,2,3,4});
        int expected=4;
        assertEquals(expected,test.size());
        test.multiPush(new int[] {1,2,3});
        expected=7;
        assertEquals(expected,test.size());
        test.multiPush(new int[] {1});
        expected=8;
        assertEquals(expected,test.size());

    }

    @Test
    public void multiPopTest() {
        IntStack test=new IntStack(5);
        test.multiPush(new int[] {1,2,3,4});
        int[] expected=new int[]{1,2,3,4};
        assertEquals(expected.length,test.multiPop(4).length);
        test.multiPush(new int[] {1,2,3});
        expected=new int[]{1,2,3};
        assertEquals(expected.length,test.multiPop(3).length);
        test.multiPush(new int[] {1});
        expected=new int[] {1};
        assertEquals(expected.length,test.multiPop(1).length);
    }
}
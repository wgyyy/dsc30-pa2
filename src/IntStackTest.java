import org.junit.Test;

import java.awt.*;
import java.util.EmptyStackException;

import static org.junit.Assert.*;

public class IntStackTest {

    @Test (expected = IllegalArgumentException.class)
    public void ISTIAE(){
        IntStack test =new IntStack(5, 0.75,0.9);
        test =new IntStack(5, 0.2,0.25);
        test =new IntStack(1, 0.75,0.25);
        test =new IntStack(5, 0.2);
        test =new IntStack(1, 0.75);
        test =new IntStack(1);
    }

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

    @Test (expected = EmptyStackException.class)
    public void peekESE() {
        IntStack test = new IntStack(5);
        test.peek();
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
        assertEquals(1,test.peek());
        assertEquals(expected,test.size());
        test.push(2);
        expected=2;
        assertEquals(2,test.peek());
        assertEquals(expected,test.size());
        test.push(0);
        expected=3;
        assertEquals(0,test.peek());
        assertEquals(expected,test.size());
    }

    @Test (expected = EmptyStackException.class)
    public void popESE() {
        IntStack test = new IntStack(5);
        test.peek();
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

    @Test (expected = IllegalArgumentException.class)
    public void multiPushIAE() {
        IntStack test = new IntStack(5);
        test.multiPush(null);
    }

    @Test
    public void multiPushTest() {
        IntStack test=new IntStack(5);
        test.multiPush(new int[] {1,2,3,4});
        int expected=4;
        assertEquals(expected,test.size());
        assertEquals(4,test.pop());
        assertEquals(3,test.pop());
        test.multiPush(new int[] {1,2,3});
        expected=5;
        assertEquals(expected,test.size());
        assertEquals(3,test.peek());
        test.multiPush(new int[] {1});
        expected=6;
        assertEquals(expected,test.size());

    }

    @Test (expected = IllegalArgumentException.class)
    public void multiPopIAE() {
        IntStack test = new IntStack(5);
        test.multiPop(-1);
    }

    @Test
    public void multiPopTest() {
        IntStack test=new IntStack(5);
        test.multiPush(new int[] {1,2,3,4});
        int[] expected=new int[]{4,3,2,1};
        assertEquals(expected.length,test.multiPop(4).length);
        test.multiPush(new int[] {1,2,3});
        expected=new int[]{3,2,1};
        assertEquals(expected.length,test.multiPop(3).length);
        test.multiPush(new int[] {1});
        expected=new int[] {1};
        assertEquals(expected.length,test.multiPop(1).length);
    }
}
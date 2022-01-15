/*
    Name: Gaoying Wang
    PID:  A16131629
 */

import java.util.EmptyStackException;

/**
 * This class creates a Stack for strings
 * @author Gaoying Wang
 * @since  ${2022-01-14}
 */
public class StringStack {

    /* instance variables, feel free to add more if you need */
    private String[] data;
    private int nElems;
    private double loadFactor;
    private double shrinkFactor;

    public StringStack(int capacity, double loadF, double shrinkF) {
        if (capacity<5&&0.67<=loadF&&loadF<=1&&0 < shrinkF&&shrinkF <= 0.33) {
            throw new IllegalArgumentException();
        }
            /*
            raise exception when any of capacity, loadF, and shrinkF
            is invalid.
             */
        data = new String[capacity];
        loadFactor = loadF;
        shrinkFactor = shrinkF;
    }

    public StringStack(int capacity, double loadF) {
        this(capacity, loadF, 0.25);
        /*
        raise exception when any of capacity, and loadF
        is invalid.
        */
    }

    public StringStack(int capacity) {
        this(capacity,0.75,0.25);
        /*
        raise exception when capacity is invalid.
        */
    }

    public boolean isEmpty() {
        if (data[0]==null){
            return true;
        }else {
            return false;
        }
    }

    public void clear() {
        for (int x=0;x<data.length;x++){
            data[x]=null;
        }
    }

    public int size() {
        /* TODO */
        return 0;
    }

    public int capacity() {
        int count=0;
        for (int y=0;y<data.length;y++){
            if (data[y]!=null) {
                count++;
            }
        }
        return count;
    }

    public String peek() {
        if (isEmpty()==true){
            throw new EmptyStackException();
        }
        return data[data.length-1];
        /*
        raise an exception if the stack is empty
         */
    }

    public void push(String element) {
        if ((this.size()/this.capacity())>=this.loadFactor){
            String[] stored_values=new String[data.length];
            for (int x=0;x<data.length;x++){
                stored_values[x]=data[x];
            }
            data=new String[data.length*2];
            for (int y=0;y<stored_values.length;y++){
                data[y]=stored_values[y];
            }
        }
        for (int m=0;m<data.length;m++){
            if (data[m]==null){
                data[m]=element;
                break;
            }
        }
    }

    public String pop() {
        if (isEmpty()==true){
            throw new EmptyStackException();
        }
        /*
        raise an exception if the stack is empty
         */
        String pop_element = null;
        for (int y = data.length - 1; y >= 0; y--) {
            if (data[y] != null) {
                pop_element = data[y];
                data[y] = null;
                break;
            }
        }
        if ((this.size() / this.capacity()) <= this.shrinkFactor) {
            String[] stored_values = new String[data.length];
            for (int x = 0; x < data.length; x++) {
                stored_values[x] = data[x];
            }
            if ((data.length / 2) < capacity()) {
                data = new String[capacity()];
            } else {
                data = new String[data.length / 2];
            }
            for (int x = 0; x < data.length; x++) {
                data[x] = stored_values[x];
            }
        }
        return pop_element;
    }

    public void multiPush(String[] elements) {
        for (int m=0;m<elements.length;m++){
            if (elements[m]==null){
                throw new IllegalArgumentException();
            }
        }
        /*
        raise an exception if there are null elements in elements.
         */
        for (int y=0;y<data.length;y++){
            if (data[y]==null){
                for (int x=0;x<elements.length;x++){
                    push(elements[x]);
                }
                break;
            }
        }
    }

    public String[] multiPop(int amount) {
        if (amount<0){
            throw new IllegalArgumentException();
        }
        /*
        raise an exception if the amount is a negative number.
         */
        String[] popped=new String[amount];
        for (int x=1;x<amount+1;x++){
            popped[x-1]=pop();

        }
        return popped;
    }
}
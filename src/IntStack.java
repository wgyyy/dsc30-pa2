/*
 * Name: Gaoying Wang
 * PID: A16131629
 */

import java.util.EmptyStackException;

/**
 * This class creates a data type integer stack
 * @author Gaoying Wang
 * @since  ${2022-03-04}
 */
public class IntStack {

    /* instance variables, feel free to add more if you need */
    private int[] data;
    private int nElems;
    private double loadFactor;
    private double shrinkFactor;
    private int capacity_origin;

    public IntStack(int capacity, double loadF, double shrinkF) {
            if (!(capacity>=5) || !(0.67<=loadF&&loadF<=1) || !(0 < shrinkF&&shrinkF <= 0.33)) {
                throw new IllegalArgumentException();
            }
            /*
            raise exception when any of capacity, loadF, and shrinkF
            is invalid.
             */
            this.data = new int[capacity];
            this.loadFactor = loadF;
            this.shrinkFactor = shrinkF;
            this.capacity_origin=capacity;
            this.nElems = 0;
    }

    public IntStack(int capacity, double loadF) {
        this(capacity, loadF, 0.25);
        /*
        raise exception when any of capacity, and loadF
        is invalid.
        */

    }

    public IntStack(int capacity) {
        this(capacity,0.75,0.25);
        /*
        raise exception when capacity is invalid.
        */
    }

    public boolean isEmpty() {
        if (nElems==0){
            return true;
        }else {
            return false;
        }

    }

    public void clear() {
       this.data=new int[capacity_origin];
       this.nElems=0;
    }

    public int size() {
        return nElems;
    }

    public int capacity() {
        return this.data.length;
    }

    public int peek() {
        if (isEmpty()){
            throw new EmptyStackException();
        }
        return data[nElems-1];
        /*
        raise an exception if the stack is empty
         */
    }

    public void push(int element) {
        if (((double)this.size()/this.capacity())>=this.loadFactor){
            int[] new_list = new int[this.data.length*2];
            for (int i = 0; i < this.data.length; i++){
                new_list[i] = this.data[i];
            }
            this.data = new_list;
        }
        data[nElems]=element;
        nElems++;
    }

    public int pop() {
        if (isEmpty()){
            throw new EmptyStackException();
        }
        /*
        raise an exception if the stack is empty
         */
        int pop_element;
        pop_element=this.data[nElems-1];
        this.data[nElems-1]=0;
        if (((double)this.size()/this.capacity()) <= this.shrinkFactor) {
            int new_capacity;
            if ((data.length/2) < capacity_origin) {
                new_capacity = capacity_origin;
            } else {
                new_capacity = data.length / 2;
            }
            int[] new_list = new int[new_capacity];
            for (int x = 0; x < this.data.length; x++) {
                new_list[x] = this.data[x];
            }
            this.data = new_list;
        }
        nElems--;
        return pop_element;
    }
    public void multiPush(int[] elements) {
        if (elements == null) {
            throw new IllegalArgumentException();
        }
        /*
        raise an exception if there are null elements in elements.
         */
        for (int i = 0;i < elements.length; i++){
            this.push(elements[i]);
        }
    }

    public int[] multiPop(int amount) {
        if (amount<=0){
            throw new IllegalArgumentException();
        }
        /*
        raise an exception if the amount is a negative number.
         */
        int[] popped=new int[amount];
        int count = 0;
        for (int x=amount-1;x>=0;x--){
            popped[count]=this.pop();
            count++;
        }
        return popped;
    }
}
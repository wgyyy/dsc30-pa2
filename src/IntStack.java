/*
 * Name: Gaoying Wang
 * PID: A16131629
 */

import java.util.EmptyStackException;

/**
 * This class creates a data type integer stack
 * @author Gaoying Wang
 * @since  ${2022-01-13}
 */
public class IntStack {

    /* instance variables, feel free to add more if you need */
    private int[] data;
    private int nElems;
    private double loadFactor;
    private double shrinkFactor;
    int[] track;
    int p_nElems;
    int capacity_origin;

    public IntStack(int capacity, double loadF, double shrinkF) {
            if (capacity<5&&0.67<=loadF&&loadF<=1&&0 < shrinkF&&shrinkF <= 0.33) {
                throw new IllegalArgumentException();
            }
            /*
            raise exception when any of capacity, loadF, and shrinkF
            is invalid.
             */
            data = new int[capacity];
            loadFactor = loadF;
            shrinkFactor = shrinkF;
            capacity_origin=capacity;



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
       data=new int[capacity_origin];
       nElems=0;
       p_nElems=nElems;
    }

    public int size() {
        return nElems;
    }

    public int capacity() {
        int capa=data.length;
        return capa;
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
        int[] stored_values=new int[data.length];
        for (int x=0;x<data.length;x++){
            stored_values[x]=data[x];
        }
        data=new int[data.length*2];
        for (int y=0;y<stored_values.length;y++){
            data[y]=stored_values[y];
        }
        }
        data[nElems]=element;
        nElems++;
        p_nElems=nElems;
        track=data;
    }

    public int pop() {
        if (isEmpty()){
            throw new EmptyStackException();
        }
        /*
        raise an exception if the stack is empty
         */
        int pop_element = 0;
        pop_element=data[nElems-1];
        data[nElems-1]=0;
        if (((double)this.size()/this.capacity()) <= this.shrinkFactor) {
            int[] stored_values = new int[data.length];
            for (int x = 0; x < data.length; x++) {
                stored_values[x] = data[x];
            }
            if ((data.length / 2) < capacity()) {
                data = new int[capacity()];
            } else {
                data = new int[data.length / 2];
            }
            for (int x = 0; x < data.length; x++) {
                data[x] = stored_values[x];
            }
        }
        nElems--;
        p_nElems=nElems;
        track=data;
        return pop_element;
    }
    public void multiPush(int[] elements) {
        for (int m=0;m<elements.length;m++){
            if (elements[m]==0){
                throw new IllegalArgumentException();
            }
        }
        /*
        raise an exception if there are null elements in elements.
         */
        for (int y=0;y<data.length;y++){
            if (data[y]==0){
                for (int x=0;x<elements.length;x++){
                    push(elements[x]);
                }
                break;
            }
        }
        p_nElems=nElems;
        track=data;

    }

    public int[] multiPop(int amount) {
        if (amount<0){
            throw new IllegalArgumentException();
        }
        /*
        raise an exception if the amount is a negative number.
         */
        int[] popped=new int[amount];
        for (int x=amount-1;x>=0;x--){
            popped[x]=pop();

        }
        p_nElems=nElems;
        track=data;
        return popped;
    }
}
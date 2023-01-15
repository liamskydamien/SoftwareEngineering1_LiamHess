package uebung10.test.Stack;

import uebung10.test.MyPrettyRectangle;

import java.util.Stack;

public class MyStack<T> extends Stack<T> {
    int counter = 0;
    int max;

    public MyStack(int max){
        this.max = max;
    }

    @Override
    public T push(T item) {
        counter++;
        if(counter >= max){
            counter--;
            throw new IndexOutOfBoundsException();
        }
        return super.push(item);
    }

    @Override
    public synchronized T pop() {
        counter--;
        if(counter < 0){
            counter = 0;
            throw new IndexOutOfBoundsException();
        }
        return super.pop();
    }


}

package uebung10.test.Stack;

import uebung10.test.MyPrettyRectangle;

import java.util.Stack;

public class MyStack<T> extends Stack<T> {
    int max;

    public MyStack(int max){
        super();
        this.max = max;
    }

    @Override
    public T push(T item) {
        if(isFull()){
            throw new IndexOutOfBoundsException();
        }
        return super.push(item);
    }

    @Override
    public synchronized T pop() {
        return super.pop();
    }

    private boolean isFull(){
        return super.size() >= max;
    }

}

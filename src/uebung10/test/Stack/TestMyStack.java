package uebung10.test.Stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestMyStack {
    MyStack<Integer> myStack = null;

    @BeforeEach
    void setup(){
        myStack = new MyStack<>(4);
    }

    @Test
    void testeDurchlauf(){
        assertThrows(Exception.class, () -> myStack.pop());
        for (int i = 1; i < 5; i++) {
            assertEquals(i,myStack.push(i));
        }
        assertThrows(IndexOutOfBoundsException.class, () -> myStack.push(5));
        for (int i = 4; i > 0; i--) {
            assertEquals(i, myStack.pop());
        }
    }
}

package com.codewithmosh;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * @Author：Happer
 * @Package：com.codewithmosh
 * @Project：DataStructures
 * @name：StackMy
 * @Date：2024/9/9 11:53
 * @Filename：StackMy
 */
public class StackMy {
    private int[] stackArray;
    private int top;
    public StackMy(int size) {
        stackArray = new int[size];
    }
    public void push(int value) {
        if (top == stackArray.length) {
            throw new StackOverflowError();
        }
        stackArray[top++] = value;
    }
    public int pop() {
        if (top == 0) {
            throw new EmptyStackException();
        }
        return stackArray[--top];
    }
    public int peek() {
        return stackArray[top - 1];
    }
    public boolean isEmpty() {
        return top == 0;
    }
    @Override
    public String toString(){

        return Arrays.toString(Arrays.copyOfRange(stackArray, 0, top));
    }
}

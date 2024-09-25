package com.codewithmosh;

import java.util.Arrays;

/**
 * @Author：Happer
 * @Package：com.codewithmosh
 * @Project：DataStructures
 * @name：ArrayQueue
 * @Date：2024/9/9 21:48
 * @Filename：ArrayQueue
 */
public class ArrayQueue {
    private int[] queue;
    private int front;
    private int end;
    private int count;
    public ArrayQueue(int size) {
        queue = new int[size];
    }
    public boolean isEmpty() {
        return count == 0;
    }
    public boolean isFull() {
        return count == queue.length;
    }
    public void enqueue(int item) {
        if (isFull()) throw new IndexOutOfBoundsException();
        if(isEmpty()) {
            front = end = 0;
            queue[end] = item;
        }
        else {
            end = Math.floorMod(end + 1, queue.length);
            queue[end] = item;
        }
        count++;
    }
    public int dequeue() {
        if (isEmpty()) throw new IndexOutOfBoundsException();
        int item = queue[front];
        front = Math.floorMod(front+1,queue.length);
        count--;
        return item;
    }
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        if(isEmpty()) return "[]";
        for (int i = 0; i < count; i++) {
            str.append(queue[(front+i)%queue.length] + " ");

        }
        return str.toString();
    }
}

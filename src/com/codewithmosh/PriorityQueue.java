package com.codewithmosh;

import java.util.NoSuchElementException;

/**
 * @Author：Happer
 * @Package：com.codewithmosh
 * @Project：DataStructures
 * @name：PriorityQueue
 * @Date：2024/9/10 17:08
 * @Filename：PriorityQueue
 */
public class PriorityQueue {
    private int[] priorityQueue = new int[5];
    private int count;
    private int front;
    private int rear;
    public Boolean isFull(){
        return count == 5;
    }
    public Boolean isEmpty(){
        return count == 0;
    }
    public void enqueue(int item) {
        if(isFull()){
            throw new IllegalStateException("Queue is full");
        }
        if (isEmpty()){
            front =rear =0;
            priorityQueue[rear] = item;
        }
        else {
            for(int i=0; i<count; i++){
                if(priorityQueue[(front+i)%5]<item){
                    for(int j=0; j<count-i; j++){
                        priorityQueue[Math.floorMod((rear-j+1),5)] = priorityQueue[Math.floorMod((rear-j),5)];
                    }
                    priorityQueue[i] = item;
                    break;
                }
            }
            rear=Math.floorMod((rear+1),5);
        }

        count++;
    }
    public int dequeue() {
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        else {
            int item = priorityQueue[front];
            front = Math.floorMod(front+1,priorityQueue.length);
            count--;
            return item;
        }
    }
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        if(isEmpty()) return "[]";
        for (int i = 0; i < count; i++) {
            str.append(priorityQueue[(front+i)%priorityQueue.length] + " ");

        }
        return str.toString();
    }
}

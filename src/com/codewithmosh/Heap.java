package com.codewithmosh;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * @Author：Happer
 * @Package：com.codewithmosh
 * @Project：DataStructures
 * @name：Heap
 * @Date：2024/9/24 16:30
 * @Filename：Heap
 */
public class Heap {
    private int[] heap = new int[5];
    int count;
    private Boolean full(){
        return count >=heap.length;
    }
    private void enlargeHeap() {
        if (full()){
            var stream = Arrays.stream(heap).toArray();
            heap = new int[heap.length*2];
            System.arraycopy(stream,0,heap,0,heap.length);
        }
    }
    public void insert(int value) {
        enlargeHeap();
        heap[count++] = value;
        bubbleUp();
    }
    public int remove(){
        if (empty())
            throw new IllegalStateException();
        var root = heap[0];
        heap[0] = heap[--count];
        bubbleDown();
        return root;
    }

    private void bubbleDown() {
        var index = 0;
        while(!isValidParent(index)&&index<count){
            var largeChildIndex = largeChildIndex(index);
            swap(largeChildIndex,index);
            index = largeChildIndex;
        }
    }

    private int largeChildIndex(int index) {
        if (!hasLeftChild(index))
            return index;
        if (!hasRightChild(index))
            return leftChildIndex(index);
        var largeChildIndex = leftChild(index)>rightChild(index)?
                leftChildIndex(index):rightChildIndex(index);
        return largeChildIndex;
    }
    private Boolean hasLeftChild(int index) {
        return leftChildIndex(index)<count;
    }
    private Boolean hasRightChild(int index) {
        return rightChildIndex(index)<count;
    }
    private void bubbleUp() {
        int index = count - 1;
        while (index>0&&heap[index] > heap[parent(index)]) {
            swap(index, parent(index));
            index = parent(index);
        }
    }
    private int leftChildIndex(int i){
        return 2*i+1;
    }
    private int rightChildIndex(int i){
        return 2*i+2;
    }
    private int leftChild(int i)
    {
        return heap[leftChildIndex(i)];
    }
    private int rightChild(int i){
        return heap[rightChildIndex(i)];
    }
    private Boolean isValidParent(int i){
        if (!hasLeftChild(i))
            return false;
        var isvalid =(heap[i]>heap[leftChildIndex(i)]) ;
        if (hasRightChild(i))
            isvalid&=heap[i]>heap[leftChildIndex(i)];
        return isvalid;
    }
    public void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
    public int parent(int i) {
        return (i-1)/2;
    }
    private Boolean empty()
    {
        return count<=0;
    }
}

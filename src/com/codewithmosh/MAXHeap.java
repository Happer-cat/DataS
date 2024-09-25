package com.codewithmosh;

/**
 * @Author：Happer
 * @Package：com.codewithmosh
 * @Project：DataStructures
 * @name：MAXHeap
 * @Date：2024/9/24 21:11
 * @Filename：MAXHeap
 */
public class MAXHeap {
    public static void heapify(int[] arr) {
        for (int i =0; i<arr.length; i++) {
            heapify(arr, arr.length-i-1);
        }
    }
    private static void heapify(int[] arr, int index) {
        var largeIndex = index;
        var leftIndex = index*2+1;
        var rightIndex = index*2+2;
        if (leftIndex < arr.length && arr[leftIndex] > arr[largeIndex])
            largeIndex = leftIndex;
        if (rightIndex < arr.length && arr[rightIndex] > arr[largeIndex])
            largeIndex = rightIndex;
        if (largeIndex == index) return;
        swap(arr,index,largeIndex);
        heapify(arr,largeIndex);
    }
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

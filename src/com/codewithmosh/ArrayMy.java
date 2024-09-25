package com.codewithmosh;

/**
 * @Author：Happer
 * @Package：com.codewithmosh
 * @Project：DataStructures
 * @name：ArrayMy
 * @Date：2024/9/4 20:58
 * @Filename：ArrayMy
 */
public class ArrayMy {
    private int[] items;
    private int count;
    public ArrayMy(int size) {
        items = new int[size];
    }
    public void insert(int item) {
        if (count == items.length) {
            int[] newItems = new int[items.length * 2];
            System.arraycopy(items, 0, newItems, 0, count);
            newItems[count++] = item;

            items = newItems;
        }
        else {
            items[count++] = item;
        }

    }
    public void removeAt(int index) {
        if(index < 0 || index >= count){
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        for (int i = index; i < count - 1; i++) {
            items[i] = items[i + 1];
        }


    }
    public void printArray() {
        for (int i = 0; i < count; i++) {
            System.out.print(items[i] + " ");
        }
    }
    public int indexOf(int item) {
        for (int i = 0; i < count; i++) {
            if (items[i] == item) {
                return i;
            }
        }
        System.out.println("Item not found");
        return -1;

    }

}

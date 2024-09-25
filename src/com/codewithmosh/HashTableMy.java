package com.codewithmosh;
import java.util.*;

/**
 * @Author：Happer
 * @Package：com.codewithmosh
 * @Project：DataStructures
 * @name：HashTableMy
 * @Date：2024/9/14 20:57
 * @Filename：HashTableMy
 */
public class HashTableMy {
    private class Entry {
        private int key;
        private String value;

        Entry(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    private LinkedList<Entry>[] hashTable;

    public HashTableMy(int size) {
        hashTable = new LinkedList[size];
    }

    public void put(int key, String value) {
        int index = getIndex(key);
        if (hashTable[index] == null)
            hashTable[index] = new LinkedList<>();

        Entry entry = findEntry(hashTable[index], key);
        if (entry != null)
            entry.value = value;  // 更新已存在的键
        else
            hashTable[index].add(new Entry(key, value));  // 添加新条目
    }

    public String get(int key) {
        Entry entry = findEntryAtKey(key);
        return entry != null ? entry.value : null;
    }

    public void remove(int key) {
        int index = getIndex(key);
        Entry entry = findEntry(hashTable[index], key);
        if (entry != null)
            hashTable[index].remove(entry);
    }

    // 辅助方法，获取哈希桶的索引
    private int getIndex(int key) {
        return key % hashTable.length;
    }

    // 辅助方法，查找条目
    private Entry findEntryAtKey(int key) {
        return findEntry(hashTable[getIndex(key)], key);
    }

    // 在链表中查找条目
    private Entry findEntry(LinkedList<Entry> list, int key) {
        if (list != null) {
            for (Entry entry : list) {
                if (entry.key == key)
                    return entry;
            }
        }
        return null;
    }

}

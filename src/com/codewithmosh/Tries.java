package com.codewithmosh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

/**
 * @Author：Happer
 * @Package：com.codewithmosh
 * @Project：DataStructures
 * @name：Tries
 * @Date：2024/9/25 17:17
 * @Filename：Tries
 */
public class Tries {
    public static int AlphabetSize = 26;
    private class Node {
        char value;
        HashMap<Character, Node> children = new HashMap<>();
        Boolean isEndOfWord = false;
        Node(char value) {
            this.value = value;
        }
        Node() {}

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }
    }
    private Node root = new Node();
    public void insert(String word) {

        var current = root;
        for(char c : word.toCharArray()) {
            if(!current.children.containsKey(c)) {
                current.children.put(c, new Node(c));
            }
            current = current.children.get(c);
        }
        current.isEndOfWord = true;
    }
    public Boolean contains(String word) {

        var current = root;
        for(char c : word.toCharArray()) {
            if(!current.children.containsKey(c)) {
                return false;
            }
            current = current.children.get(c);
        }
        return current.isEndOfWord;
    }
    public Boolean containsPrefix(String word) {

        var current = root;
        for(char c : word.toCharArray()) {
            if(!current.children.containsKey(c)) {
                return false;
            }
            current = current.children.get(c);
        }
        return true;
    }
    public void traverse() {
        traverse(root);
    }
    public void traverse(Node node) {
        System.out.print(node.value + " ");
            if(node.isEndOfWord) {
                return;
            }
            for(var child : node.children.values()) {
                traverse(child);
            }

    }
    public void remove(String word) {
        if(!contains(word)) {
            return;
        }
        remove(root,word,0);
    }
    public void remove(Node node,String word,int index) {
        if (index == word.length()) {
            node.isEndOfWord = false;
            return;
        }
        var child = node.children.get(word.charAt(index));
        var ch = word.charAt(index);

        if (node.children.containsKey(ch)) {
            remove(child,word,index+1);
        }
        if(!child.isEndOfWord&&child.children.isEmpty()){
            node.children.remove(ch);
        }
    }

    public List<String> findWords(String prefix) {
        if(!containsPrefix(prefix)) {
            throw new IllegalArgumentException("prefix does not exist");
        }
        List<String> words = new ArrayList<>();


        var current = root;
        for(char c : prefix.toCharArray()) {
            current = current.children.get(c);
        }
        find(current,prefix.substring(0,prefix.length()-1),words);
        return words;
    }
    private void find(Node node,String prefix,List<String> words) {
        prefix =prefix + node.value;
        if(node.isEndOfWord) {
            words.add(prefix);
        }

            for(var child : node.children.values()) {
                find(child,prefix,words);
            }

    }
}

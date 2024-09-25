package com.codewithmosh;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author：Happer
 * @Package：com.codewithmosh
 * @Project：DataStructures
 * @name：Tree
 * @Date：2024/9/17 17:08
 * @Filename：Tree
 */
    public class Tree {
        private class Node{
            int value;
            Node leftChild;
            Node rightChild;
            public Node(int value) {
                this.value = value;
            }

            @Override
            public String toString() {
                return "Node value=" + value;
            }
        }

    public Node getRoot() {
        return root;
    }

    private Node root;

        public void insert(int value) {
            if(root == null) {
                root = new Node(value);
                return;
            }
            insert(root, value);
        }
        private void insert(Node root, int value) {
            Node current = root;
            while(current != null) {
                if(current.value < value) {
                    if(current.rightChild == null) {
                        current.rightChild = new Node(value);
                        break;
                    }
                    current = current.rightChild;
                }
                else {
                    if(current.leftChild == null) {
                        current.leftChild = new Node(value);
                        break;
                    }
                    current = current.leftChild;
                }

            }

        }
        public Boolean find(int value) {
            return find(root, value);
        }
    public void traversePreOrder(){
            traversePreOrder(root);
    }
        public void traversePreOrder(Node root){
            if(root == null) return;
            System.out.println(root.value);
            traversePreOrder(root.leftChild);
            traversePreOrder(root.rightChild);
        }
        public void traverseInOrder(){
            traverseInOrder(root);
        }
    public void traverseInOrder(Node root){
        if(root == null) return;
        traverseInOrder(root.leftChild);
        System.out.println(root.value);
        traverseInOrder(root.rightChild);
    }
    public void broadTraverse(){
            if(root == null) return;
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);
            while(!queue.isEmpty()){
                Node current = queue.poll();
                System.out.println(current.value);
                if(current.leftChild != null) queue.add(current.leftChild);
                if(current.rightChild != null) queue.add(current.rightChild);
            }

    }
    public int heightTree(){
           return height(root);
    }
    private int height(Node node){
            if(node == null) return -1;
            if (isLeaf(node)) return 0;
            return Math.max(height(node.leftChild), height(node.rightChild)) + 1;
    }
    public int min(){
            return min(root);
    }
    public Boolean equals(Tree tr){
            return equal(this.root,tr.root);
    }
    private Boolean equal(Node root1, Node root2){
        if (root1 ==null && root2 == null) return true;
        if (root1 ==null || root2 == null) return false;


            return equal(root1.leftChild, root2.leftChild)&&equal(root1.rightChild, root2.rightChild)&&root1.value == root2.value;
    }
    private int min(Node node){
            if(node == null) return Integer.MAX_VALUE;
        if (isLeaf(node)) return node.value;
            return Math.min(Math.min(min(node.leftChild), min(node.rightChild)),node.value);
    }

    private static boolean isLeaf(Node node) {
        return node.leftChild == null && node.rightChild == null;
    }

    private Boolean find(Node current,int value) {

        if(current.value < value) {
            return find(current.rightChild,value);
        }
        if(current.value > value) {
            return find(current.leftChild,value);
        }
        if(current == null) {
            return false;
        }
        if(current.value == value) {
            return true;
        }
        return false;
    }
    public Boolean isTwo(){

           return isTwo(root,Integer.MIN_VALUE,Integer.MAX_VALUE);
    }
    private Boolean isTwo(Node node,int min,int max){
            if(node == null) return true;
            if (node.value>min && node.value<max) {
                return isTwo(node.leftChild,min, node.value)&&isTwo(node.rightChild, node.value, max);
            }
            return false;


    }
    public ArrayList<Integer> nodesAtDepth(int depth)
    {
        ArrayList<Integer> list = new ArrayList<>();
        nodesAtDepth(root,depth,list);
        return list;
    }
    private void nodesAtDepth(Node node, int depth,ArrayList<Integer> list){
            if(depth == 0)
            {
                list.add(node.value);
            }
            else {
                if (node.leftChild!=null){
                    nodesAtDepth(node.leftChild, depth-1,list);
                }
                if (node.rightChild!=null){
                    nodesAtDepth(node.rightChild, depth-1,list);
                }
            }
    }
    public void bFS(){
            bFS(root);
    }
    public void bFS (Node root){
            if(root == null) return;
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);
            while(!queue.isEmpty()){
                Node current = queue.poll();
                System.out.println("Current value is " + current.value);
                if(current.leftChild != null) queue.add(current.leftChild);
                if(current.rightChild != null) queue.add(current.rightChild);
            }
    }

}

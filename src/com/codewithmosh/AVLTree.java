package com.codewithmosh;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author：Happer
 * @Package：com.codewithmosh
 * @Project：DataStructures
 * @name：AVLTree
 * @Date：2024/9/22 20:28
 * @Filename：AVLTree
 */
public class AVLTree {
    private class Node{
        int value;
        int height;
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
    private Node root;
    public void insert(int value) {
        root = insert(root, value);
    }
    private Node insert(Node node, int value) {
        if (node == null) {
            return new Node(value);
        }
        if (node.value > value) {
            node.leftChild = insert(node.leftChild, value);
        } else  {
            node.rightChild = insert(node.rightChild, value);
        }
        setHeight(node);

        return balance(node);
    }
    private int height(Node node) {
        if (node == null) return -1;
        return node.height;
    }
    public int calHeightTree(){
        return calHeight(root);
    }
    private int calHeight(Node node){
        if(node == null) return -1;
        if (isLeaf(node)) return 0;
        return Math.max(calHeight(node.leftChild), calHeight(node.rightChild)) + 1;
    }
    private void setHeight(Node node){
       node.height = Math.max(height(node.leftChild), height(node.rightChild)) + 1;
    }
    private boolean isLeaf(Node node){
        return node.leftChild == null && node.rightChild == null;
    }
    private boolean isLeftHeavy(int balanceFactor){
        return balanceFactor>1;
    }
    private boolean isRightHeavy(int balanceFactor){
        return balanceFactor<-1;
    }
    private int balanceFactor(Node node){

        return node == null?0:height(node.leftChild) - height(node.rightChild);
    }
    private Node balance(Node node){
        var balanceFactor = balanceFactor(node);
        if(isLeftHeavy(balanceFactor)){
            if(balanceFactor(node.leftChild)<0){
                node.leftChild = leftRotate(node.leftChild);
            }
            return rightRotate(node);
        }
        if(isRightHeavy(balanceFactor)){
            if(balanceFactor(node.rightChild)>0){
                node.rightChild = rightRotate(node.rightChild);
            }
            return leftRotate(node);
        }
        return node;
    }
    private Node rightRotate(Node node){
        Node temp = node.leftChild;
        node.leftChild = temp.rightChild;
        temp.rightChild = node;
        setHeight(temp);
        setHeight(node);
        return temp;
    }
    private Node leftRotate(Node node){
        Node temp = node.rightChild;
        node.rightChild = temp.leftChild;
        temp.leftChild = node;
        setHeight(temp);
        setHeight(node);
        return temp;
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
    private void nodesAtDepth(Node node, int depth, ArrayList<Integer> list){
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
    public ArrayList<Integer> nodesAtDepth(int depth)
    {
        ArrayList<Integer> list = new ArrayList<>();
        nodesAtDepth(root,depth,list);
        return list;
    }
    public void BFS (){
        for (int i=0;i<=height(root);i++){
           var list = nodesAtDepth(i);
            System.out.println(list);
        }
    }
}

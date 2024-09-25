package com.codewithmosh;

import java.util.*;
import java.util.PriorityQueue;

/**
 * @Author：Happer
 * @Package：com.codewithmosh
 * @Project：DataStructures
 * @name：WeightedGraph
 * @Date：2024/10/1 11:23
 * @Filename：WeightedGraph
 */
public class WeightedGraph {
    private class Node {
        String label;
        Node(String label) {
            this.label = label;
        }
        // 重写equals()方法
        @Override
        public boolean equals(Object obj) {
            // 检查是否与自己进行比较
            if (this == obj) return true;

            // 检查是否是相同类型的对象
            if (obj == null || getClass() != obj.getClass()) return false;

            // 类型转换并比较字段
            Node node = (Node) obj;
            return label != null ? label.equals(node.label) : node.label == null;
        }

        // 重写hashCode()方法
        @Override
        public int hashCode() {
            return label != null ? label.hashCode() : 0;
        }
    }
    private class Edge{
        private Node from;
        private Node to;
        private int weight;
        public Edge(Node from, Node to,int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int hashCode() {
            String labelWeight = to.label;
            return to.label!=null?labelWeight.hashCode():0;
        }

        @Override
        public boolean equals(Object obj) {
            // 检查是否与自己进行比较
            if (this == obj) return true;

            // 检查是否是相同类型的对象
            if (obj == null || getClass() != obj.getClass()) return false;

            // 类型转换并比较字段
            Edge edge = (Edge) obj;
            return this.from.equals(edge.from) && this.to.equals(edge.to);
        }

        @Override
        public String toString() {
            return from.label+" "+to.label+" "+weight;
        }
    }
    private HashMap<String,Node> nodes = new HashMap<>();
    private HashMap<Node, HashSet<Edge>> adjacencyList = new HashMap<>();


    public void addNode(String label) {
        Node newNode = new Node(label);
        if (!nodes.containsKey(label)) {
            nodes.put(label, newNode);
            adjacencyList.put(newNode, new HashSet<>());
        }
    }
    public void addEdge(String from, String to, int weight) {
        if(!nodes.containsKey(from)&&!nodes.containsKey(to)) {
            throw new IllegalArgumentException("not found");
        }
        Edge newEdge = new Edge(nodes.get(from), nodes.get(to), weight);
        adjacencyList.get(nodes.get(from)).remove(newEdge);
        adjacencyList.get(nodes.get(from)).add(newEdge);
        adjacencyList.get(nodes.get(to)).remove(new Edge(nodes.get(to), nodes.get(from), (int) weight));
        adjacencyList.get(nodes.get(to)).add(new Edge(nodes.get(to), nodes.get(from), weight));
    }
    public void print(){
        for (var item : nodes.keySet()) {
            System.out.println(item);
        }
        for (var item : adjacencyList.keySet()) {
            if (!adjacencyList.get(item).isEmpty()) {
                System.out.println(item.label+" "+adjacencyList.get(item));
            }
        }
    }
    public int dijkstra(String from, String to) {
        HashSet<Node> visited = new HashSet<>();
        HashMap<Node,Integer> distances = new HashMap<>();
        HashMap<Node,Node> previous = new HashMap<>();
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(distances::get));
        var current = nodes.get(from);
        previous.put(current,current);
        for (var node:nodes.values())
        {
            distances.put(node, Integer.MAX_VALUE);
        }
        distances.replace(current, 0);
        pq.add(current);
        while (!pq.isEmpty()) {
            current = pq.poll();
            visited.add(current);
            var list =adjacencyList.get(current);
            for (var item : list) {
                if (visited.contains(item.to)) continue;
                Integer newDistance = distances.get(current)+item.weight;
                if (newDistance < distances.get(item.to)) {
                    distances.replace(item.to, newDistance);
                    previous.put(item.to, current);
                }
                pq.add(item.to);
            }
        }
        var toNode = nodes.get(to);
        var previousNode = previous.get(toNode);
        System.out.println(distances.get(nodes.get(to)));
        System.out.println(toNode.label+"");
        while(toNode!=previousNode){
            System.out.println(previousNode.label+"");
            var temp = previousNode;
            previousNode = previous.get(previousNode);
            toNode = temp;

        }
        return distances.get(nodes.get(to));
    }
}

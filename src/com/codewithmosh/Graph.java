package com.codewithmosh;

import java.util.*;

/**
 * @Author：Happer
 * @Package：com.codewithmosh
 * @Project：DataStructures
 * @name：Graph
 * @Date：2024/9/27 19:54
 * @Filename：Graph
 */
public class Graph {
    private class Node {
        String label;
        public Node(String label) {
            this.label = label;
        }
    }
    private HashMap<String, Node> nodes = new HashMap<>();
    private HashMap<Node, List<Node>> adjacency = new HashMap<>();
    public void addNode(String label) {
        Node newNode = new Node(label);
        nodes.put(label, newNode);
        adjacency.put(newNode, new ArrayList<Node>());
    }
    public void addEdge(String label1, String label2) {
        if (nodes.containsKey(label1)&&nodes.containsKey(label2)) {
            var fromNode =  nodes.get(label1);
            var toNode =  nodes.get(label2);
            adjacency.get(fromNode).add(toNode);
            adjacency.get(toNode).add(fromNode);
        }
    }
    public void printNodeEdges(String label){
        if (nodes.containsKey(label)) {
            var node =  nodes.get(label);
            for (var edge : adjacency.get(node)) {
                System.out.println(edge.label);
            }
        }

    }
    public void printAdjacency() {
        for (Node node : adjacency.keySet()) {
            var edges = adjacency.get(node);
            edges.forEach(edge -> System.out.println(node.label+" to "+edge.label));
        }
    }
    public void removeNode(String label) {
        if(containsNode(label)) {
            var targetNode = nodes.get(label);
            var list = new ArrayList<Node>(adjacency.get(targetNode));
            for (var edge : list) {
                removeEdge(targetNode.label, edge.label);
                removeEdge(edge.label, targetNode.label);
            }
            nodes.remove(label);
        }
    }
    public void removeEdge(String label1, String label2) {
        if (containsNode(label1)&&containsNode(label2)) {
            var fromNode =  nodes.get(label1);
            var toNode =  nodes.get(label2);
            if(adjacency.get(fromNode).contains(toNode)&&adjacency.get(toNode).contains(fromNode)) {
                adjacency.get(fromNode).remove(toNode);
            }

        }
    }
    public void depthFirstSearch(String label) {
        if (!nodes.containsKey(label)) return;
        var node =  nodes.get(label);
        HashSet<String> visited = new HashSet<>();
        visited.add(label);
        System.out.println(label);
        depthFirstSearch(node,visited);
    }
    public void depthFirstSearch(Node node, HashSet<String> visited) {
        for (var edge : adjacency.get(node)) {
            if (!visited.contains(edge.label))
            {
                visited.add(edge.label);
                System.out.println(edge.label);
                depthFirstSearch(edge,visited);
            }

        }
    }
    public void depthFirstSearchIterative(String label) {
        if (!nodes.containsKey(label)) return;
        var node =  nodes.get(label);
        Stack<Node> stack = new Stack<>();
        Set<String> visited = new HashSet<>();
        stack.push(node);

        while (!stack.empty()) {
            Node top = stack.pop();
            visited.add(top.label);
            System.out.println(top.label);
            for (var edge : adjacency.get(top)) {
                if (!visited.contains(edge.label)){
                    stack.push(edge);
                }
            }

        }

    }
    public void breadthFirstSearch(String label) {
        if (!nodes.containsKey(label)) return;
        var node =  nodes.get(label);
        Queue<Node> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            var current = queue.poll();
            visited.add(current.label);
            System.out.println(current.label);
            for (var edge : adjacency.get(current)) {
                if (!visited.contains(edge.label)&&!queue.contains(edge)){
                    queue.add(edge);
                }
            }
        }
    }
    private Boolean containsNode(String label) {
        return nodes.containsKey(label);
    }
    public Boolean isCycle(){
        HashSet<String> visited = new HashSet<>();
        Stack<String> visiting = new Stack<>();
        HashSet<String> all = new HashSet<>(nodes.keySet());

        for(var label :all) {
            Stack<String> stack = new Stack<>();
            stack.push(label);
            visiting.add(label);
            while (!stack.empty()) {
                var current = stack.peek();
                Boolean hasUnvisitedNeighbors = false;
                for (var neighbor : adjacency.get(nodes.get(current))) {
                    if (visited.contains(neighbor.label)) {continue;}
                    if (visiting.contains(neighbor.label)){return true;}
                    visiting.add(neighbor.label);
                    stack.push(neighbor.label);
                    hasUnvisitedNeighbors = true;
                    break;
                }
                if(!hasUnvisitedNeighbors) {
                    stack.pop();
                    visiting.remove(current);
                    visited.add(current);
                }


            }



        }


        return false;
    }
    public Boolean isCycleRecursive(){
        HashSet<String> visited = new HashSet<>();
        HashSet<String> visiting = new HashSet<>();
        HashSet<String> all = new HashSet<>(nodes.keySet());
        Boolean isCycle = false;
        // 在循环外创建迭代器
        // 逐个读取下一个元素
        for (String current : all) {
            isCycle |= isCycleRecursive(current,null,visiting, visited);
        }
        return isCycle;
    }
    private Boolean isCycleRecursive(String label,String previous,
                                     HashSet<String> visited,
                                     HashSet<String> visiting) {
        visiting.add(label);
        for (var neighbor : adjacency.get(nodes.get(label))) {
            if (visited.contains(neighbor.label)) {continue;}
            if (neighbor.label.equals(previous)) {continue;}
            if (visiting.contains(neighbor.label)) {return true;}
            if(isCycleRecursive(neighbor.label,label, visited, visiting)){
                return true;
            }
        }
        visiting.remove(label);
        visited.add(label);
        return false;
    }
}

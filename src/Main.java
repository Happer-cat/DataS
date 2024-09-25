import com.codewithmosh.*;

import java.util.*;

/**
 * @Author：Happer
 * @Package：
 * @Project：Default (Template) Project
 * @name：${NAME}
 * @Date：2024/9/4 15:43
 * @Filename：${NAME}
 *///TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();
        var str1 = "a";
        var str2 = "b";
        var str3 = "c";
        var str4 = "d";
        var str5 = "e";
        graph.addNode(str1);
        graph.addNode(str2);
        graph.addNode(str3);
        graph.addNode(str4);
        graph.addNode(str5);
        graph.addEdge(str1,str2);
        graph.addEdge(str2,str3);
        graph.addEdge(str1,str3);
        System.out.println(graph.isCycleRecursive());
    }


}
package com.codewithmosh;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @Author：Happer
 * @Package：com.codewithmosh
 * @Project：DataStructures
 * @name：Expression
 * @Date：2024/9/8 19:41
 * @Filename：Expression
 */
public class Expression {

    private final List<Character> leftBracket =Arrays.asList('(','[','<','{');;
    private final List<Character> rightBracket = Arrays.asList(')',']','>','}');

    public Boolean isBalanced(String expression) {

        var stack = new Stack<Character>();
         for (char ch : expression.toCharArray()) {
             if (leftBracket.contains(ch)) {
                 stack.push(ch);
             }
             if (rightBracket.contains(ch)) {
                 if (stack.isEmpty()) {
                     throw new IllegalArgumentException();
                 }
                if (!BracketMatch(stack.pop(), ch))
                    return false;

             }
         }
         if (stack.isEmpty()) {
             return true;
         }
         return false;
    }
    Boolean BracketMatch(Character left, Character right) {
        return leftBracket.indexOf(left) == rightBracket.indexOf(right);
    }
}

package com.google.ssmm.algorithm.leetcode.tiku;

import java.util.Deque;
import java.util.LinkedList;

public class n227 {
    public static int calculate(String s) {
        Deque<String> deque = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char item = s.charAt(i);
            if (s.charAt(i) == 32) {
                continue;
            }
            if (item == '*' || item == '/') {
                String pop = deque.pollLast();
                i++;
                while (s.charAt(i) == 32) {
                    i++;
                }
                if (item == '*') {
                    deque.add("" + Integer.parseInt(pop) * Integer.parseInt(String.valueOf(s.substring(i))));
                } else {
                    deque.add("" + Integer.parseInt(String.valueOf(pop)) / Integer.parseInt(String.valueOf(s.charAt(i))));
                }
                continue;
            } else if (item == '+' || item == '-') {

            } else {
                if (!deque.isEmpty() && !"+".equals(deque.getLast()) && !"-".equals(deque.getLast())) {
                    deque.add("" + (Integer.parseInt(String.valueOf(deque.pollLast())) * 10 + Integer.parseInt(String.valueOf(item))));
                    continue;
                }
            }
            deque.add(item + "");
        }
        if (deque.isEmpty()) {
            return 0;
        }
        int first = Integer.parseInt(String.valueOf(deque.pollFirst()));
        while (!deque.isEmpty()) {
            String fuhao = deque.pollFirst();
            Integer second = Integer.parseInt(String.valueOf(deque.pollFirst()));
            if ("+".equals(fuhao)) {
                first = first + (int) second;
            } else {
                first = first - (int) second;
            }
        }
        return first;
    }


    public int calculate1(String s) {
        /*
            将 减法、乘法、除法 转换为 加法
            某个数 num, 如果前面的对应的运算符是 -，那么 将 -num 压入栈中
            这样，我们只需在最后将栈的元素全部弹出，完成加法操作，即可得到最终结果

            对于括号，它存在递归性质
            即
            3 * (2 + 4 * 3) + 2
          = 3 * calculate(2 + 4 * 3) + 2
          = 3 * 24 + 2
          即我们可以将括号内的字符串当作一个运算式，再递归调用本函数，最终返回一个数值
        */
        int[] i = new int[1];
        return dfs(s, i);
    }

    private int dfs(String s, int[] i) {
        Deque<Integer> stack = new LinkedList<>();

        //记录某个连续的数，比如 "42"，那么我们首先 num = 4，然后遇到 2 ,num = num * 10 + 2 = 42
        int num = 0;
        char op = '+';
        for (; i[0] < s.length(); i[0]++) {
            char ch = s.charAt(i[0]);

            //遇到左括号，递归运算内部子式
            if (ch == '(') {
                ++i[0];
                num = dfs(s, i);
            }

            if (Character.isDigit(ch)) {
                num = num * 10 + (ch - '0');
            }
            //不是数字，不是空格（运算符 或 '(' 或 ')' ） 或者 到了最后一个字符，那么根据前面记录的 op 操作符 将数字压栈，然后将新的运算符 ch 赋值给 op
            if (!Character.isDigit(ch) && ch != ' ' || i[0] == s.length() - 1) {
                switch (op) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        int pre = stack.pop();
                        stack.push(pre * num);
                        break;
                    case '/':
                        pre = stack.pop();
                        stack.push(pre / num);
                        break;
                }
                num = 0;
                op = ch;
            }
            /*
            遇到右括号，退出循环，然后计算结果， 返回上一层 dfs
            这一步写在最后是因为，当 ch 为 右括号 时，那么我们需要先将前面已经得到的 num 压入栈中，再退出循环
            */
            if (ch == ')') {
                break;
            }
        }
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }


    public static void main(String[] args) {
        int calculate = calculate("1*2-3/4+5*6-7*8+9/10");
        System.out.println(calculate);
    }
}

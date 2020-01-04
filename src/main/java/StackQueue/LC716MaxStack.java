package StackQueue;

import java.util.Stack;

/**
 * Design a max stack that supports push, pop, top, peekMax and popMax.
 *
 * push(x) -- Push element x onto stack.
 * pop() -- Remove the element on top of the stack and return it.
 * top() -- Get the element on the top.
 * peekMax() -- Retrieve the maximum element in the stack.
 * popMax() -- Retrieve the maximum element in the stack, and remove it.
 * If you find more than one maximum elements, only remove the top-most one.
 *
 * Example 1:
 * MaxStack stack = new MaxStack();
 * stack.push(5);
 * stack.push(1);
 * stack.push(5);
 * stack.top(); -> 5
 * stack.popMax(); -> 5
 * stack.top(); -> 1
 * stack.peekMax(); -> 5
 * stack.pop(); -> 1
 * stack.top(); -> 5
 *
 * Note:
 * -1e7 <= x <= 1e7
 * Number of operations won't exceed 10000.
 * The last four operations won't be called when stack is empty.
 */
public class LC716MaxStack {

    private Stack<Integer> sAll; //存所有
    private Stack<Integer> sMax; //存最大

    /** initialize your data structure here. */
    public LC716MaxStack() {
        sAll = new Stack<>();
        sMax = new Stack<>();
    }

    public void push(int x) {
        if (sMax.isEmpty() || sMax.peek() <= x) sMax.push(x);
        sAll.push(x);
    }

    public int pop() {
        if (!sMax.isEmpty() && sMax.peek() == sAll.peek()) sMax.pop();
        return sAll.pop();
    }

    public int top() {
        return sAll.peek();
    }

    public int peekMax() {
        return sMax.peek();
    }

    public int popMax() {
        int ret = sMax.peek();

        // temp存放从ret到sAll顶部这一部分的值
        Stack<Integer> temp = new Stack<>();
        while (sAll.peek() != ret) {
            temp.push(sAll.pop());
        }
        sAll.pop();
        sMax.pop();

        while(!temp.isEmpty()) {
            // 注意这里是this.push 需要<sAll, sMax>整体做push操作
            this.push(temp.pop());
        }

        return ret;
    }

}

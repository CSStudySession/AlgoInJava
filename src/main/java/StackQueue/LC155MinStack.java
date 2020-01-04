package StackQueue;

import java.util.Stack;

/**
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 *
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * getMin() -- Retrieve the minimum element in the stack.
 *
 * Example:
 *
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> Returns -3.
 * minStack.pop();
 * minStack.top();      --> Returns 0.
 * minStack.getMin();   --> Returns -2.
 */
public class LC155MinStack {
    // store all the numbers
    private Stack<Integer> stack = new Stack<>();
    // store the current min values
    private Stack<Integer> minStack = new Stack<>();

    public void push(int x) {
        // store current min value into minStack
        if (minStack.isEmpty() || x <= minStack.peek()) {
            minStack.push(x);
        }

        stack.push(x);
    }

    public void pop() {
        // use equals to compare the value of two object, if equal, pop both of them
        if (stack.peek().equals(minStack.peek())) {
            minStack.pop();
        }

        stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}

package Airbnb;

import common.NestedInteger;
import common.NestedList;
import common.NestedObject;

import java.util.*;

public class NestedIterator implements Iterator<Integer> {

    /*List<Integer> list = new ArrayList<>();
    Iterator<Integer> cur;

    private void helper(List<NestedObject> nestedList) {
        for (NestedObject n : nestedList) {
            if (n.isInteger()) {
                list.add(n.getInteger());
            } else {
                helper(n.getList());
            }
        }
    }

    public NestedIterator(List<NestedObject> nestedList) {
        helper(nestedList);
        cur = list.iterator();
    }

    @Override
    public Integer next() {
        return cur.next();
    }

    @Override
    public boolean hasNext() {
        return cur.hasNext();
    }*/



    Stack<NestedObject> stack = new Stack<>();

    public NestedIterator(List<NestedObject> nestedList) {
        for (int i = nestedList.size()-1; i >= 0; --i) {
            stack.push(nestedList.get(i));
        }
    }

    @Override
    public boolean hasNext() {
        while(!stack.isEmpty()) {
            NestedObject nestedObject = stack.peek();

            if (nestedObject.isInteger()) {
                return true;
            }

            stack.pop();
            for (int i = nestedObject.getList().size()-1; i >= 0; --i) {
                stack.push(nestedObject.getList().get(i));
            }
        }
        return false;
    }

    @Override
    public Integer next() {
        return stack.pop().getInteger();
    }

    @Override
    public void remove() {

    }


    public static void main(String[] args) {
        List<NestedObject> list = new ArrayList<>();
        List<NestedObject> tmp = new ArrayList<>();
        tmp.add(new NestedInteger(3));
        tmp.add(new NestedInteger(4));


        list.add(new NestedInteger(1));
        list.add(new NestedInteger(2));
        list.add(new NestedList(tmp));

        NestedIterator nestedIterator = new NestedIterator(list);
        while(nestedIterator.hasNext()) {
            System.out.println(nestedIterator.next());
        }
    }
}

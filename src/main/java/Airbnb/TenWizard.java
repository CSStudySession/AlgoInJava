package Airbnb;

import java.util.*;

/**
 * There are 10 wizards, 0-9, you are given a list that each entry is a list of wizards known by wizard.
 * Define the cost between wizards and wizard as square of difference of i and j.
 * To findWithDuplicates the min cost between 0 and 9.
 *
 * For example:
 *
 * wizard[0] list: 1, 4, 5 
 *
 * wizard[4] list: 9
 *
 *  wizard 0 to 9 min distance is (0-4)^2+(4-9)^2=41 (wizard[0]->wizard[4]->wizard[9])
 */
public class TenWizard {

    class Node{
        int val;
        int cost;
        List<Node> children;
        Node(int val) {
            this.val = val;
            this.cost = Integer.MAX_VALUE;
            children = new ArrayList<>();
        }

        Node(int val, List<Node> children) {
            this.val = val;
            this.cost = Integer.MAX_VALUE;
            this.children = children;
        }
    }

    public int solve(List<Integer>[] wizards) {

        HashMap<Integer, Node> map = new HashMap<>();
        for (int i = 0; i < wizards.length; ++i) {
            Node node = map.getOrDefault(i, new Node(i));
            for (Integer wizard : wizards[i]){
                Node child = map.getOrDefault(wizard, new Node(wizard));
                map.put(wizard, child);
                node.children.add(child);
            }
            map.put(i, node);
        }

        map.get(0).cost = 0;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(map.get(0));

        while (!queue.isEmpty()) {
            Node root = queue.poll();
            for (Node node : root.children) {
                int prev = node.cost;
                int cur = Math.min(root.cost + (int)Math.pow(root.val-node.val, 2), node.cost);
                if (cur < prev) {
                    node.cost = cur;
                    queue.offer(node);
                }
            }
        }

        return map.get(9).cost;
    }


    public static void main(String[] args) {
        TenWizard tenWizard = new TenWizard();
        ArrayList<Integer>[] wizards = new ArrayList[10];
        for(int i = 0; i < 10; i++) {
            wizards[i] = new ArrayList<>();
        }

        ArrayList<Integer> list0 = wizards[0];
        list0.add(1);
        list0.add(4);
        list0.add(5);
        ArrayList<Integer> list4 = wizards[4];
        list4.add(0);
        list4.add(9);

        System.out.println(tenWizard.solve(wizards));
    }
}

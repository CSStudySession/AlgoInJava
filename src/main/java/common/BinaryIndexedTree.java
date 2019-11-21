package common;

public class BinaryIndexedTree {

    int[] tree;

    public BinaryIndexedTree(int n) {
        tree = new int[n+1];
    }

    private int lowBit(int i) {
        return i & (-i);
    }

    public void update(int i, int num) {
        while (i < tree.length) {
            tree[i] += num;
            i += lowBit(i);
        }
    }

    public int query(int i) {
        int sum = 0;
        while (i > 0) {
            sum += tree[i];
            i -= lowBit(i);
        }
        return sum;
    }


    public static void main(String[] args) {
        int[] test = {1,1,1,1,1,1,1,1,1,1};

        BinaryIndexedTree binaryIndexedTree = new BinaryIndexedTree(test.length);

        for (int i = 0; i < test.length; ++i) {
            binaryIndexedTree.update(i+1, test[i]);
        }

        int sum0_4 = binaryIndexedTree.query(4);
        System.out.println(sum0_4);
        int sum0_2 = binaryIndexedTree.query(2);
        System.out.println(sum0_2);
        int sum2_4 = binaryIndexedTree.query(5) - binaryIndexedTree.query(2);
        System.out.println(sum2_4);

    }
}

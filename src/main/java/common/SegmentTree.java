package common;

public class SegmentTree {

    private int N = 100000;
    private int n;

    private int[] tree = new int[2*N];

    public void build(int[] arr, int len) {
        n = len;

        for (int i = 0; i < n; ++i) {
            tree[n+i] = arr[i];
        }

        System.out.println("constructing");
        for (int i = n - 1; i > 0; --i) {
            System.out.println("i: " + i + ", left: " + (i << 1) + ", right: " + (i << 1 | 1));
            //tree[i] = tree[i << 1] + tree[i << 1 | 1];
            tree[i] = tree[i*2] + tree[i*2+1];
        }
    }

    public void updateTreeNode(int p, int value) {
        System.out.println("updating index: " + p + ", value: " + value);
        p += n;
        tree[p] = value;

        for (int i = p; i > 1; i >>= 1) {
            System.out.println("i: " + (i >> 1) + ", left: " + i + ", right: " + (i^1));
            tree[i >> 1] = tree[i] + tree[i^1];
        }
    }

    public int query(int l, int r) {
        System.out.println("getting query");
        int res = 0;
        for (l += n, r += n; l < r; l >>= 1, r >>= 1) {
            System.out.println("l: " + l + ", l & 1: " + (l&1) + ", next: "  + ((l+1)>>1));
            if ((l & 1) > 0) {
                res += tree[l++];
            }
            System.out.println("r: " + r + ", r & 1: " + (r&1) + ", next: "  + ((r+1)>>1));
            if ((r & 1) > 0) {
                res += tree[--r];
            }
        }

        return res;
    }

    public int[] getTree() {
        return tree;
    }

    static public void main (String[] args)
    {
        int []a = {1, 2, 3, 4, 5, 6, 7, 8,
                9, 10, 11, 12};

        SegmentTree segmentTree = new SegmentTree();
        // n is global
        //n = a.length;

        // build tree
        segmentTree.build(a, a.length);

        // print the sum in range(1,2)
        // index-based
        System.out.println(segmentTree.query(1, 3));

        // modify element at 2nd index
        segmentTree.updateTreeNode(2, 1);

        // print the sum in range(1,2)
        // index-based
        System.out.println(segmentTree.query(1, 3));

        System.out.println(segmentTree.getTree()[1]);
    }
}

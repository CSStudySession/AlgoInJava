package common;

public class UnionFind {

    int count;
    int[] parent;
    int[] rank;



    public UnionFind(int N) {
        count = 0;
        parent = new int[N];
        rank = new int[N];
        for (int i = 0; i < N; ++i) {
            parent[i] = -1;
            rank[i] = 0;
        }
    }


    public boolean isValid(int i) {
        return parent[i] >= 0;
    }

    public void setParent(int i) {
        parent[i] = i;
        ++count;
    }

    public int find(int i) {
        if (parent[i] != i) {
            parent[i] = find(parent[i]);
        }
        return parent[i];
    }


    public void union(int x, int y) {
        int rootx = find(x);
        int rooty = find(y);

        if (rootx != rooty) {
            if (rootx > rooty) {
                parent[rooty] = rootx;
                rank[rooty] += 1;
            } else if (rank[rootx] < rank[rooty]) {
                parent[rootx] = rooty;
            } else {
                parent[rooty] = rootx;
                rank[rootx] += 1;
            }
            --count;
        }
    }

    public int getCount() {
        return count;
    }


    // following 2 methods time complexity: Log(*N)!!!!
    public int findWithPathCompression(int x) {
        int j, fx;
        j = x;

        while(parent[j] != j) {
            j = parent[j];
        }

        // path compression
        // main idea is to link every node on the path to the very top parent node
        while(x != j) {
            fx = parent[x];
            parent[x] = j;
            x = fx;
        }

        return j;
    }

    public void unionWithPathCompression(int x, int y) {
        int rootx = findWithPathCompression(x);
        int rooty = findWithPathCompression(y);

        if (rootx != rooty) {
            parent[rootx] = rooty;
        }
    }
}

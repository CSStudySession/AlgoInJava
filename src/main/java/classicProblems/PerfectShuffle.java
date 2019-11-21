package classicProblems;


/**
 * 有个长度为2n的数组{a1,a2,a3,…,an,b1,b2,b3,…,bn}，希望排序后{a1,b1,a2,b2,….,an,bn}，请考虑有无时间复杂度o(n)，空间复杂度0(1)的解法。
 */
public class PerfectShuffle {

    /**
     * 1st step: given m = num of elements for each category, findWithDuplicates k when 2m = 3^k - 1, so that we can divide and conquer
     * 2nd step: 把数组中的A【m+1,...,n+m]那部分循环右移m位
     * 3rd step: 因对于2m长度的数组来说，刚好有k个圈，且每个圈的头部为3^i, 其中i = 0,1,2,...,k-1,所以对每个圈之星cycleLeader算法，且因数组长度为m，所以需要对2m+1取模
     * 4th step: 对数组的后面部分A[2m+1, ... ,2n]继续地柜使用本算法，这相当于n减少了m
     */

    public void CycleLeader(String[] A, int start, int mod) {
        for(int i = start * 2 % mod;i != start;i = i * 2 % mod) {
            String temp = A[i];
            A[i] = A[start];
            A[start] = temp;
        }
    }

    public void Reverse(String[] A, int start, int end) {
        while(start < end) {
            String temp = A[start];
            A[start++] = A[end];
            A[end--] = temp;
        }
    }

    public void RightRotate(String[] A, int start, int m, int n) {
        Reverse(A, start + m + 1, start + n);
        Reverse(A, start + n + 1, start + n + m);
        Reverse(A, start + m + 1, start + n + m);
    }

    public void PerfectShuffle(String[] A) {
        int len = A.length;
        int n = (len - 1) / 2;
        int start = 0;
        while(n > 1) {
            //第1步：找到2*m = 3^k - 1，使得3^k <= len - 1 < 3^(k + 1)
            int k = 0, m = 1;
            for(;(len - 1) / m >= 3;k++, m = m * 3);
            m = m / 2;

            //第2步：把数组中的A[m + 1,...,n + m]那部分循环右移m位
            RightRotate(A, start, m, n);

            //第3步：对于长度为2*m的数组，刚好有k个圈，每个圈的头部为3^i
            for(int i = 0, t = 1;i < k;i++, t = t * 3)
                CycleLeader(A, t, m * 2 + 1);

            //第4步：对数组后面部分A[2m + 1,...,2n]继续递归上面3步
            start = start + m * 2;
            n = n - m;

        }
        //n == 1时
        String temp = A[1 + start];
        A[1 + start] = A[2 + start];
        A[2 + start] = temp;
        for(int i = 1;i < len;i = i + 2) {
            String a1 = A[i];
            A[i] = A[i + 1];
            A[i + 1] = a1;
        }
    }
}

package common;

public class bitMaskDP {
    private static int _1(int x){
        return 1 << x;
    }
    private static int valid(int x , int y , int st){
        if (x == 0){
            if (y == 2){
                return _1(1) & st;
            }
            else if (y == 8){
                return _1(4) & st;
            }
        }
        /// all the cases
        return -1;
    }
    public static void  main(String args[]){
        int dp[][] = new int[512][10];
        for (int i = 0 ; i < 9 ; ++i){
            dp[_1(i)][i] = 1;
        }
        int ans = 0;
        for (int i = 0 ; i < _1(i) ; ++i){
            for (int j = 0 ; j < 9 ; ++j) if (dp[i][j] > 0){
                ans += dp[i][j];
                for (int k = 0 ; k < 9 ; ++k) {
                    if ((i & _1(k)) == 0 && valid(j , k , i) > 0){
                        dp[(i | _1(k))][k] += dp[i][j];
                    }
                }
            }
        }

    }
}

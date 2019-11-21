package dynamicProgramming;

import java.util.Arrays;

public class CoinChange {

    public int getNum(int[] coins, int amount) {
        int[] ret = new int[amount+1];

        Arrays.fill(ret, Integer.MAX_VALUE);
        ret[0] = 0;

        for (int coin : coins) {
            for (int i = coin; i <= amount; ++i) {
                ret[i] = Math.min(ret[i], ret[i-coin]+1);
            }
        }

        return ret[amount] >= amount + 1 ? -1 : ret[amount];
    }



    public static void main(String[] args) {
        int[] coins = {1,2,5};
        CoinChange coinChange = new CoinChange();

        System.out.println(coinChange.getNum(coins,12));
    }
}

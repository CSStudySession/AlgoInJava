package Memorization;

import java.util.HashMap;
import java.util.Map;

/**
 * In the "100 game," two players take turns adding, to a running total,
 * any integer from 1..10. The player who first causes the running total to reach or exceed 100 wins.
 *
 * What if we change the game so that players cannot re-use integers?
 *
 * For example, two players might take turns drawing from a common pool of numbers of 1..15
 * without replacement until they reach a total >= 100.
 *
 * Given an integer maxChoosableInteger and another integer desiredTotal,
 * determine if the first player to move can force a win, assuming both players play optimally.
 *
 * You can always assume that maxChoosableInteger will not be larger than 20
 * and desiredTotal will not be larger than 300.
 *
 * Example
 *
 * Input:
 * maxChoosableInteger = 10
 * desiredTotal = 11
 *
 * Output:
 * false
 *
 * Explanation:
 * No matter which integer the first player choose, the first player will lose.
 * The first player can choose an integer from 1 up to 10.
 * If the first player choose 1, the second player can only choose integers from 2 up to 10.
 * The second player will win by choosing 10 and get a total = 11, which is >= desiredTotal.
 * Same with other integers chosen by the first player, the second player will always win.
 *
 * 思路：
 * > 对于min-max， 通常是采用top down dynamic programming+memorization的方式， 也就是递归的过程中记录
 * > min-max的基本框架可以如下：
 * 对于当前的所有选项， 做一个选择， 然后看对方是否在剩下的选择里面不能赢，
 * 如果对手不能赢， 那现在这方就选择这个策略，也就是自己赢。
 *
 * 如果当前不能找到一个选择，使得对手不能赢，那么就是无论当前选择什么，对手都可以赢， 那么就是自己输。
 */
public class LC464CanIWin {

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if (desiredTotal <= 0) return true;
        if ((maxChoosableInteger+1)*maxChoosableInteger/2 < desiredTotal) return false;
        //用char数组编码来表示当前所有数字的选取状态 -> char节约空间
        char[] states = new char[maxChoosableInteger];
        for (int i = 0; i < states.length; i++) {
            states[i] = '0';
        }
        Map<String, Boolean> memo = new HashMap<>();
        return memoDfs(desiredTotal, states, memo);

    }

    private boolean memoDfs(int desiredTotal, char[] states, Map<String, Boolean> memo) {
        String curState = new String(states);
        if (memo.containsKey(curState)) return memo.get(curState);

        for (int i = 0; i < states.length; i++) {
            if (states[i] == '0') {
                states[i] = '1';
                if (desiredTotal-(i+1)<=0 || !memoDfs(desiredTotal-(i+1),
                        states, memo)) {
                    memo.put(curState, true);
                    states[i] = '0';
                    return true;
                }
                states[i] = '0';
            }
        }
        memo.put(curState, false);
        return false;
    }
}

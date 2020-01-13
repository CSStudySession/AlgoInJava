package StackQueue;

import java.util.Stack;

/**
 * We are given an array asteroids of integers representing asteroids in a row.
 * For each asteroid, the absolute value represents its size,
 * and the sign represents its direction (positive meaning right, negative meaning left).
 * Each asteroid moves at the same speed.
 * Find out the state of the asteroids after all collisions. If two asteroids meet,
 * the smaller one will explode. If both are the same size, both will explode.
 * Two asteroids moving in the same direction will never meet.
 *
 * Example 1:
 * Input:
 * asteroids = [5, 10, -5]
 * Output: [5, 10]
 * Explanation:
 * The 10 and -5 collide resulting in 10.  The 5 and 10 never collide.
 *
 * Example 2:
 * Input:
 * asteroids = [8, -8]
 * Output: []
 * Explanation:
 * The 8 and -8 collide exploding each other.
 *
 * Example 3:
 * Input:
 * asteroids = [10, 2, -5]
 * Output: [10]
 * Explanation:
 * The 2 and -5 collide resulting in -5.  The 10 and -5 collide resulting in 10.
 *
 * Example 4:
 * Input:
 * asteroids = [-2, -1, 1, 2]
 * Output: [-2, -1, 1, 2]
 * Explanation:
 * The -2 and -1 are moving left, while the 1 and 2 are moving right.
 * Asteroids moving the same direction never meet, so no asteroids will meet each other.
 *
 * Note:
 * The length of asteroids will be at most 10000.
 * Each asteroid will be a non-zero integer in the range [-1000, 1000]..
 */
public class LC735AsteroidCollision {

    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> st = new Stack<>();
        for (int ast : asteroids) {
            if (ast > 0) {
                st.push(ast);
            } else { // ast is negative
                // 栈顶元素为正数且无法抵消掉当前ast的冲击
                while (!st.isEmpty() && st.peek() > 0 && st.peek() < Math.abs(ast)) {
                    st.pop();
                }

                /*
                停止出栈的三种情况:
                1. 栈空了 (当前元素直接入栈)
                2. 栈顶元素是负数 与当前ast方向相同 (当前元素直接入栈)
                3. 栈顶元素为正 绝对值大于等于ast (如果绝对值和ast相等 两者直接抵消 栈顶元素出栈)
                 */
                if (st.isEmpty() || st.peek() < 0) {
                    st.push(ast);
                } else if (ast + st.peek() == 0) {
                    st.pop(); //equal
                }
            }
        }

        int[] res = new int[st.size()];
        // 栈先入后出 所以结果倒着放进数组
        for(int i = res.length - 1; i >= 0; i--){
            res[i] = st.pop();
        }

        return res;
    }

}

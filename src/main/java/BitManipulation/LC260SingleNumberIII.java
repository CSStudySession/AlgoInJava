package BitManipulation;

/**
 * Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice.
 * Find the two elements that appear only once.
 *
 * Example:
 * Input:  [1,2,1,3,2,5]
 * Output: [3,5]
 *
 * Note:
 * The order of the result is not important. So in the above example, [5, 3] is also correct.
 * Your algorithm should run in linear runtime complexity.
 * Could you implement it using only constant space complexity?
 *
 * 思路: 抵消法
 * 假设a,b为最终答案 想办法把所有数分成跟a和b相关的两组数
 * 与a相关的数组A中 所有数亦或起来就是a 同理得到b
 * 问题转化为如何求这个分组因子
 * 1. 先把所有数xor起来 得到一个partitioner: 这个数其实是a xor b的结果
 * 2. 制造分离因子 找出a和b二进制表示法中的不同的其中一位
 *    可以用这个公式: x = x & (~(x-1)) 找到x从右往左第一个为1的位置(在这个位置上a,b不同)
 * 3. 分组 得到x分离因子之后 将所有数分成 &x==0 vs &x!=0两组即可 每组内部进行xor运算 分别得到a,b
 */
public class LC260SingleNumberIII {

    public int[] singleNumber(int[] nums) {
        int[] res = new int[2];
        int partitionNum = 0;
        // 把
        for (int num : nums) {
            partitionNum ^= num;
        }
        // make partitioner: x = x & (~(x-1))
        partitionNum &= ~(partitionNum-1);

        for (int num : nums) {
            if ((num & partitionNum) == 0) {
                res[0] ^= num;
            } else {
                res[1] ^= num;
            }
        }

        return res;
    }

}

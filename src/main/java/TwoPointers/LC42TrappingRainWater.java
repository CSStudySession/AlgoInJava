package TwoPointers;

/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it is able to trap after raining.
 *
 * Example:
 * Input: [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 *
 * 复杂度
 * 时间 O(n) 空间(1)
 * 思路：双指针->相遇问题
 * 从数组两端走起，每次迭代时判断左pointer和右pointer指向的数字哪个大，
 * 如果左pointer小，意味着向左移动右pointer不可能使结果变得更好，因为瓶颈在左pointer，
 * 移动右pointer只会变小，所以这时候我们选择左pointer右移。
 * 反之，则选择右pointer左移。在这个过程中一直维护最大的那个容积
 */
public class LC42TrappingRainWater {

    public int trap(int[] height) {
        if (height == null || height.length < 3) return 0;
        int left = 0;
        int right = height.length - 1;
        int ret = 0;

        while (left < right) {
            if (height[left] < height[right]) {
                int ptr = left + 1;
                while (ptr < right && height[ptr] < height[left]) {
                    ret += height[left] - height[ptr];
                    ptr++;
                }
                left = ptr;
            } else {
                // 右边是瓶颈 朝左移动右指针
                int ptr = right - 1;
                while (ptr > left && height[ptr] < height[right]) {
                    ret += height[right] - height[ptr];
                    ptr--;
                }
                right = ptr;
            }
        }
        return ret;
    }
}

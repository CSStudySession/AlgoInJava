package TwoPointers;

/**
 * Given n non-negative integers a1, a2, ..., an ,
 * where each represents a point at coordinate (i, ai).
 * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
 * Find two lines, which together with x-axis forms a container,
 * such that the container contains the most water.
 * Note: You may not slant the container and n is at least 2.
 *
 * 复杂度
 * 时间 O(n) 空间(1)
 * 思路：双指针->相遇问题
 * 从数组两端走起，每次迭代时判断左pointer和右pointer指向的数字哪个大，如果左pointer小，
 * 意味着向左移动右pointer不可能使结果变得更好，因为瓶颈在左pointer，移动右pointer只会变小，所以这时候我们选择左pointer右移。
 * 反之，则选择右pointer左移。在这个过程中一直维护最大的那个容积
 */
public class LC11ContainerWithMostWater {

    public int maxArea(int[] height) {
        if (height == null || height.length == 0) return 0;
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;
        while (left < right) {
            maxArea = Math.max(maxArea, Math.min(height[left], height[right]) * (right - left));
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }

}

package dynamicProgramming;


/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
 *
 *
 * The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!
 *
 * Example:
 *
 * Input: [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 */
public class TrappingWater {

    public int trap(int[] height) {

        int n = height.length, ret = 0;
        int[] left = new int[n];
        int[] right = new int[n];

        left[0] = height[0];
        right[n-1] = height[n-1];
        for (int i = 1; i < height.length; ++i) {
            left[i] = Math.max(height[i], left[i-1]);
            right[n-i-1] = Math.max(height[n-i-1], right[n-i]);
        }

        for (int i = 0; i < n; ++i) {
            ret += Math.min(left[i], right[i]) - height[i];
        }

        return ret;
    }


    public static void main(String[] args) {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        TrappingWater trappingWater = new TrappingWater();
        System.out.println(trappingWater.trap(height));
    }
}

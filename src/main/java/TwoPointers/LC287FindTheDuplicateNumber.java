package TwoPointers;

/**
 * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive),
 * prove that at least one duplicate number must exist.
 * Assume that there is only one duplicate number, find the duplicate one.
 *
 * Example 1:
 * Input: [1,3,4,2,2]
 * Output: 2
 *
 * Example 2:
 * Input: [3,1,3,4,2]
 * Output: 3
 *
 * Note:
 * You must not modify the array (assume the array is read only).
 * You must use only constant, O(1) extra space.
 * Your runtime complexity should be less than O(n2).
 * There is only one duplicate number in the array, but it could be repeated more than once.
 *
 * 思路：
 * 与链表找环相似
 * 首先为什么一定会有环？
 * 把数组下标想象成链表节点 数组的值就是next指针 由于题设给定所有数组的值都在[1,n]范围内
 * 所以index为0的第一个元素 不会被别的元素指向了 所以等价于n个位置(1+n个元素除去第一个下标为0的位置) 然后1+n个指针 必然有一个[1,n]之间的位置
 * 被指向了两次
 *
 * 既然问题转化成链表找环 就可以用快慢指针去做了
 */
public class LC287FindTheDuplicateNumber {

    public int findDuplicate(int[] nums) {
        int fast = 0;
        int slow = 0;
        // 由于一定有环 所以使用do while结构 快指针步进2步 慢指针步进1步 直至相遇
        do {
            fast = nums[nums[fast]];
            slow = nums[slow];
        } while (fast != slow);

        //  相遇后 快指针回到起始点 然后两指针每次都走一步 相遇时即为重复元素
        slow = 0;
        while (fast != slow) {
            fast = nums[fast];
            slow = nums[slow];
        }

        return fast;
    }

}

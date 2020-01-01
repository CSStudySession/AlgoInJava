package Heap;

import java.util.TreeSet;

/**
 * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value.
 * So the median is the mean of the two middle value.
 *
 * Examples:
 * [2,3,4] , the median is 3
 * [2,3], the median is (2 + 3) / 2 = 2.5
 *
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right.
 * You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 * Your job is to output the median array for each window in the original array.
 *
 * For example,
 * Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
 * Window position                Median
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       1
 *  1 [3  -1  -3] 5  3  6  7       -1
 *  1  3 [-1  -3  5] 3  6  7       -1
 *  1  3  -1 [-3  5  3] 6  7       3
 *  1  3  -1  -3 [5  3  6] 7       5
 *  1  3  -1  -3  5 [3  6  7]      6
 * Therefore, return the median sliding window as [1,-1,-1,3,5,6].
 *
 * Note:
 * You may assume k is always valid, ie: k is always smaller than input array's size for non-empty array.
 */
public class LC480SlidingWindowMedian {

    public double[] medianSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        TreeSet<Item> minHeap = new TreeSet<>();
        TreeSet<Item> maxHeap = new TreeSet<>();
        double[] result = new double[len - k + 1];
        if (k == 0) return result;
        boolean isEven = (k % 2 == 0);
        int half = (k + 1) / 2;

        //  先把前k-1个元素加到堆里面
        for (int i = 0; i < k - 1; i++) {
            addItem(minHeap, maxHeap, half, new Item(i, nums[i]));
        }

        int idx = 0;
        // 固定窗口型问题 每次加一个再减掉一个
        for (int j = k - 1; j < nums.length; j++) {
            addItem(minHeap, maxHeap, half, new Item(j, nums[j]));

            // 注意下面 是first不是pollFirst!!
            if (!isEven) {
                result[idx] = minHeap.first().value;
            } else {
                result[idx] = (maxHeap.last().value + minHeap.first().value) / (double)2;
            }

            // 注意维护下标idx 当创建完Item对象用于删除操作之后 要idx++
            removeItem(minHeap, maxHeap, new Item(idx, nums[idx]));
            idx++;
        }

        return result;
    }

    // 由于对象是由下标+元素值双重定位 所以是唯一的
    private void removeItem(TreeSet<Item> minHeap, TreeSet<Item> maxHeap, Item item) {
        if (minHeap.contains(item)) {
            minHeap.remove(item);
        } else {
            maxHeap.remove(item);
        }
    }

    // 先往小根堆里加元素 当小根堆里面元素超过size==(k+1)/2时 再往大根堆里加入
    private void addItem(TreeSet<Item> minHeap, TreeSet<Item> maxHeap, int size, Item item) {
        if (minHeap.size() < size) {
            minHeap.add(item);
        } else {
            maxHeap.add(item);
        }

        /*
        小根堆存放的元素都是大数 大根堆里存的都是小数 大数集合的最小元素不能小于小数集合的最大元素
        如果出现元素错位的情况 需要交换错位的元素
         */
        if (minHeap.size() == size && (maxHeap.size() > 0
                && minHeap.first().value < maxHeap.last().value)) {
            Item minItem = minHeap.pollFirst();
            Item maxItem = maxHeap.pollLast();
            minHeap.add(maxItem);
            maxHeap.add(minItem);
        }
    }

    // 自定义Item类 方便TreeSet用于比较
    public class Item implements Comparable<Item> {
        int idx;
        int value;

        public Item(int idx, int value) {
            this.idx = idx;
            this.value = value;
        }

        public int compareTo(Item other) {
            return this.value == other.value ? this.idx - other.idx : this.value - other.value;
        }

    }

    public static void main(String[] args) {
        LC480SlidingWindowMedian inst = new LC480SlidingWindowMedian();
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;
        inst.medianSlidingWindow(nums, k);
        System.out.println(".");
    }

}

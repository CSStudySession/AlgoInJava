package Design;

import java.util.*;

/**
 * Design a data structure that supports all following operations in average O(1) time.
 *
 * Note: Duplicate elements are allowed.
 * insert(val): Inserts an item val to the collection.
 * remove(val): Removes an item val from the collection if present.
 * getRandom: Returns a random element from current collection of elements.
 * The probability of each element being returned is linearly related to
 * the number of same value the collection contains.
 *
 * Example:
 * // Init an empty collection.
 * RandomizedCollection collection = new RandomizedCollection();
 *
 * // Inserts 1 to the collection. Returns true as the collection did not contain 1.
 * collection.insert(1);
 *
 * // Inserts another 1 to the collection. Returns false as the collection contained 1. Collection now contains [1,1].
 * collection.insert(1);
 *
 * // Inserts 2 to the collection, returns true. Collection now contains [1,1,2].
 * collection.insert(2);
 *
 * // getRandom should return 1 with the probability 2/3, and returns 2 with the probability 1/3.
 * collection.getRandom();
 *
 * // Removes 1 from the collection, returns true. Collection now contains [1,2].
 * collection.remove(1);
 *
 * // getRandom should return 1 and 2 both equally likely.
 * collection.getRandom();
 */
public class LC381RandomizedCollection {

    Map<Integer, List<Integer>> valToIdx;
    List<Item> items;

    /** Initialize your data structure here. */
    public LC381RandomizedCollection() {
        valToIdx = new HashMap<>();
        items = new ArrayList<>();
    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        boolean containsVal = valToIdx.containsKey(val) ? true : false;

        valToIdx.putIfAbsent(val, new ArrayList<>());
        int idx = valToIdx.get(val).size();
        Item cur = new Item(val, idx);
        valToIdx.get(val).add(items.size());

        items.add(cur);

        return containsVal;
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if (!valToIdx.containsKey(val)) return false;

        /*
        1. valIndx: 待删元素在数组最后一次出现的下标
        2. 因为要删掉vaIndx指向的元素 所以把数组最后一个元素 直接覆盖到valIndx上即可
        3. 数组删掉最后一个元素 此时该元素已经挪动到坐标valIndx上
        4. 修改curLastItem下标的指向地址为valIndx
        5. 删除val元素下标集合的最后一个元素
        6. 如果val元素下标集合空了 直接删除整条记录
         */
        int valIndx = valToIdx.get(val).get(valToIdx.get(val).size() - 1);
        Item curLastItem = items.get(items.size() - 1);
        items.set(valIndx, curLastItem);
        items.remove(items.size() - 1);

        valToIdx.get(curLastItem.val).set(curLastItem.idx, valIndx);
        valToIdx.get(val).remove(valToIdx.get(val).size() - 1);
        if (valToIdx.get(val).size() == 0) {
            valToIdx.remove(val);
        }
        return true;
    }

    /** Get a random element from the collection. */
    public int getRandom() {
        Random rand = new Random();
        int idx = rand.nextInt(items.size());
        return items.get(idx).val;
    }

    public class Item {
        int val;
        /*
         在相同val的map entry中对应的下标
         比如: 3: {3, 5}
         item{3,0}
         3在数组位置的{3,5} 当前item的值是3 是3对应列表的第一个元素
         */
        int idx;
        public Item() {}

        public Item(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }
    }
}

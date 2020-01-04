package Design;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * Design and implement a data structure for Least Frequently Used (LFU) cache.
 * It should support the following operations: get and put.
 *
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present.
 * When the cache reaches its capacity, it should invalidate the least frequently used item before inserting a new item.
 * For the purpose of this problem, when there is a tie (i.e., two or more keys that have the same frequency),
 * the least recently used key would be evicted.
 *
 * Note that the number of times an item is used is the number of calls
 * to the get and put functions for that item since it was inserted.
 * This number is set to zero when the item is removed.
 *
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 *
 * Example:
 * LFUCache cache = new LFUCache( 2 / capacity / );
 *  cache.put(1,1);
 *  cache.put(2,2);
 *  cache.get(1);       // returns 1
 *  cache.put(3,3);    // evicts key 2
 *  cache.get(2);       // returns -1 (not found)
 *  cache.get(3);       // returns 3.
 *  cache.put(4,4);    // evicts key 1.
 *  cache.get(1);       // returns -1 (not found)
 *  cache.get(3);       // returns 3
 *  cache.get(4);       // returns 4
 */
public class LC460LFUCache {

    private Map<Integer, Integer> keyToValue;
    private Map<Integer, Integer> keyToFreq;
    private Map<Integer, LinkedHashSet<Integer>> freqToKeys;
    private int cap;
    private int minFreq;

    public LC460LFUCache(int capacity) {
        keyToValue = new HashMap<>();
        keyToFreq = new HashMap<>();
        freqToKeys = new HashMap<>();
        cap = capacity;
        minFreq = 0;
    }

    public int get(int key) {
        if (!keyToValue.containsKey(key)) {
            return -1;
        }

        updateFreqForKey(key);
        return keyToValue.get(key);
    }

    public void put(int key, int value) {
        if (cap <= 0) {
            return;
        }

        if (keyToValue.containsKey(key)) {
            keyToValue.put(key, value);
            updateFreqForKey(key);
            return;
        }

        // 每次添加新key之前 检查是否满了
        if (keyToValue.size() >= cap) {
            evict();
        }

        keyToValue.put(key, value);
        keyToFreq.put(key, 1);
        addKeyToFreqSet(1, key);
        minFreq = 1;
    }

    private void updateFreqForKey(int key) {
        int freq = keyToFreq.get(key);
        keyToFreq.put(key, freq + 1);
        freqToKeys.get(freq).remove(key);

        if (freq == minFreq && freqToKeys.get(freq).size() == 0) {
            freqToKeys.remove(freq);
            minFreq++;
        }

        addKeyToFreqSet(freq + 1, key);
    }

    private void addKeyToFreqSet(int freq, int key) {
        if (!freqToKeys.containsKey(freq)) {
            freqToKeys.put(freq, new LinkedHashSet<>());
        }

        freqToKeys.get(freq).add(key);
    }

    private void evict() {
        int key = freqToKeys.get(minFreq).iterator().next();
        freqToKeys.get(minFreq).remove(key);
        if (freqToKeys.get(minFreq).size() == 0) {
            freqToKeys.remove(minFreq);
        }

        keyToValue.remove(key);
        keyToFreq.remove(key);
    }

}

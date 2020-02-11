package TwoPointers;

/**
 * Given two strings s and t, determine if they are both one edit distance apart.
 *
 * Note:
 * There are 3 possibilities to satisfy one edit distance apart:
 *
 * Insert a character into s to get t
 * Delete a character from s to get t
 * Replace a character of s to get t
 *
 * Example 1:
 * Input: s = "ab", t = "acb"
 * Output: true
 * Explanation: We can insert 'c' into s to get t.
 *
 * Example 2:
 * Input: s = "cab", t = "ad"
 * Output: false
 * Explanation: We cannot get t from s by only one step.
 *
 * Example 3:
 * Input: s = "1203", t = "1213"
 * Output: true
 * Explanation: We can replace '0' with '1' to get t.
 *
 * followup:
 * 两个string都是online的，也就是只能通过API一个一个读，不知道长度，不能往回读，不能存在cache里
 */
public class LC161OneEditDistance {
    /*
    取出两个string的长度 如果相差大于1 直接返回false
    遍历短的那个string 如果当前char不等于对应位置的char:
    1. 如果长度相等 同时跳过
    2. 如果长度不等 长string跳过
     */
    public boolean isOneEditDistance(String s, String t) {
        int lenS = s.length();
        int lenT = t.length();
        if (Math.abs(lenS - lenT) > 1) return false;

        if (lenS > lenT) return isOneEditDistance(t, s);

        for (int i = 0; i < lenS; i++) {
            if (s.charAt(i) != t.charAt(i)) {
                if (lenS == lenT) {
                    return s.substring(i + 1).equals(t.substring(i + 1));
                }

                return s.substring(i).equals(t.substring(i + 1));
            }
        }
        // corner case：两个string都是空串""
        return lenS != lenT;
    }

    // followup question. commented code out because here using a Stream object without defining
    /*
    public boolean isOneEditDistanceOnline(Stream s, Stream t) {
        char sBuffer[] = new char[2], tBuffer[] = new char[2];
        sBuffer[0] = s.next();
        tBuffer[0] = t.next();
        sBuffer[1] = s.next();
        tBuffer[1] = t.next();

        while (sBuffer[0] != '\n' && tBuffer[0] != '\n') {
            if (sBuffer[0] != tBuffer[0]) {
                break;
            }
            // 相等则接着读
            sBuffer[0] = sBuffer[1];
            tBuffer[0] = tBuffer[1];
            sBuffer[1] = s.next();
            tBuffer[1] = t.next();
        }

        //  两个空串""
        if (sBuffer[0] == '\n' && tBuffer[0] == '\n') {
            return false;
        }

        if (sBuffer[0] == '\n' && tBuffer[1] == '\n' ||
                sBuffer[1] == '\n' && tBuffer[0] == '\n' ||
                sBuffer[1] == '\n' && tBuffer[1] == '\n') {
            return true;
        }

        boolean sRemove = true, tRemove = true, modify = true;
        int cnt = 0;
        while (sBuffer[0] != '\n' && tBuffer[0] != '\n') {
            if (sBuffer[0] != tBuffer[1]) {
                tRemove = false;
            }

            if (sBuffer[1] != tBuffer[0]) {
                sRemove = false;
            }

            if (sBuffer[1] != tBuffer[1]) {
                modify = false;
            }

            if (cnt == 1) return false;
            cnt++;

            if (!tRemove && !sRemove && !modify) {
                return false;
            }

            sBuffer[0] = sBuffer[1];
            tBuffer[0] = tBuffer[1];
            sBuffer[1] = s.next();
            tBuffer[1] = t.next();
        }

        if (tRemove && sBuffer[0] == '\n' && tBuffer[0] != '\n' && tBuffer[1] == '\n') return true;
        if (sRemove && tBuffer[0] == '\n' && sBuffer[0] != '\n' && sBuffer[1] == '\n') return true;
        if (modify && tBuffer[0] == '\n' && sBuffer[0] == '\n') return true;

        return false;
    }
     */
}


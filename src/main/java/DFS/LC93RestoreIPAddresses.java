package DFS;

import java.util.ArrayList;

/**
 * Given a string containing only digits,
 * restore it by returning all possible valid IP address combinations.
 *
 * Example:
 * Input: "25525511135"
 * Output: ["255.255.11.135", "255.255.111.35"]
 */
public class LC93RestoreIPAddresses {

    public ArrayList<String> restoreIpAddresses(String s) {
        ArrayList<String> result = new ArrayList<>();
        ArrayList<String> list = new ArrayList<>();

        if (s.length() < 4 || s.length() > 12)
            return result;

        helper(result, list, s , 0);
        return result;
    }

    public void helper(ArrayList<String> result, ArrayList<String> list, String s, int start) {
        if (list.size() == 4) {
            // 必须所有字符都用上
            if (start != s.length()) return;

            StringBuffer sb = new StringBuffer();
            for (String tmp: list) {
                sb.append(tmp);
                sb.append(".");
            }
            sb.deleteCharAt(sb.length()-1);
            result.add(sb.toString());
            return;
        }

        // i<start+3 => 一段ip最多是3位 当i>start+3时 就直接结束改循环
        for (int i = start; i < s.length() && i < start + 3; i++) {
            String tmp = s.substring(start, i + 1);
            if (isValid(tmp)) {
                list.add(tmp);
                helper(result, list, s, i+1);
                list.remove(list.size() - 1);
            }
        }
    }

    private boolean isValid(String s) {
        // to eliminate cases like "00", "10"
        if (s.charAt(0) == '0') {
            return s.equals("0");
        }
        int digit = Integer.valueOf(s);
        return (digit >= 0 && digit <= 255);
    }
}

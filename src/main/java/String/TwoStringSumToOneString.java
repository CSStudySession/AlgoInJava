package String;

import java.util.Map;

/**
 * 给一个 Map，里面是 Character 到 Integer 的关系。给三个字符串，求问前两个字符串通过 map 换算出来的数字之和，是否等于第三个字符串通过 map 换算出来的数字
 *
 * 另一种问法：
 * 给你 str1, str2, str3 和一个map
 * 三个string都是字母组成的，map 是字母到数字的映射（不一定是 one to one mapping，不一定string中的字母都有映射，需要写代码validate）
 * 需要验证转换成数字后的 str1 + str2 = str3。
 *
 */
public class TwoStringSumToOneString {

    public boolean isSumUpToOneString(String str1, String str2, String str3, Map<Character, Integer> dict) {
        if (str1 == null || str2 == null || str3 == null ||
            str1.length() == 0 || str2.length() == 0 || str3.length() == 0) return false;
        try {
            int num1 = getNumFromString(str1, dict);
            int num2 = getNumFromString(str2, dict);
            int num3 = getNumFromString(str3, dict);
            return num3 == num1 + num2;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }

    private int getNumFromString(String str, Map<Character, Integer> dict) throws IllegalArgumentException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (!dict.containsKey(str.charAt(i))) {
                throw new IllegalArgumentException("invalid input string!");
            }

            sb.append(dict.get(str.charAt(i)));
        }
        return Integer.valueOf(sb.toString());
    }

    public static void main(String[] args) {

    }
}

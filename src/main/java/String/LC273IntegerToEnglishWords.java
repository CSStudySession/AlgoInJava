package String;

/**
 * Convert a non-negative integer to its english words representation.
 * Given input is guaranteed to be less than 2^31 - 1.
 *
 * Example 1:
 * Input: 123
 * Output: "One Hundred Twenty Three"
 *
 * Example 2:
 * Input: 12345
 * Output: "Twelve Thousand Three Hundred Forty Five"
 *
 * Example 3:
 * Input: 1234567
 * Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 *
 * Example 4:
 * Input: 1234567891
 * Output: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
 *
 * 思路：从低位到高位 三个一组去读
 * 题目中限定了输入数字范围为[0, 2^31 - 1]之间，最高只能到billion位 3个一组也只需处理四组即可
 */
public class LC273IntegerToEnglishWords {

    // 数组下标与单词对应的实际数字对应 例如:One->下标1 Twenty->下标2
    private final String[] LESS_THAN_20 = {"", "One", "Two", "Three", "Four", "Five", "Six",
            "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen",
            "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};

    private final String[] TENS = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty",
            "Seventy", "Eighty", "Ninety"};

    private final String[] THOUSANDS = {"", "Thousand", "Million", "Billion"};

    public String numberToWords(int num) {
        if (num == 0) return "Zero";

        int i = 0;
        StringBuilder words = new StringBuilder();

        while (num > 0) {
            // 每三位读一次 注意word之间加空格->每次append一个" "
            if (num % 1000 != 0) {
                words.insert(0,helper(num % 1000) + THOUSANDS[i] + " ");
            }
            // last three bits are already be read, so throw them away
            num /= 1000;
            // move to next level
            i++;
        }

        return words.toString().trim();
    }

    private String helper(int num) {
        if (num == 0) {
            return "";
        } else if (num < 20) {
            return LESS_THAN_20[num] + " ";
        } else if  (num < 100) {
            return TENS[num / 10] + " "  + helper(num % 10);
        } else {
            return LESS_THAN_20[num / 100] + " Hundred " + helper(num % 100);
        }
    }

}

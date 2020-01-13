package String;

/**
 * Given two non-negative integers num1 and num2 represented as strings,
 * return the product of num1 and num2, also represented as a string.
 *
 * Example 1:
 * Input: num1 = "2", num2 = "3"
 * Output: "6"
 *
 * Example 2:
 * Input: num1 = "123", num2 = "456"
 * Output: "56088"
 *
 * Note:
 * The length of both num1 and num2 is < 110.
 * Both num1 and num2 contain only digits 0-9.
 * Both num1 and num2 do not contain any leading zero, except the number 0 itself.
 * You must not use any built-in BigInteger library or convert the inputs to integer directly.
 */
public class LC43MultiplyStrings {

    public String multiply(String num1, String num2) {
        int len1 = num1.length();
        int len2 = num2.length();
        // num1*num2最多可能有len1+len2位：9*9=81
        int[] product = new int[len1 + len2];

        // 模拟乘法
        for (int i = len2 - 1; i >= 0; i--) {
            for (int j = len1 - 1; j >= 0; j--) {
                int mul = (num1.charAt(j) - '0') * (num2.charAt(i) - '0');
                int curIndex = i + j + 1;
                int nextIndex = i + j;
                int sum = mul + product[curIndex];

                product[nextIndex] += sum / 10;
                product[curIndex] = sum % 10;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int digit : product) {
            if (!(sb.length() == 0 && digit == 0)) {
                sb.append(digit);
            }
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }

}

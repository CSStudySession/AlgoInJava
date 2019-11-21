package String;

/**
 * Validate if a given string can be interpreted as a decimal number.
 *
 * Some examples:
 * "0" => true
 * " 0.1 " => true
 * "abc" => false
 * "1 a" => false
 * "2e10" => true
 * " -90e3   " => true
 * " 1e" => false
 * "e3" => false
 * " 6e-1" => true
 * " 99e2.5 " => false
 * "53.5e93" => true
 * " --6 " => false
 * "-+3" => false
 * "95a54e53" => false
 *
 * Note: It is intended for the problem statement to be ambiguous.
 * You should gather all requirements up front before implementing one.
 * However, here is a list of characters that can be in a valid decimal number:
 *
 * Numbers 0-9
 * Exponent - "e"
 * Positive/negative sign - "+"/"-"
 * Decimal point - "."
 * Of course, the context of these characters also matters in the input.
 *
 * 思路：有限状态机
 * 一些变种：输入仅有负号小数点和数字, 输入不会有科学计数法
 */
public class LC65ValidNumber {

    public enum input {
        INVALID, // 0
        NUMBER,  // 1
        SIGN,    // 2
        EXP,     // 3
        DOT      // 4
    }
    public boolean isNumber(String s) {
        if (s == null || s.length() == 0) return false;

        int[][] states = {
                {-1, 1,  2, -1,  5},   // init state 0
                {-1, 1, -1,  3,  4},   // number state 1, stable state
                {-1, 1, -1, -1,  4},   // sign state 2
                {-1, 1,  2, -1, -1},   // exp state 3
                {-1, 1, -1,  3, -1},    // dot state 4，maybe stable state
                {-1, 1, -1, -1, -1}   // .xx state 5
        };

        boolean hasDot = false;
        boolean hasExp = false;
        boolean hasSign = false;
        boolean hasNum = false;
        int state = 0;
        char[] str = s.trim().toCharArray();
        for (int i = 0 ; i < str.length; i++) {
            switch(str[i]) {
                case '-':
                {
                    if (hasSign) return false;
                    hasSign = true;
                    state = states[state][input.SIGN.ordinal()];
                }
                break;
                case '+':
                {
                    if (hasSign) return false;
                    hasSign = true;
                    state = states[state][input.SIGN.ordinal()];
                }
                break;
                case 'e':
                {
                    if (hasExp) return false;
                    hasExp = true;
                    if (hasSign) hasSign = false;
                    state = states[state][input.EXP.ordinal()];
                }
                break;
                case 'E':
                {
                    if (hasExp) return false;
                    hasExp = true;
                    if (hasSign) hasSign = false;
                    state = states[state][input.EXP.ordinal()];
                }
                break;
                case '.':
                {
                    if (hasDot) return false;
                    if (hasExp) return false;
                    hasDot = true;
                    state = states[state][input.DOT.ordinal()];
                }
                break;
                default:
                {
                    if (str[i] >= '0' && str[i] <= '9') {
                        hasNum = true;
                        state = states[state][input.NUMBER.ordinal()];
                    }
                    else return false;
                }
            }
            if (state == -1) return false;
        }
        //System.out.println("final state is: " + state);
        if (state == 1 || (state == 4 && hasNum)) return true;
        else return false;
    }

    public static void main(String[] args) {
        LC65ValidNumber inst = new LC65ValidNumber();
        String s = ".1e2";
        boolean ret = inst.isNumber(s);
        System.out.println(ret);
    }

}

package String;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array of words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.
 *
 * You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' '
 * when necessary so that each line has exactly maxWidth characters.
 *
 * Extra spaces between words should be distributed as evenly as possible.
 * If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.
 *
 * For the last line of text, it should be left justified and no extra space is inserted between words.
 *
 * Note:
 * A word is defined as a character sequence consisting of non-space characters only.
 * Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
 * The input array words contains at least one word.
 *
 * Example 1:
 * Input:
 * words = ["This", "is", "an", "example", "of", "text", "justification."]
 * maxWidth = 16
 * Output:
 * [
 *    "This    is    an",
 *    "example  of text",
 *    "justification.  "
 * ]
 *
 * Example 2:
 * Input:
 * words = ["What","must","be","acknowledgment","shall","be"]
 * maxWidth = 16
 * Output:
 * [
 *   "What   must   be",
 *   "acknowledgment  ",
 *   "shall be        "
 * ]
 * Explanation: Note that the last line is "shall be    " instead of "shall     be",
 *              because the last line must be left-justified instead of fully-justified.
 *              Note that the second line is also left-justified because it contains only one word.
 *
 * Example 3:
 * Input:
 * words = ["Science","is","what","we","understand","well","enough","to","explain",
 *          "to","a","computer.","Art","is","everything","else","we","do"]
 * maxWidth = 20
 * Output:
 * [
 *   "Science  is  what we",
 *   "understand      well",
 *   "enough to explain to",
 *   "a  computer.  Art is",
 *   "everything  else  we",
 *   "do                  "
 * ]
 *
 * 思路:
 * start with left being the first word.
 * findRight: Then we greedily try to go as far right as possible until we fill our current line.
 * Then we justify one line at a time.
 * justify: In all cases we pad the right side with spaces until we reach max width for the line;
 *
 * If it's one word then it is easy, the result is just that word.
 * If it's the last line then the result is all words separated by a single space.
 * Otherwise we calculate the size of each space evenly and if there is a remainder we distribute an extra space until it is gone.
 *
 * 变种: 条件不变 只需要返回最后输出多少行?
 * 还是利用findRight method每次找到一行的最右边的单词指针 然后另起一行重新计算 直到left == words.length
 */
public class LC68TextJustification {

    public List<String> fullJustify(String[] words, int maxWidth) {
        int left = 0;
        List<String> result = new ArrayList<>();

        while (left < words.length) {
            int right = findRight(left, words, maxWidth);
            result.add(justify(left, right, words, maxWidth));
            left = right + 1;
        }

        return result;
    }

    /*              0
       sum = 7
     * words = ["Science","is","what","we","understand","well","enough","to","explain",
     *          "to","a","computer.","Art","is","everything","else","we","do"]
     * maxWidth = 20
     * Output:
     * [
     *   "Science  is  what we",
     *   "understand      well",
     *   "enough to explain to",
     *   "a  computer.  Art is",
     *   "everything  else  we",
     *   "do                  "
     * ]
     */

    private int findRight(int left, String[] words, int maxWidth) {
        int right = left;
        int sum = words[right].length();
        right++;

        // +1 for space between words
        while (right < words.length && (sum + 1 + words[right].length()) <= maxWidth) {
            sum += 1 + words[right].length();
            right++;
        }

        // right is not valid when loop exited, so -1
        return right - 1;
    }

    private String justify(int left, int right, String[] words, int maxWidth) {
        if (right == left) return padResult(words[left], maxWidth);

        boolean isLastLine = right == words.length - 1;
        int numSpaces = right - left;
        int totalSpace = maxWidth - wordsLength(left, right, words);

        String space = isLastLine ? " " : blank(totalSpace / numSpaces);
        int remainder = isLastLine ? 0 : totalSpace % numSpaces;

        StringBuilder result = new StringBuilder();
        for (int i = left; i <= right; i++)
            result.append(words[i])
                    .append(space)
                    .append(remainder-- > 0 ? " " : "");

        return padResult(result.toString().trim(), maxWidth);
    }

    private int wordsLength(int left, int right, String[] words) {
        int wordsLength = 0;

        for (int i = left; i <= right; i++) {
            wordsLength += words[i].length();
        }

        return wordsLength;
    }

    private String padResult(String result, int maxWidth) {
        return result + blank(maxWidth - result.length());
    }

    /*
    new String(new char[length]).replace('\0', ' ') means to create a string of a new character array of the input length
    and replace('\0', '0') replaces each of the characters with '0'
    (as the default String initialization character is 0 (or '\u0000' or \0)) to empty space ' '
     */
    private String blank(int length) {
        return new String(new char[length]).replace('\0', ' ');
    }

}

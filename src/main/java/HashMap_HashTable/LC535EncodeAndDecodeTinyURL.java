package HashMap_HashTable;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * TinyURL is a URL shortening service where you enter a URL such as
 * https://leetcode.com/problems/design-tinyurl and it returns a short URL such as http://tinyurl.com/4e9iAk.
 *
 * Design the encode and decode methods for the TinyURL service.
 * There is no restriction on how your encode/decode algorithm should work.
 * You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.
 *
 * 思路：
 * assign a unique integer id to each long URL representation and convert this integer id to its base-62 representation.
 *
 * A URL character can be one of the following：
 * Lower case alphabet [a to z], total 26 characters
 * Upper case alphabet [A to Z], total 26 characters
 * Digit [0 to 9], total 10 characters
 * So there are total 26 + 26 + 10 = 62 possible characters and hence the task is to convert a decimal number to base 62 number.
 *
 * To get the original long URL, we need to get URL stored in the Map.
 *
 * Time complexity: O(log n) for encode() method and O(1) for decode() method.
 * To covert decimal integer id n to its base-62 representation will take O(log62(n)) time.
 * Space complexity: O(m) - where m is the number of entries in the HashMap.
 * i.e., the number of shortURLs generated so far.
 */
public class LC535EncodeAndDecodeTinyURL {

    private static final Map<String, String> shortToLongMap = new HashMap<>();
    private static final Map<String, String> LongToShortMap = new HashMap<>();
    private static final String BASE_HOST = "http://tinyurl.com/";
    private static final int K = 6; // 6-digit long  short URL is good enough

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        if (LongToShortMap.containsKey(longUrl)) {
            return LongToShortMap.get(longUrl);
        }

        String shortUrl = generateRandomShortUrl();

        while(shortToLongMap.containsKey(shortUrl));
        shortUrl = generateRandomShortUrl();

        shortToLongMap.put(shortUrl, longUrl);
        LongToShortMap.put(longUrl, shortUrl);
        return shortUrl;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl)
    {
        return shortToLongMap.get(shortUrl);
    }

    private String generateRandomShortUrl()
    {
        final String BASE62 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < K; i++) {
            int randomIndex = ThreadLocalRandom.current().nextInt(0, BASE62.length());
            sb.append(BASE62.charAt(randomIndex));
        }

        String shortUrl = BASE_HOST + sb.toString();
        return shortUrl;
    }
}

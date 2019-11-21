package Dropbox.哦你赛;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class KeyValueDatabase {

    Map<Integer, Integer> map = new HashMap<>();

    //return a transaction ID
    public void openTransaction() {

    }


    public Object read(String transactionId, Object key) {
        return null;
    }

    public void write(String transactionId, Object key, Object value) {

    }


    public void commit(String transactionId) {


    }


    public static void main (String[] str) {
        try {
            InputStream inputStream = new FileInputStream("/Users/Nero/IdeaProjects/algorithm/src/main/java/common/ReadWriteLock.java");
            byte[] bytes = new byte[22];
            inputStream.read(bytes);
            for (byte b : bytes) {
                System.out.println((char)b);
            }
        } catch (Exception e) {

        }
    }
}

package Dropbox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindDuplicatedFile {

    /**
     * Follow-up beyond contest:
     * Imagine you are given a real file system, how will you search files? DFS or BFS?
     * If the file content is very large (GB level), how will you modify your solution?
     * If you can only read the file by 1kb each time, how will you modify your solution?
     * What is the time complexity of your modified solution? What is the most time-consuming part and memory consuming part of it? How to optimize?
     * How to make sure the duplicated files you findWithDuplicates are not false positive?
     * @param paths
     * @return
     */
    public List<List<String>> findDuplicate(String[] paths) {
        Map<String, List<String>> map = new HashMap<>();

        for (String path : paths) {
            String[] strs = path.split(" ");
            String root = strs[0] + "/";
            for (int i = 1; i < strs.length; ++i) {
                int index = strs[i].indexOf("(");
                String fileName = strs[i].substring(0, index);
                String content = strs[i].substring(index);
                if (map.containsKey(content)) {
                    map.get(content).add(root+fileName);
                } else {
                    List<String> list = new ArrayList<>();
                    list.add(root+fileName);
                    map.put(content, list);
                }
            }
        }

        List<List<String>> ret = new ArrayList<>();

        for (String key : map.keySet()) {
            if (map.get(key).size() > 1) {
                ret.add(map.get(key));
            }
        }

        return ret;
    }

}

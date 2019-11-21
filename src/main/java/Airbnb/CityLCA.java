package Airbnb;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * input是[["earth", "north america", "south america"], ["south america", "brazil", "arginta"], ["north america", "united states", "canada"], ["united states", "california", "new york"], ["california", "San francisco", "Oakland"]]
 * 意思是对于每一row 第一项是后几位的parent 然后给你这个map 和两个城市 找到他们的LCA
 * example: "new york" 和 "oakland" 就是 united states
 */
public class CityLCA {
    
    public String getLCA(String[][] cities, String source, String dest) {
        
        Map<String, String> map = new HashMap<>();
        
        for (String[] s : cities) {
            for (int i = 1; i < s.length; ++i) {
                map.put(s[i], s[0]);
            }
        }
        
        String s = source, d = dest;
        HashSet<String> set = new HashSet<>();
        
        while (map.get(s) != null) {
            set.add(s);
            s = map.get(s);
        }
        
        while(map.get(d) != null) {
            if (set.contains(d)) {
                return d;
            }
            d = map.get(d);
        }
        
        return "";
    }
    
    public static void main(String[] args) {
        String[][] testCases = {
                {"earth", "north america", "south america"},
                {"south america", "brazil", "arginta"},
                {"north america", "united states", "canada"},
                {"united states", "california", "new york"},
                {"california", "san francisco", "oakland"}
        };

        CityLCA cityLCA = new CityLCA();
        System.out.println(cityLCA.getLCA(testCases, "new york", "oakland"));
    }
}

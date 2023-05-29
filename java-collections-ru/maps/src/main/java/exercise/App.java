package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class App {
    public static Map  getWordCount(String sentence) {
        Map<String, Integer> map = new HashMap<>();
        String[] str = sentence.replaceAll("(^\\s+|\\s+$)", "").split("\\s+");
        for (String word : str) {
            if (word.isEmpty()) {  // проверка на пустую строку
                break;
            }
            if (map.containsKey(word)) {
                int count = map.get(word);
                map.put(word, count + 1);
            } else {
                map.put(word, 1);
            }
        }
        return map;
    }
    public static String toString(Map map) {
            StringBuilder str = new StringBuilder();
            System.out.println("{");
            for (Object key : map.keySet()) {
                str.append("  " + key + ":" + " " + map.get(key) + "\n");
            }
            System.out.println(str);
            System.out.println("}");
            return str.toString().trim();
        }
}

//END

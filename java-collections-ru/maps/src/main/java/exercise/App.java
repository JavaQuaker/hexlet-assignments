package exercise;


import java.util.HashMap;

import java.util.Map;

// BEGIN
public class App {
    static String sentence1 = "the java is the best programming language java";
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
    public static String toString(Map<String, Integer> map) {
        if (map.isEmpty()) {
            return "{}";
        }
        StringBuilder builder = new StringBuilder("{\n");
        for (String key : map.keySet()) {
            builder.append("  " + key + ":" + " "+ map.get(key) + "\n");
        }
        return builder.append("}").toString();
    }
    public static void main(String[] args) {
        toString(getWordCount(sentence1));
    }
}

//END

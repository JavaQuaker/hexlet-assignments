package exercise;


import java.util.HashMap;

import java.util.Map;

// BEGIN
public class App {
    static String sentence1 = "word text cat apple word apple word";
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
            str.append("  ").append(key).append(":" + " ").append(map.get(key)).append("\n");
        }
        System.out.println(str.toString().replaceAll("\\s+$", " "));
        System.out.println("}");
        return str.toString();
    }

    public static void main(String[] args) {
        toString(getWordCount(sentence1));
    }
}

//END

package exercise;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Map.Entry;

// BEGIN
public class App {
public static List findWhere(List<Map<String, String>> list, Map map) {
        List<Map<String, String>> result = new ArrayList<>();
        for (Map<String, String> book : list) {
        if (book.entrySet().containsAll(map.entrySet())) {
        result.add(book);
        }

        }
        return result;

        }
public static String toString(List<Map<String, String>> list) {
        System.out.println("[");
        for (Map<String, String> book : list) {
        System.out.println("  " + book);
        }
        System.out.println("]");
        return list.toString();
        }
        }

//END

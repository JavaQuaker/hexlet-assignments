package exercise;

import java.util.*;

// BEGIN
/*
Map<String, Object> data1 = new HashMap<>(
    Map.of("one", "eon", "two", "two", "four", true)
);
System.out.println(data1); //=> {two=two, four=true, one=eon}

Map<String, Object> data2 = new HashMap<>(
    Map.of("two", "own", "zero", 4, "four", true)
);
System.out.println(data2); //=> {zero=4, two=own, four=true}

Map<String, String> result = App.genDiff(data1, data2);
System.out.println(result); //=> {four=unchanged, one=deleted, two=changed, zero=added}
     */
public class App {
    public static Map<String, String> genDiff(Map<String, Object> dataOne, Map<String, Object> dataTwo) {
        Map<String, String> result = new LinkedHashMap<>();
        TreeSet<String> keySet = new TreeSet<>(dataOne.keySet());
        keySet.addAll(dataTwo.keySet());

        for (String key : keySet) {
            if (!dataTwo.containsKey(key)) {
                result.put(key, "deleted");

            }
            else if (!dataOne.containsKey(key)) {
                result.put(key, "added");
            }

            else if (compare(dataOne.get(key), dataTwo.get(key))) {
                result.put(key, "unchanged");
            } else {
                result.put(key, "changed");
            }
        }
        return result;
    }
    public static Boolean compare(Object objectOne, Object objectTwo) {
        if ((objectOne == null) && (objectTwo == null)) {
            return true;
        }
        if ((objectOne == null) || (objectTwo == null)) {
            return false;
        }
        return objectOne.equals(objectTwo);
    }
}
//END

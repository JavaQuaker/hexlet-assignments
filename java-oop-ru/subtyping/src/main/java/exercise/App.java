package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;

// BEGIN
public class App {
    public static Map<String, String> swapKeyValue(KeyValueStorage storage) {
        Map<String, String> map = storage.toMap();
        Map<String, String> result = new HashMap<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            result.put(entry.getValue(), entry.getKey());
        }

        for (Map.Entry<String, String> entry : result.entrySet()) {
            storage.set(entry.getKey(), entry.getValue());
        }
        return result;
    }
}
// END

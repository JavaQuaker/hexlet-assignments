package exercise;

import java.util.stream.Collectors;
import java.util.Map;

// BEGIN
public class Tag {
    public String tag;
    public Map<String, String> map;

    public Tag(String tag, Map<String, String> map) {
        this.tag = tag;
        this.map = map;
    }

    @Override
    public String toString() {
        String str = "";
        for (Map.Entry<String, String> entry : map.entrySet()) {
            str = str.concat(" " + entry.getKey() + "=" + "\"" + entry.getValue() + "\"");
        }
        return "<" + tag + str + ">";
    }

}
// END

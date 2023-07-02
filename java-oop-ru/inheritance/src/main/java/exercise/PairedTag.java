package exercise;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

// BEGIN
public class PairedTag extends Tag {

    String bodyTag;
    List<Tag> list;

    public PairedTag(String tag, Map<String, String> map, String bodyTag, List<Tag> list) {
        super(tag, map);
        this.bodyTag = bodyTag;
        this.list = list;
    }

    @Override
    public String toString() {
        String result = super.toString();
        String str = "";
        if (list == null) {

            return result + bodyTag + "</" + tag + ">";

        } else {

            for (Tag t : list) {

                str = str.concat(t.toString());
            }
            return result + bodyTag + str + "</" + tag + ">";
        }
    }
}

// END

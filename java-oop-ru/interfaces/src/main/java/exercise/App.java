package exercise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class App {
    public static List buildApartmentsList(List<Home> list, int n) {
        List<String> result = new ArrayList<>();
        Collections.sort(list, Comparator.comparing(Home::getArea));
        for (int i = 0; i < n && i < list.size(); i++) {
            result.add(list.get(i).toString());
        }
        return result;
    }
}
// END

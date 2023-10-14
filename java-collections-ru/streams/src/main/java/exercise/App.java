package exercise;

import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

// BEGIN
public class App {
    public static int getCountOfFreeEmails(List<String> list){
        long count = list.stream()
                .filter(email -> email.endsWith("gmail.com") || email.endsWith("yandex.ru")
                        || email.endsWith("hotmail.com"))
                .count();
        return (int) count;
    }

}
// END

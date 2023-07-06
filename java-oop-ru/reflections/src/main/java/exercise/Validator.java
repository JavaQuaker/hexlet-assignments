package exercise;
import exercise.demo.RandomNumber;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Field;
// BEGIN
public class Validator {
    private static MinLength minLength;
    private static Address address;


    public static List validate(Address address) {
        List<String> list = new ArrayList<>();
        Field[] fields = address.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {

                if (field.get(address) == null) {
                    if (field.getName().equals("flatNumber")) {
                        return list;
                    } else {
                        list.add(field.getName());
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
        return list;
    }
}
// END

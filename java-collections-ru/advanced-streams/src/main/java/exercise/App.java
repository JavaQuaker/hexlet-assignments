package exercise;

import java.util.stream.Collectors;
import java.util.Arrays;

// BEGIN
public class App {

    public static String getForwardedVariables(String environment) {

        StringBuilder builder = new StringBuilder();
        String[] str = environment.split(",");
        System.out.println(Arrays.toString(str));

        for (int i = 0; i < str.length; i++) {
            if (str[i].contains("X_FORWARDED_")) {
                String result = str[i].replace("X_FORWARDED_", "").trim();

                builder.append(result + ",");
            }
        }
        return "\"" + removeLastChar(builder) + "\"";
    }

    public static StringBuilder removeLastChar(StringBuilder builder) {
        if (builder == null || builder.length() == 0) {
            return builder;
        }
        return new StringBuilder(builder.substring(0, builder.length() - 1));
    }
}

//END

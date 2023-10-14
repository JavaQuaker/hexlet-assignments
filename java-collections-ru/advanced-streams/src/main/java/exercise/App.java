package exercise;

import java.util.stream.Collectors;
import java.util.Arrays;

// BEGIN
public class App {
//    static String expected2 = "X_FORWARDED_MAIL=tirion@google.com,X_FORWARDED_HOME=/home/tirion,language=en";
    static String expected2 = "key5=value5,X_FORWARDED_var3=value,key6=value6";
    public static String getForwardedVariables(String environment) {
        StringBuilder result = new StringBuilder();
        String[] str = environment.split(",");
            result.append("\"");
        for (String q : str) {
            if (q.contains("X_FORWARDED_")) {
                String w = q.replaceAll("X_FORWARDED_", "");
                result.append(w);
                result.append(",");
            }
        }
        return removeLastChar(result).toString() + "\"";
    }
    public static StringBuilder removeLastChar(StringBuilder builder) {
        if (builder == null || builder.length() == 0) {
            return builder;
        }
        return new StringBuilder(builder.substring(0, builder.length() - 1));
    }

    public static void main(String[] args) {
        System.out.println(getForwardedVariables(expected2));
    }
}

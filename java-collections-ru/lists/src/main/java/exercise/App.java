package exercise;


import java.util.ArrayList;
import java.util.List;

// BEGIN
/*
App.scrabble("rkqodlw", "world"); // true
App.scrabble("ajv", "java"); // false
App.scrabble("avjafff", "JaVa"); // true
App.scrabble("", "hexlet"); // false
*/
public  class App {
    public static boolean scrabble(String str1, String str2) {
        if (str1.length() < str2.length()) {
            return false;
        }
        List<Character> list1 = new ArrayList<>();
        for (char ch : str1.toLowerCase().toCharArray()) {
            list1.add(ch);
        }
        List<Character> list2 = new ArrayList<>();
        for (char ch : str2.toLowerCase().toCharArray()) {
            list2.add(ch);
        }
        for (int i = 0; i < str2.length(); i++) {
            char ch = str2.toLowerCase().charAt(i);
            int count1 = countChar(list1, ch);
            int count2 = countChar(list2, ch);
            if (count1 < count2) {
                return false;
            }
        }
        return true;
    }
    public static int countChar(List list, char ch) {
        int count = 0;
        for (Object f : list) {
            if (f.equals(ch)) {
                count++;
            }
        }
        return count;
    }
}



//END

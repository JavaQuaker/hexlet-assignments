package exercise;



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
        for (int i = 0; i < str2.length(); i++) {
            char ch = str2.charAt(i);
            int count1 = countChar(str1, ch);
            int count2 = countChar(str2, ch);
            if (count1 < count2) {
                return false;
            }
        }
        return true;
    }
    public static int countChar(String str, char ch) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.toLowerCase().charAt(i) == ch) {
                count++;
            }
        }
        return count;
    }
}



//END

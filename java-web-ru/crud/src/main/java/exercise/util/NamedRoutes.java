package exercise.util;

public class NamedRoutes {

    public static String rootPath() {
        return "/";
    }

    // BEGIN
    public static String postsPath() {
        return "/posts";
    }
    public static String postPath(String id) {
        return "/posts/" + id;
    }
    public static String postsPath(int list) {
        return "posts?page=" + String.valueOf(list);
    }
    public static String postPath(long list) {
        return postPath(String.valueOf(list));
    }


    // END
}

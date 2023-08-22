package exercise;

import io.javalin.Javalin;
import java.util.List;
import java.util.Map;

public final class App {

    private static final List<Map<String, String>> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        // BEGIN
        app.get("/users", ctx -> {
            Integer page = ctx.queryParamAsClass("page", Integer.class).getOrDefault(1);
            Integer per = ctx.queryParamAsClass("per", Integer.class).getOrDefault(5);
            List<Map<String, String>> result = Data.getUsers().stream()
                    .skip((long) (page - 1) * per)
                    .limit(per)

                    .collect(Collectors.toList());

            ctx.json(result);
        });
        // END

        return app;

    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}

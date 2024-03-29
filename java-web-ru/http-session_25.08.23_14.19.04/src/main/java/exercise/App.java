package exercise;

import io.javalin.Javalin;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class App {

    private static final List<Map<String, String>> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        // BEGIN

        app.get("users/", ctx -> {
            Integer per = ctx.queryParamAsClass("per", Integer.class).getOrDefault(5);
            Integer page = ctx.queryParamAsClass("page", Integer.class).getOrDefault(1);

            int offset = (page - 1) * per;
            List<Map<String, String>> user = Data.getUsers().stream()
                    .skip(offset)
                    .limit(per)
                    .collect(Collectors.toList());
            ctx.json(user);
        });
        // END

        return app;

    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}

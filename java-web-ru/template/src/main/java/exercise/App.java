package exercise;

import io.javalin.Javalin;
import java.util.List;
import io.javalin.http.NotFoundResponse;
import exercise.model.User;
import exercise.dto.users.UserPage;
import exercise.dto.users.UsersPage;
import org.eclipse.jetty.server.Authentication;

import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public final class App {

    // Каждый пользователь представлен объектом класса User
    private static final List<User> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        // BEGIN
        app.get("users/{id}", ctx -> {
            Long id = ctx.pathParamAsClass("id", long.class).get();
            User user = Data.getUsers().stream()
                    .filter(x -> x.getId()==id)
                    .findFirst()
                    .orElse(null);
            if (user == null) {
                throw new NotFoundResponse("User not found");
            }
            UserPage page = new UserPage(user);
            ctx.render("users/show.jte", Collections.singletonMap("page", page));
        });

        app.get("users/", ctx -> {
            List<User> users = Data.getUsers();
            UsersPage page = new UsersPage(users);
            ctx.render("users/index.jte", Collections.singletonMap("page", page));
        });
        // END

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}

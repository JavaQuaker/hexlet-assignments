package exercise;

import io.javalin.Javalin;
import java.util.List;
import java.util.Collections;
import java.util.Locale;

import exercise.model.User;
import exercise.dto.users.UsersPage;
import exercise.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import exercise.util.Security;

public final class App {

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        app.get("/users", ctx -> {
            List<User> users = UserRepository.getEntities();
            var page = new UsersPage(users);
            ctx.render("users/index.jte", Collections.singletonMap("page", page));
        });

        // BEGIN
        app.get("/users/build", ctx -> {
            ctx.render("users/build.jte");
        });

        app.post("/users", ctx -> {


           String extractionFirstName = ctx.formParam("firstName");
           if (ctx.formParam("firstName") == null) {
               new NullPointerException();
           } else {
               extractionFirstName = ctx.formParam("firstName").trim();

           }
            String firstName = StringUtils.capitalize(extractionFirstName);



           String extractionLastName = ctx.formParam("lastName");
           if (ctx.formParam("lastName") == null) {
               new NullPointerException();
           } else {
               extractionLastName = ctx.formParam("lastName").trim();
           }
           String lastName = StringUtils.capitalize(extractionLastName);


           String email = ctx.formParam("email").toLowerCase().trim();
           String password = Security.encrypt(ctx.formParam("password"));

           var user = new User(firstName, lastName, email, password);
           UserRepository.save(user);
           ctx.redirect("/users");

        });
        // END

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}

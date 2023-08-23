package exercise;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.validation.Validator;


import java.net.http.HttpResponse;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// BEGIN
import io.javalin.http.NotFoundResponse;
// END

public final class App {

    private static final List<Map<String, String>> COMPANIES = Data.getCompanies();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        // BEGIN

        app.get("companies/{id}", ctx -> {
            String id = ctx.pathParam("id");
            List<Map<String, String>> result = Data.getCompanies().stream()
                    .filter(x -> x.get("id").equals(id))
                            .collect(Collectors.toList());


            Map<String, String> map = result.stream()
                            .flatMap(x -> x.entrySet().stream())
                                    .collect(Collectors.toMap(
                                            Map.Entry::getKey,
                                            Map.Entry::getValue
                                    ));

            ctx.json(map);

            List<Map<String, String>> ID = Data.getCompanies().stream()
                    .filter(x -> x.get("id").equals(id))
                    .collect(Collectors.toList());
            if (ID.isEmpty()) {
                ctx.status(404);
                ctx.result("Company not found");
            }

        });

        // END

        app.get("/companies", ctx -> {
            ctx.json(COMPANIES);
        });

        app.get("/", ctx -> {
            ctx.result("open something like (you can change id): /companies/5");
        });

        return app;

    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}

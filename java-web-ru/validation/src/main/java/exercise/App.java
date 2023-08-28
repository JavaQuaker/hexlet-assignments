package exercise;

import io.javalin.Javalin;
import io.javalin.validation.ValidationException;
import java.util.List;
import exercise.model.Article;
import exercise.dto.articles.ArticlesPage;
import exercise.dto.articles.NewArticlePage;
import java.util.Collections;

import exercise.repository.ArticleRepository;

public final class App {

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        app.get("/articles", ctx -> {
            List<Article> articles = ArticleRepository.getEntities();
            var page = new ArticlesPage(articles);
            ctx.render("articles/index.jte", Collections.singletonMap("page", page));
        });

        // BEGIN
        app.get("/articles/new", ctx -> {
           NewArticlePage page = new NewArticlePage();
           ctx.render("articles/build.jte", Collections.singletonMap("page", page));

        });

        app.post("/articles", ctx -> {
           String title = ctx.formParam("title");
           String content = ctx.formParam("content");

           try {
               String titleForm = ctx.formParamAsClass("title", String.class)
                       .check(x -> x.length() > 2, "Название не должно быть короче двух символов")
                       .check(x -> !ArticleRepository.existsByTitle(x), "Статья с таким названием уже существует")
                       .get();

               String contentForm = ctx.formParamAsClass("content", String.class)
                       .check(x -> x.length() >= 10, "Статья должна быть не короче 10 символов")
                       .get();
               Article article = new Article(titleForm, contentForm);
               ArticleRepository.save(article);
               ctx.redirect("/articles");

           } catch (ValidationException e) {
               NewArticlePage page = new NewArticlePage(title, content, e.getErrors());
               ctx.status(422);
               ctx.render("articles/build.jte", Collections.singletonMap("page", page));

           }
        });
        // END

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}

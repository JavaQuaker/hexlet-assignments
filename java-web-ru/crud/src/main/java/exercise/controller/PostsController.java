package exercise.controller;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import exercise.dto.posts.PostsPage;
import exercise.dto.posts.PostPage;
import exercise.model.Post;
import exercise.repository.PostRepository;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

public class PostsController {

    // BEGIN
    public static void index(Context ctx) {
        Integer list = ctx.queryParamAsClass("page", Integer.class).getOrDefault(1);
        Integer per = ctx.queryParamAsClass("per", Integer.class).getOrDefault(5);
        List<Post> posts = PostRepository.getEntities().stream()
                .skip((long)(list - 1) * per)
                .limit(per)
                .collect(Collectors.toList());

        PostsPage page = new PostsPage(posts, list);
        ctx.render("posts/index.jte", Collections.singletonMap("page", page));
    }

    public static void show(Context ctx) {
        long id = ctx.pathParamAsClass("id", long.class).get();
        Post post = PostRepository.find(id);


        if (post == null) {
            throw new NotFoundResponse("Page not found");
        }

        PostPage page = new PostPage(post);
        ctx.render("posts/show.jte", Collections.singletonMap("page", page));

    }
    // END
}

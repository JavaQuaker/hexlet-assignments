package exercise.controller.users;

import exercise.Data;
import exercise.model.Post;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;

// BEGIN
@RestController
@RequestMapping("/api")
public class PostsController {
    private List<Post> posts = Data.getPosts();

    @GetMapping("/users/{id}/posts")
    public List<Post> show(@PathVariable Integer id) {
        return posts.stream()
                .filter(value -> value.getUserId() == id)
                .toList();

    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/users/{id}/posts")
    public Post create(@RequestBody Post post, @PathVariable Integer id) {
        post.setUserId(id);
        posts.add(post);
        return post;
    }

}


// END

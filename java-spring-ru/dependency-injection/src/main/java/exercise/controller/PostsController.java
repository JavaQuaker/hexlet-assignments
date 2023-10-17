package exercise.controller;

import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.Optional;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;

// BEGIN
@RestController
@RequestMapping("/posts")
public class PostsController {

    @Autowired

    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;


    @GetMapping(path = "")
    public List<Post> index() {
        return postRepository.findAll();
    }
    @GetMapping("/{id}")
    public Post show(@PathVariable long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id" + " " + id + " " + "not found"));
        return post;
    }
    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public Post create(@RequestBody Post post) {
        postRepository.save(post);
        return post;
    }
    @PutMapping("/{id}")
    public Post update(@PathVariable long id, @RequestBody Post data) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + " " + id + " " + "not found"));
        post.setBody(data.getBody());
        post.setTitle(data.getTitle());
        return data;
    }
    @DeleteMapping("/{id}")
    public void remove(@PathVariable long id) {
        Post post = postRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Post with id" + " " + id + " " + "not found"));

        postRepository.deleteById(id);
        commentRepository.deleteByPostId(id);

    }
}

// END

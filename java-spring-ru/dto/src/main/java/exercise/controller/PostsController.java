package exercise.controller;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;
import exercise.dto.PostDTO;
import exercise.dto.CommentDTO;

// BEGIN
@RestController
@RequestMapping("/posts")
public class PostsController {
    @Autowired
    PostRepository postRepository;
    @Autowired
    CommentRepository commentRepository;

    @GetMapping(path = "")
    public List<PostDTO> index() {
        var posts = postRepository.findAll();
        var result = posts.stream()
                .map(this::toDTO)
                .toList();
        return result;
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostDTO show(@PathVariable long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id" + " " + id + " " + "not found"));
        return toDTO(post);
    }
    private PostDTO toDTO(Post post) {
        PostDTO dto = new PostDTO();
        dto.setId(post.getId());
        dto.setBody(post.getBody());
        dto.setTitle(post.getTitle());
        List<Comment> comments = commentRepository.findByPostId(post.getId());
        ArrayList<CommentDTO> commentsDTO = new ArrayList<CommentDTO>();
        for (Comment comment : comments) {
            CommentDTO commentDTO = new CommentDTO();
            commentDTO.setBody(comment.getBody());
            commentDTO.setId(comment.getId());
            commentsDTO.add(commentDTO);
        }
        dto.setComments(commentsDTO);
        return dto;
    }

}
// END

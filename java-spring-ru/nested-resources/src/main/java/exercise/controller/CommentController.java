package exercise.controller;

import exercise.dto.CommentDto;
import exercise.dto.PostDto;
import exercise.model.Comment;
import exercise.repository.CommentRepository;
import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.ResourceNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/posts")
@AllArgsConstructor
public class CommentController {

    private final CommentRepository commentRepository;

    private  PostRepository postRepository;

    // BEGIN
    @GetMapping(path = "/{postId}/comments")
    public Iterable<Comment> getPostComments(@PathVariable long postId) {
        return commentRepository.findAllByPostId(postId);
    }
    @GetMapping(path = "/{postId}/comments/{commentId}")
    public Comment getComment(@PathVariable long postId, @PathVariable long commentId) {
       var comment = commentRepository.findByIdAndPostId(postId, commentId)
               .orElseThrow(() -> new ResourceNotFoundException("not found"));
       return comment;
    }
    @PostMapping(path = "/{postId}/comments")
    @ResponseStatus(HttpStatus.OK)
    public Comment setComment(@RequestBody CommentDto commentData, @PathVariable long postId) {
        var post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));

        Comment comment = new Comment();
        comment.setContent(commentData.content());
        comment.setPost(post);
        commentRepository.save(comment);
        return comment;
    }
    @PatchMapping(path = "/{postId}/comments/{commentId}")
    public Comment updateComment(@PathVariable long postId, @PathVariable long commentId, @RequestBody CommentDto commentDto) {
        var comment = commentRepository.findByIdAndPostId(commentId, postId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));
        comment.setContent(commentDto.content());
        return commentRepository.save(comment);
    }
    @DeleteMapping(path = "/{postId}/comments/{commentId}")
    public void deleteComment(@PathVariable long postId, @PathVariable long commentId) {
        var comment = commentRepository.findByIdAndPostId(commentId, postId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));
        commentRepository.delete(comment);
    }

    // END
}

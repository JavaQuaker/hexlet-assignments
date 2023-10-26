package exercise.controller;

import java.util.List;

import exercise.dto.*;
import exercise.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/books")
public class BooksController {
    @Autowired
    private BookService bookService;

    // BEGIN
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public  List<BookDTO> indexAuthor() {
        var authors = bookService.getAll();
        return authors;
    }

    @GetMapping("/{id}")
    public BookDTO showAuthor(@PathVariable Long id) {
        return bookService.findById(id);
    }
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public BookDTO createAuthor(@RequestBody BookCreateDTO bookDATA) {
        return bookService.create(bookDATA);
    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookDTO updateAuthor(@RequestBody BookUpdateDTO bookDATA, @PathVariable long id) {
        return bookService.update(bookDATA, id);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAuthor(@PathVariable Long id) {
        bookService.delete(id);
    }
    // END
}

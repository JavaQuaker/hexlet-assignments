package exercise.service;

import exercise.dto.*;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.BookMapper;
import exercise.model.Author;
import exercise.model.Book;
import exercise.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    // BEGIN
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookMapper bookMapper;

    public List<BookDTO> getAll() {
        List<Book> books = bookRepository.findAll();
        List<BookDTO> result = new ArrayList<>();
        for (Book book : books) {
            BookDTO bookDTO = bookMapper.map(book);
            result.add(bookDTO);
        }
        return result;
    }
    public BookDTO create(BookCreateDTO bookDATA) {
        Book book = bookMapper.map(bookDATA);
        book.setAuthor(book.getAuthor());
        book.setTitle(book.getTitle());
        bookRepository.save(book);
        BookDTO bookDTODTO = bookMapper.map(book);
        return bookDTODTO;
    }
    public BookDTO findById(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            BookDTO bookDTO = bookMapper.map(book);
            return bookDTO;
        } else {
            throw new ResourceNotFoundException("not found");
        }
    }
    public BookDTO update(BookUpdateDTO bookData, Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            bookMapper.update(bookData, book);
            bookRepository.save(book);
            BookDTO bookDTO = bookMapper.map(book);
            return bookDTO;
        } else {
            throw new ResourceNotFoundException("Not Found");
        }
    }
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
    // END
}

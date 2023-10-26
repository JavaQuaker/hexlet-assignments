package exercise.service;

import exercise.dto.AuthorCreateDTO;
import exercise.dto.AuthorDTO;
import exercise.dto.AuthorUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.AuthorMapper;
import exercise.model.Author;
import exercise.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    // BEGIN
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private AuthorMapper authorMapper;

    public List<AuthorDTO> getAll() {
        List<Author> authors = authorRepository.findAll();
        List<AuthorDTO> result = new ArrayList<>();
        for (Author author : authors) {
            AuthorDTO authorDTO = authorMapper.map(author);
            result.add(authorDTO);
        }
        return result;
    }
    public AuthorDTO create(AuthorCreateDTO authorDATA) {
        Author author = authorMapper.map(authorDATA);
        author.setFirstName(author.getFirstName());
        author.setLastName(author.getLastName());
        authorRepository.save(author);
        AuthorDTO authorDTO = authorMapper.map(author);
        return authorDTO;
    }
    public AuthorDTO findById(Long id) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if (optionalAuthor.isPresent()) {
            Author author = optionalAuthor.get();
            AuthorDTO authorDTO = authorMapper.map(author);
            return authorDTO;
        } else {
            throw new ResourceNotFoundException("not found");
        }
    }
    public AuthorDTO update(AuthorUpdateDTO authorData, Long id) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if (optionalAuthor.isPresent()) {
            Author author = optionalAuthor.get();
            authorMapper.update(authorData, author);
            authorRepository.save(author);
            AuthorDTO authorDTO = authorMapper.map(author);
            return authorDTO;
        } else {
            throw new ResourceNotFoundException("Not Found");
        }
    }
    public void delete(Long id) {
        authorRepository.deleteById(id);
    }

    // END
}

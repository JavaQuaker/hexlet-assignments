package exercise.controller;

import exercise.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

import exercise.model.Person;

@RestController
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping(path = "/{id}")
    public Person show(@PathVariable long id) {
        return personRepository.findById(id).get();
    }

    // BEGIN

    @GetMapping("")
    public List<Person> index() {
        List<Person> personList = personRepository.findAll();
        return personList;
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    Person create(@RequestBody Person person) {
        personRepository.save(person);
        return person;
    }
    @DeleteMapping("/{id}")
    public void destroy(@PathVariable long id) {
        personRepository.deleteById(id);
    }

    // END
}

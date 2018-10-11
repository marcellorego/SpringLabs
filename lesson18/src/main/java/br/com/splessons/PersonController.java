package br.com.splessons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.List;

@RestController
@RequestMapping(path = "/person", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @PostMapping
    public Person savePerson(@RequestBody @Valid Person person) throws ValidationException {

        long count = personRepository.countByName(person.getName());

        if (count > 0) {
            throw new ValidationException("Person already exists");
        }

        return personRepository.save(person);
    }

    @GetMapping
    public List<Person> getCustomers() {
        return personRepository.findAll();
    }

    @ExceptionHandler(ValidationException.class)
    public final ResponseEntity<Void> handleUserNotFoundException(ValidationException ex, WebRequest request) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}

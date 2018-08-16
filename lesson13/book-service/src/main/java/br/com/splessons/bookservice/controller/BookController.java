package br.com.splessons.bookservice.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.splessons.bookservice.model.Book;

@RestController
@RequestMapping("/books")
public class BookController {

  private List<Book> bookList = Arrays.asList(
        new Book(1L, "Baeldung goes to the market", "Tim Schimandle"),
        new Book(2L, "Baeldung goes to the park", "Slavisa")
    );
 
    @GetMapping("")
    public List<Book> findAllBooks() {
        return bookList;
    }
 
    @GetMapping("/{bookId}")
    public Book findBook(@PathVariable Long bookId) {
        return bookList.stream()
            .filter(b -> b.getId().equals(bookId))
            .findFirst()
            .orElse(null);
    }
    
}
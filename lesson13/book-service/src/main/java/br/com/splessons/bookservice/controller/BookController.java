package br.com.splessons.bookservice.controller;

import java.util.Arrays;
import java.util.List;

import br.com.splessons.bookservice.exception.BookNotFoundException;
import br.com.splessons.bookservice.payload.BookStar;
import br.com.splessons.bookservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.splessons.bookservice.model.Book;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("")
    public List<Book> findAllBooks() {
        return bookService.findAllBooks();
    }

    @GetMapping("/{bookId}")
    public Book findBook(@PathVariable Long bookId) {
        return bookService.findBookById(bookId).orElseThrow(() -> new BookNotFoundException(bookId));
    }

    @GetMapping("/stars/{bookId}")
    public BookStar findBookStars(@PathVariable Long bookId) {
        return bookService.findBookStarById(bookId).orElseThrow(() -> new BookNotFoundException(bookId));
    }

}
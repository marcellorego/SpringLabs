package br.com.splessons.bookservice.service;

import br.com.splessons.bookservice.client.StarClient;
import br.com.splessons.bookservice.model.Book;
import br.com.splessons.bookservice.payload.BookStar;
import br.com.splessons.bookservice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    private final StarClient starClient;

    @Autowired
    public BookService(BookRepository bookRepository, StarClient starClient) {
        this.bookRepository = bookRepository;
        this.starClient = starClient;
    }

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> findBookById(Long bookId) {
        Book result = bookRepository.findOne(bookId);
        return Optional.ofNullable(result);
    }

    public Optional<BookStar> findBookStarById(Long bookId) {

        Optional<Book> bookOptional = findBookById(bookId);
        if (bookOptional.isPresent()) {
            List<Integer> stars = starClient.bookStars(bookId);
            return Optional.of(new BookStar(bookOptional.get(), stars));
        } else {
            return Optional.empty();
        }


    }
}

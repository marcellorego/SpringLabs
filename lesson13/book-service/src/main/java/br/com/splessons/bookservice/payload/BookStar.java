package br.com.splessons.bookservice.payload;

import br.com.splessons.bookservice.model.Book;

import java.util.Collections;
import java.util.List;

public class BookStar {

    private final Book book;
    private final List<Integer> stars;

    public BookStar(Book book, List<Integer> stars) {
        this.book = book;
        this.stars = stars;
    }

    public Book getBook() {
        return this.book;
    }

    public List<Integer> getStars() {
        return Collections.unmodifiableList(this.stars);
    }
}

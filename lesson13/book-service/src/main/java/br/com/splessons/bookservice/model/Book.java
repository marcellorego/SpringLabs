package br.com.splessons.bookservice.model;

public class Book {

  private Long id;
  private String author;
  private String title;

  public Book(Long id, String author, String title) {
    this.id = id;
    this.author = author;
    this.title = title;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getAuthor() {
    return this.author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
  
}
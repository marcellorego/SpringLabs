package br.com.splessons.bookservice.model;

import javax.persistence.*;

@Entity
@Table(name = "book")
public class Book {

  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false)
  private String author;

  @Column(nullable = false)
  private String title;

  public Book() { }

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
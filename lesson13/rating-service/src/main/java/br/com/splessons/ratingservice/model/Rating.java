package br.com.splessons.ratingservice.model;

public class Rating {
  
  private Long id;
  private Long bookId;
  private int stars;  

  public Rating(Long id, Long bookId, int stars) {
    this.id = id;
    this.bookId = bookId;
    this.stars = stars;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getBookId() {
    return this.bookId;
  }

  public void setBookId(Long bookId) {
    this.bookId = bookId;
  }

  public int getStars() {
    return this.stars;
  }

  public void setStars(int stars) {
    this.stars = stars;
  }
}
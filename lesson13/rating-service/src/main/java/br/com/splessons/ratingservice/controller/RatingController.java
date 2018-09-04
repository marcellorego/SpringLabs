package br.com.splessons.ratingservice.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.*;

import br.com.splessons.ratingservice.model.Rating;

@RestController
@RequestMapping("/rating")
public class RatingController {
  private List<Rating> ratingList = Arrays.asList(
      new Rating(1L, 1L, 2), 
      new Rating(2L, 1L, 3), 
      new Rating(3L, 2L, 4),
      new Rating(4L, 2L, 5),
      new Rating(5L, 1L, 1)
    );

  @GetMapping("/book/{bookId}")
  public List<Rating> findRatingsByBookId(@PathVariable("bookId") Long bookId) {
    return bookId == null || bookId.equals(0L) ?
            Collections.emptyList() :
            ratingList.stream().filter(r -> r.getBookId().equals(bookId)).collect(Collectors.toList());
  }

  @GetMapping("/book/stars/{bookId}")
  public List<Integer> findStarsByBookId(@PathVariable("bookId") Long bookId) {
    return findRatingsByBookId(bookId).stream().map(rating -> rating.getStars()).collect(Collectors.toList());
  }

  @GetMapping("/{ratingId}")
  public List<Rating> findRatingsById(@PathVariable("ratingId") Long ratingId) {
    return ratingId == null || ratingId.equals(0L) ?
            Collections.emptyList() :
            ratingList.stream().filter(r -> r.getId().equals(ratingId)).collect(Collectors.toList());
  }

  @GetMapping("/star/{stars}")
  public List<Rating> findRatingsByStars(@PathVariable("stars") int stars) {
    return ratingList.stream().filter(r -> r.getStars() == stars).collect(Collectors.toList());
  }

  @GetMapping("")
  public List<Rating> findAllRatings() {
    return ratingList;
  }
}
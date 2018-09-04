package br.com.splessons.bookservice.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "rating-service")
public interface StarClient {
    @RequestMapping(method = RequestMethod.GET, value = "/rating/book/stars/{bookId}")
    List<Integer> bookStars(@PathVariable(value = "bookId") Long bookId);
}
package com.example.demo.controller;

import com.example.demo.dao.BlogPostDAO;
import com.example.demo.model.BlogPost;
import com.example.demo.model.BlogPostsDTO;
import com.example.demo.model.DateRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.List;

@RestController
public class BlogPostController {

    @Autowired
    BlogPostDAO blogPostDAO;

    @GetMapping("/blogPosts")
    public BlogPostsDTO findAll(
            @RequestParam("start") @NotNull Instant start,
            @RequestParam("end") @NotNull Instant end) {

        DateRange dateRange = new DateRange(start, end);
        List<BlogPost> blogPosts = blogPostDAO.findByCreationDate(dateRange);
        return BlogPostsDTO.builder().blogPosts(blogPosts).build();
    }
}

package com.example.demo.dao;

import com.example.demo.model.BlogPost;
import com.example.demo.model.DateRange;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BlogPostDAO {

    private final List<BlogPost> blogPosts = List.of(
            new BlogPost(Instant.parse("2020-01-01T15:00:00Z"), "How Spring DI works ?"),
            new BlogPost(Instant.parse("2020-01-25T12:30:00Z"), "A Spring Security overview"),
            new BlogPost(Instant.parse("2020-02-15T10:00:00Z"), "Integration tests with Spring")
    );

    public List<BlogPost> findByCreationDate(DateRange dateRange) {
        return blogPosts.stream()
                .filter(post -> post.getCreationDate().isAfter(dateRange.getStart()) && post.getCreationDate().isBefore(dateRange.getEnd()))
                .collect(Collectors.toList());

    }
}

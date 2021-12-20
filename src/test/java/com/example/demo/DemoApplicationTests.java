package com.example.demo;

import com.example.demo.model.BlogPostsDTO;
import com.example.demo.model.DateRange;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class DemoApplicationTests {

    @LocalServerPort
    int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    void _findAll_january_posts() {
        ResponseEntity<BlogPostsDTO> response = restTemplate.exchange(
                "http://localhost:" + port + "/blogPosts?start=2020-01-01T00:00:00Z&end=2020-01-30T23:59:59Z",
                HttpMethod.GET,
                null,
                BlogPostsDTO.class
        );

        BlogPostsDTO actual = response.getBody();

        assertEquals(2, actual.getBlogPosts().size(), "the two blog posts of january are returned");

        DateRange expectedDateRange = new DateRange(Instant.parse("2020-01-01T00:00:00Z"), Instant.parse("2020-01-30T23:59:59Z"));
        assertEquals(expectedDateRange, actual.getDateRange(), "the date range applied is returned in the response");
    }

}

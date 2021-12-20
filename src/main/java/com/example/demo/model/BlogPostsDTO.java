package com.example.demo.model;

import com.example.demo.model.contract.IDateRangeFiltered;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlogPostsDTO implements IDateRangeFiltered {

    private DateRange dateRange;
    private List<BlogPost> blogPosts;

}

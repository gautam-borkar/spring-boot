package com.gborkar.spring_aggregator.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserPost {
    
    private User user;
    private List<Post> posts;

}

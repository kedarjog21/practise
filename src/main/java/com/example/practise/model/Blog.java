package com.example.practise.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Blog {
    int id;
    String title;
    String body;
    long votes;
    int authorid;
}

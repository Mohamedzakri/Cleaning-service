package com.medzak.models;


import lombok.AllArgsConstructor;

import java.time.LocalTime;
import java.util.List;

//@Entity
@AllArgsConstructor
public class FilterSearchEntity {
    private double price;
    private String location;
    private int reputation;
    private List<String> services;
    private LocalTime startTime;
    private LocalTime endTime;
}

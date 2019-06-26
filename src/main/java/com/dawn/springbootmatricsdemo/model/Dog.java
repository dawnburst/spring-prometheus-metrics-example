package com.dawn.springbootmatricsdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Dog {

    private String name;
    private String type;
    private String dogOwnerName;
}

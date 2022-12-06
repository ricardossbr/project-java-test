package com.example.projectjavatest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Users {
    private List<User> users;
}

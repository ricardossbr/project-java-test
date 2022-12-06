package com.example.projectjavatest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Teams {
    private List<Team> teams;
}

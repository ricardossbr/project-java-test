package com.example.projectjavatest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamAndUserDTO {
    private String team;
    private Set<String> users;
    private String teamLead;

}

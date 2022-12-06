package com.example.projectjavatest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("team-and-user")
public class TeamAndUserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String name;
    private Set<UserModel> users;
    private UserModel teamLead;

    public TeamAndUserModel(TeamModel team, Set<UserModel> users, UserModel teamLead){
        this.id = team.getId();
        this.name = team.getName();
        this.users = users;
        this.teamLead = teamLead;
    }

}

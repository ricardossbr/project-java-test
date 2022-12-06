package com.example.projectjavatest.service;

import com.example.projectjavatest.dto.Team;
import com.example.projectjavatest.dto.Teams;
import com.example.projectjavatest.dto.User;
import com.example.projectjavatest.dto.Users;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Component
public class HttpService {
    @Value("${url.users}")
    private String urlUsers;

    @Value("${url.teams}")
    private String urlTeams;
    private final RestTemplate request = new RestTemplate();

    public Users getUsers(){
        try{
            final ResponseEntity<User[]> forEntity = request.getForEntity(urlUsers, User[].class);
            final Users users = new Users(Arrays.asList(forEntity.getBody()));
            return users;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    public Teams getTeams(){
        try{
            final ResponseEntity<Team[]> forEntity = request.getForEntity(urlTeams, Team[].class);
            final Teams teams = new Teams(Arrays.asList(forEntity.getBody()));
            return teams;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}

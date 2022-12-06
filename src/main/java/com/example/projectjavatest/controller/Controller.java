package com.example.projectjavatest.controller;

import com.example.projectjavatest.dto.TeamAndUserDTO;
import com.example.projectjavatest.exception.TeamAndUserException;
import com.example.projectjavatest.exception.TheTeamException;
import com.example.projectjavatest.exception.TheTeamLeadException;
import com.example.projectjavatest.exception.TheUserException;
import com.example.projectjavatest.model.TeamAndUserModel;
import com.example.projectjavatest.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


@RestController
public class Controller {

    @Autowired
    private Service service;

    @GetMapping("/up")
    public ResponseEntity getUp(){
        service.savedUser();
        service.saveTeam();
        return ResponseEntity.ok("");
    }

    @GetMapping(value = "/team-user", produces="application/json")
    public ResponseEntity getTeamUser(@RequestParam("idTeam") String team) throws TeamAndUserException {
        final TeamAndUserModel response = this.service.getTeamAndUser(team);
        if(Objects.isNull(response)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/team-user", produces="application/json", consumes="application/json")
    public ResponseEntity save(@RequestBody TeamAndUserDTO teamAndUserDTO) throws TheTeamLeadException, TheTeamException, TheUserException, TeamAndUserException {
        final TeamAndUserModel response = this.service.save(teamAndUserDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/team-user", produces="application/json")
    public ResponseEntity deleteTeamUser(@RequestParam("idTeam") String team) throws TeamAndUserException {
        this.service.deleteTeamAndUser(team);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/team-user", produces="application/json", consumes="application/json")
    public ResponseEntity edit(@RequestBody TeamAndUserDTO teamAndUserDTO, @RequestParam("idTeam") String team) throws TheTeamLeadException, TheTeamException, TeamAndUserException, TheUserException {
        if(Objects.nonNull(teamAndUserDTO) && Objects.nonNull(team)){
            final TeamAndUserModel response = this.service.edit(teamAndUserDTO, team);
            return ResponseEntity.ok(response);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }




}

package com.example.projectjavatest.service;

import com.example.projectjavatest.dto.TeamAndUserDTO;
import com.example.projectjavatest.dto.Teams;
import com.example.projectjavatest.dto.Users;
import com.example.projectjavatest.exception.TeamAndUserException;
import com.example.projectjavatest.exception.TheTeamLeadException;
import com.example.projectjavatest.exception.TheTeamException;
import com.example.projectjavatest.exception.TheUserException;
import com.example.projectjavatest.model.TeamAndUserModel;
import com.example.projectjavatest.model.TeamModel;
import com.example.projectjavatest.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Service {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private HttpService httpService;

    public List<TeamAndUserModel> getAllTeamAndUser() throws TeamAndUserException {
        return this.repositoryService.getAllTeamAndUser();
    }
    public TeamAndUserModel getTeamAndUser(String team) throws TeamAndUserException {
        final Optional<TeamAndUserModel> teamAndUserFound = getTeamAndUserModel(team);
        return teamAndUserFound.get();
    }

    private Optional<TeamAndUserModel> getTeamAndUserModel(String team) throws TeamAndUserException {
        final Optional<TeamAndUserModel> teamAndUserFound = this.repositoryService.getTeamAndUserByTeam(team);
        if(teamAndUserFound.isEmpty()){
            throw new TeamAndUserException("This TeamAndUser: " + team + " does not exist yet, please save the TeamAndUser and return here to see it.");
        }
        return teamAndUserFound;
    }

    public TeamAndUserModel save(TeamAndUserDTO teamAndUserDTO) throws TheTeamLeadException, TheTeamException, TheUserException, TeamAndUserException {
        final Set<UserModel> userModels = this.getUsers(teamAndUserDTO.getUsers());
        final UserModel teamLead = this.getTeamLead(teamAndUserDTO.getTeamLead(), userModels);
        final TeamModel teamFound = this.getTeamModel(teamAndUserDTO.getTeam());
        final Optional<TeamAndUserModel> teamAndUserFound = this.repositoryService.getTeamAndUserByTeam(teamFound.getId());
        try {
            if(teamAndUserFound.isEmpty()){
                return this.repositoryService.saveTeamAndUserModel(new TeamAndUserModel(teamFound, userModels, teamLead));
            }else{
                throw new TeamAndUserException("This TeamAndUser: " + teamAndUserDTO.getTeam() + " already exist, please use Put Method for change something in TeamAndUser.");
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public TeamAndUserModel edit(TeamAndUserDTO teamAndUserDTO, String idTeamOld) throws TheTeamLeadException, TheTeamException, TeamAndUserException, TheUserException {
        final Set<UserModel> userModels = getUsers(teamAndUserDTO.getUsers());
        final UserModel teamLead = this.getTeamLead(teamAndUserDTO.getTeamLead(), userModels);
        final TeamModel teamFound = this.getTeamModel(idTeamOld);
        final Optional<TeamAndUserModel> teamAndUserFound = this.repositoryService.getTeamAndUserByTeam(idTeamOld);
        try {
            if(teamAndUserFound.isEmpty()){
                this.deleteTeamAndUser(teamAndUserDTO.getTeam());
                return this.repositoryService.saveTeamAndUserModel(new TeamAndUserModel(teamFound, userModels, teamLead));
            }else{
                throw new TeamAndUserException("This TeamAndUser: " + teamAndUserDTO.getTeam() + " already exist, please use Put Method for change something in TeamAndUser.");
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public void deleteTeamAndUser(String idTeamAndUser) throws TeamAndUserException {
        final Optional<TeamAndUserModel> teamAndUserFound = getTeamAndUserModel(idTeamAndUser);
        this.repositoryService.delete(teamAndUserFound.get());
    }



    private TeamModel getTeamModel(String teamId) throws TheTeamException {
        final Optional<TeamModel> teamFound = this.repositoryService.findTeamBy(teamId);
        if(teamFound.isEmpty()){
            throw new TheTeamException( "Team: " + teamId + " does not exist, please verify the correct idTeam and try again! :) ");
        }
        return teamFound.get();
    }

    private UserModel getTeamLead(String teamLeadDTO, Set<UserModel> userModels) throws TheTeamLeadException {
        final Optional<UserModel> teamLead = userModels.stream().filter(user -> Objects.equals(user.getId(), teamLeadDTO)).findFirst();
        if(teamLead.isEmpty()){
            throw new TheTeamLeadException("The TeamLead: " + teamLeadDTO + " need to be in Users List! please verify the correct teamLeadId and try again! :)");
        }
        return teamLead.get();
    }


    private Set<UserModel> getUsers(Set<String> UsersModel) throws TheUserException {
        final Set<UserModel> users = new HashSet<>();
        for (String id: UsersModel) {
            final Optional<UserModel> userFound = this.repositoryService.findUserBy(id);
            if(userFound.isEmpty()) throw new TheUserException("The User: " + id + " not exist!" );
            users.add(userFound.get());
        }
        return users;
    }



    public void saveTeam(){
        try{
            final Teams teams = httpService.getTeams();
            teams.getTeams().stream().parallel().forEach( team -> {
                    this.repositoryService.saveTeamModel(new TeamModel(team.getId(), team.getName()));
                }
            );
        }catch (Exception e ){
            e.printStackTrace();
        }


    }

    public void savedUser(){
        try {
            final Users users = httpService.getUsers();
            users.getUsers().stream().parallel().forEach( user -> {
                    this.repositoryService.saveUserModel(new UserModel(user.getId(), user.getDisplayName()));
                }
            );
        }catch (Exception e ){
            e.printStackTrace();
        }
    }
}

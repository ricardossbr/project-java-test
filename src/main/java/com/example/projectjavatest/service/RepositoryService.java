package com.example.projectjavatest.service;

import com.example.projectjavatest.model.TeamAndUserModel;
import com.example.projectjavatest.model.TeamModel;
import com.example.projectjavatest.model.UserModel;
import com.example.projectjavatest.repository.TeamAndUserRepository;
import com.example.projectjavatest.repository.TeamsRepository;
import com.example.projectjavatest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class RepositoryService {
    @Autowired
    private TeamAndUserRepository teamAndUserRepository;

    @Autowired
    private TeamsRepository teamsRepository;

    @Autowired
    private UserRepository userRepository;

    public TeamAndUserModel saveTeamAndUserModel(TeamAndUserModel teamAndUser){
        return this.teamAndUserRepository.save(teamAndUser);
    }

    public List<TeamAndUserModel> getAllTeamAndUser() {
        return this.teamAndUserRepository.findAll();
    }
    public Optional<TeamAndUserModel> getTeamAndUserByTeam(String id) {
        return this.teamAndUserRepository.findById(id);
    }

    public Optional<UserModel> findUserBy(String id){
        return this.userRepository.findById(id);
    }

    public Optional<TeamModel> findTeamBy(String id){
        return this.teamsRepository.findById(id);
    }

    public TeamModel saveTeamModel(TeamModel team){
        return this.teamsRepository.save(new TeamModel(team.getId(), team.getName()));
    }

    public UserModel saveUserModel(UserModel user){
        return this.userRepository.save(new UserModel(user.getId(), user.getDisplayName()));
    }

    public void delete(TeamAndUserModel teamAndUserModel) {
        this.teamAndUserRepository.delete(teamAndUserModel);
    }
}

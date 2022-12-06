package com.example.projectjavatest.service;

import com.example.projectjavatest.dto.TeamAndUserDTO;
import com.example.projectjavatest.exception.TeamAndUserException;
import com.example.projectjavatest.exception.TheTeamException;
import com.example.projectjavatest.exception.TheTeamLeadException;
import com.example.projectjavatest.exception.TheUserException;
import com.example.projectjavatest.model.TeamAndUserModel;
import com.example.projectjavatest.model.TeamModel;
import com.example.projectjavatest.model.UserModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ServiceTest {

    @InjectMocks
    private Service service;

    @Mock
    private RepositoryService repositoryService;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test()
    public void when_call_get_team_and_user_should_be_ok() throws TeamAndUserException {
        Mockito.when(this.repositoryService.getTeamAndUserByTeam(Mockito.anyString())).thenReturn(this.getOptionalTeamAndUserModel());
        final TeamAndUserModel response =  this.service.getTeamAndUser("");
        assertEquals(response.getId() , "c99c850b-a442-41ac-b9bc-209b792d0574");
        assertEquals(response.getUsers().size() , 4);
    }

    @Test()
    public void when_call_get_team_and_user_should_be_not_ok(){
        Mockito.when(this.repositoryService.getTeamAndUserByTeam(Mockito.anyString())).thenReturn(Optional.empty());
        final String id = "test";
        try {
            this.service.getTeamAndUser(id);
        } catch (TeamAndUserException e) {
            assertEquals("This TeamAndUser: " + id + " does not exist yet, please save the TeamAndUser and return here to see it.", e.getMessage());
        }
    }

    @Test()
    public void when_call_save_team_and_user_should_be_ok() throws TheTeamException, TheTeamLeadException, TheUserException, TeamAndUserException {
        Mockito.when(this.repositoryService.findUserBy(Mockito.anyString())).thenReturn(this.getOptionalUserModel());
        Mockito.when(this.repositoryService.findTeamBy(Mockito.anyString())).thenReturn(this.getOptionalTeamModel());
        Mockito.when(this.repositoryService.getTeamAndUserByTeam(Mockito.anyString())).thenReturn(Optional.empty());
        Mockito.when(this.repositoryService.saveTeamAndUserModel(Mockito.any(TeamAndUserModel.class))).thenReturn(this.getOptionalTeamAndUserModel().get());
        final TeamAndUserModel response =  this.service.save(this.getTeamAndUserDTOOK());
        assertEquals(response.getId() , "c99c850b-a442-41ac-b9bc-209b792d0574");
        assertEquals(response.getUsers().size() , 4);
    }

    @Test()
    public void when_call_save_team_and_user_should_be_not_ok_Team_And_User_Exception() throws TheTeamException, TheTeamLeadException, TheUserException, TeamAndUserException {
        Mockito.when(this.repositoryService.findUserBy(Mockito.anyString())).thenReturn(this.getOptionalUserModel());
        Mockito.when(this.repositoryService.findTeamBy(Mockito.anyString())).thenReturn(this.getOptionalTeamModel());
        Mockito.when(this.repositoryService.getTeamAndUserByTeam(Mockito.anyString())).thenReturn(this.getOptionalTeamAndUserModel());
        try {
            final TeamAndUserModel response =  this.service.save(this.getTeamAndUserDTOOK());
        }catch (TeamAndUserException e){
            assertEquals("This TeamAndUser: " + this.getTeamAndUserDTOOK().getTeam() + " already exist, please use Put Method for change something in TeamAndUser.", e.getMessage());
        }
    }

    @Test()
    public void when_call_save_team_and_user_should_be_not_ok_The_User_Exception() throws TheTeamException, TheTeamLeadException, TeamAndUserException {
        Mockito.when(this.repositoryService.findUserBy(Mockito.anyString())).thenReturn(Optional.empty());
        try {
            final TeamAndUserModel response =  this.service.save(this.getTeamAndUserDTOOK());
        }catch (TheUserException e){
            assertEquals("The User: fd282131-d8aa-4819-b0c8-d9e0bfb1b75c not exist!", e.getMessage());
        }
    }


    @Test()
    public void when_call_edit_team_and_user_should_be_ok() throws TheTeamException, TheTeamLeadException, TheUserException, TeamAndUserException {
        Mockito.when(this.repositoryService.findUserBy(Mockito.anyString())).thenReturn(this.getOptionalUserModel());
        Mockito.when(this.repositoryService.findTeamBy(Mockito.anyString())).thenReturn(this.getOptionalTeamModel());
        Mockito.when(this.repositoryService.getTeamAndUserByTeam(Mockito.anyString())).thenReturn(Optional.empty());
        Mockito.when(this.repositoryService.saveTeamAndUserModel(Mockito.any(TeamAndUserModel.class))).thenReturn(this.getOptionalTeamAndUserModel().get());
        Mockito.when(this.repositoryService.getTeamAndUserByTeam("c99c850b-a442-41ac-b9bc-209b792d0574")).thenReturn(this.getOptionalTeamAndUserModel());
        final TeamAndUserModel response =  this.service.edit(this.getTeamAndUserDTOOK(), Mockito.anyString());
        assertEquals(response.getId() , "c99c850b-a442-41ac-b9bc-209b792d0574");
        assertEquals(response.getUsers().size() , 4);
    }

    @Test()
    public void when_call_edit_team_and_user_should_be_not_ok_Team_And_User_Exception() throws TheTeamException, TheTeamLeadException, TheUserException, TeamAndUserException {
        Mockito.when(this.repositoryService.findUserBy(Mockito.anyString())).thenReturn(this.getOptionalUserModel());
        Mockito.when(this.repositoryService.findTeamBy(Mockito.anyString())).thenReturn(this.getOptionalTeamModel());
        Mockito.when(this.repositoryService.getTeamAndUserByTeam(Mockito.anyString())).thenReturn(this.getOptionalTeamAndUserModel());
        try {
            final TeamAndUserModel response =  this.service.edit(this.getTeamAndUserDTOOK(), Mockito.anyString());
        }catch (TeamAndUserException e){
            assertEquals("This TeamAndUser: " + this.getTeamAndUserDTOOK().getTeam() + " already exist, please use Put Method for change something in TeamAndUser.", e.getMessage());
        }
    }

    @Test()
    public void when_call_edit_team_and_user_should_be_not_ok_The_User_Exception() throws TheTeamException, TheTeamLeadException, TeamAndUserException {
        Mockito.when(this.repositoryService.findUserBy(Mockito.anyString())).thenReturn(Optional.empty());
        try {
            final TeamAndUserModel response =  this.service.edit(this.getTeamAndUserDTOOK(), Mockito.anyString());
        }catch (TheUserException e){
            assertEquals("The User: fd282131-d8aa-4819-b0c8-d9e0bfb1b75c not exist!", e.getMessage());
        }
    }

    @Test()
    public void when_call_save_team_and_user_should_be_not_ok_The_Team_Lead_Exception() throws TheTeamException, TheUserException, TeamAndUserException {
        Mockito.when(this.repositoryService.findUserBy(Mockito.anyString())).thenReturn(this.getOptionalUserModel());
        try {
            final TeamAndUserModel response =  this.service.save(this.getTeamAndUserDTONOTOK());
        }catch (TheTeamLeadException e){
            assertEquals("The TeamLead: " + this.getTeamAndUserDTONOTOK().getTeamLead() + " need to be in Users List! please verify the correct teamLeadId and try again! :)", e.getMessage());
        }
    }

    @Test()
    public void when_call_delete_team_and_user_should_be_not_ok(){
        Mockito.when(this.repositoryService.getTeamAndUserByTeam(Mockito.anyString())).thenReturn(Optional.empty());
        final String id = "abc";
        try {
            this.service.deleteTeamAndUser(id);
        } catch (TeamAndUserException e) {
            assertEquals("This TeamAndUser: " + id + " does not exist yet, please save the TeamAndUser and return here to see it.", e.getMessage());
        }
    }


    private Optional<TeamAndUserModel> getOptionalTeamAndUserModel(){
        return Optional.of(new TeamAndUserModel(
                new TeamModel("c99c850b-a442-41ac-b9bc-209b792d0574", "Delicious Fuchsia Partridge"),
                Set.of(new UserModel("1b140966-5a01-49da-872e-71a769f98941", "carmeloStark"),
                        new UserModel("fd282131-d8aa-4819-b0c8-d9e0bfb1b75c", "gianniWehner"),
                        new UserModel("aa569071-6ade-4ff6-b567-6466fcbae74a", "brendenVolkman"),
                        new UserModel("fddcde65-70b2-49f9-8b4d-5126adc345c1", "marionKertzmann")),
                new UserModel("1b140966-5a01-49da-872e-71a769f98941", "carmeloStark")
        ));
    }

    private TeamAndUserDTO getTeamAndUserDTOOK(){
        return new TeamAndUserDTO("c99c850b-a442-41ac-b9bc-209b792d0574",
                Set.of("fd282131-d8aa-4819-b0c8-d9e0bfb1b75c"),
                "fd282131-d8aa-4819-b0c8-d9e0bfb1b75c"
                );
    }

    private TeamAndUserDTO getTeamAndUserDTONOTOK(){
        return new TeamAndUserDTO("c99c850b-a442-41ac-b9bc-209b792d0574",
                Set.of("fd282131-d8aa-4819-b0c8-d9e0bfb1b75c-a-a"),
                "fd282131-d8aa-4819-b0c8-d9e0bfb1b75c-a"
        );
    }

    private Optional<UserModel> getOptionalUserModel(){
        return Optional.of(new UserModel("fd282131-d8aa-4819-b0c8-d9e0bfb1b75c", "gianniWehner"));
    }

    private Optional<TeamModel> getOptionalTeamModel(){
        return Optional.of(new TeamModel("c99c850b-a442-41ac-b9bc-209b792d0574", "Delicious Fuchsia Partridge"));
    }

}

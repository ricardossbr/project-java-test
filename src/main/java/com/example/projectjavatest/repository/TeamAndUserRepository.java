package com.example.projectjavatest.repository;

import com.example.projectjavatest.model.TeamAndUserModel;
import com.example.projectjavatest.model.TeamModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamAndUserRepository extends MongoRepository<TeamAndUserModel, String> {
}

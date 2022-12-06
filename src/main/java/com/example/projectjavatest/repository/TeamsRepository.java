package com.example.projectjavatest.repository;

import com.example.projectjavatest.model.TeamModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamsRepository extends MongoRepository<TeamModel, String> {
}

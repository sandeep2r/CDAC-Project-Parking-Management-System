package com.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.parking.entities.Agent;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Integer> {

    
	@Query("select c from Agent c where c.email = :loginId and c.password = :password")
	Agent loginValidation(String loginId, String password);
}
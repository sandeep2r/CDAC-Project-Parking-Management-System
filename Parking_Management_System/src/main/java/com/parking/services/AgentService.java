package com.parking.services;

import java.util.List;

import com.parking.dto.AuthRequest;
import com.parking.entities.Agent;

public interface AgentService {

	List<Agent> getAllAgents();

	Agent getAgentById(Integer id);

	Agent addAgent(Agent agent);

	Agent updateAgent(Integer id, Agent agent);

	void deleteAgent(Integer id);

	Agent login(AuthRequest authRequest);

}

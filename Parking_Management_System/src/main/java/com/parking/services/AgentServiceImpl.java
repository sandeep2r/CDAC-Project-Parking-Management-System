package com.parking.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parking.dto.AuthRequest;
import com.parking.entities.Agent;
import com.parking.repository.AgentRepository;

@Service
@Transactional
public class AgentServiceImpl implements AgentService {

	@Autowired
	private AgentRepository agentRepository;

	@Override
	public Agent addAgent(Agent agent) {
		return agentRepository.save(agent);
	}

	@Override
	public List<Agent> getAllAgents() {
		return agentRepository.findAll();
	}

	@Override
	public Agent getAgentById(Integer id) {
		return agentRepository.findById(id).orElse(null);
	}

	@Override
	public Agent updateAgent(Integer id, Agent agent) {
		Agent existingAgent = agentRepository.findById(id).orElse(null);
		if (existingAgent != null) {
			existingAgent.setEmail(agent.getEmail());
			existingAgent.setFirstName(agent.getFirstName());
			existingAgent.setLastName(agent.getLastName());
			existingAgent.setPassword(agent.getPassword());
			existingAgent.setAddress(agent.getAddress());
			existingAgent.setMobileNumber(agent.getMobileNumber());
			existingAgent.setLoggedIn(agent.getLoggedIn());
			return agentRepository.save(existingAgent);
		} else {
			return null;
		}
	}

	@Override
	public void deleteAgent(Integer id) {
		agentRepository.deleteById(id);

	}

	@Override
	public Agent login(AuthRequest authRequest) {

		return agentRepository.loginValidation(authRequest.getLoginId(), authRequest.getPassword());
	}
}
package com.parking.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parking.entities.Agent;
import com.parking.services.AgentService;

@RestController
@RequestMapping("/agent")
@CrossOrigin(origins = "http://localhost:3000")
public class AgentController {

    @Autowired
    private AgentService agentService;

    @PostMapping("/add")
    public ResponseEntity<?> addAgent(@Valid @RequestBody Agent agent) {
    	return new ResponseEntity<>(agentService.addAgent(agent),HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAgentById(@PathVariable("id") Integer id) {
    	return new ResponseEntity<>(agentService.getAgentById(id), HttpStatus.OK);
    }

    @GetMapping("/list/updateAgent/{id}")
    public ResponseEntity<?> updateAgent(@PathVariable("id") Integer id, @Valid @RequestBody Agent agent) {
    	return new ResponseEntity<>(agentService.updateAgent(id,agent), HttpStatus.OK);
    }

    @GetMapping("/deleteAgent/{id}")
    public ResponseEntity<?> deleteAgent(@PathVariable Integer id) {
    	agentService.deleteAgent(id);
    	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAllAgents() {
    	return new ResponseEntity<>(agentService.getAllAgents(), HttpStatus.OK);
    }
}
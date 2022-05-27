package com.digi.leaves.consumer;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.digi.leaves.modelDTO.EmployeeDTO;
import com.digi.leaves.service.EmployeeService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EmployeeConsumer {
	
	@Autowired
	private EmployeeService empService;
	
	private ObjectMapper objectMapper = new ObjectMapper();

	@KafkaListener(topics = "t_employee")
	public void listenAll(String message) throws JsonParseException, JsonMappingException, IOException {
		EmployeeDTO empConsumed = objectMapper.readValue(message, EmployeeDTO.class);
		empService.saveConsumed(empConsumed);
	}

}

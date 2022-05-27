package com.digi.employee.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.digi.employee.modelDTO.EmployeeDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EmployeeProducer {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	private ObjectMapper objectMapper = new ObjectMapper();

	public void send(EmployeeDTO emp) {
		try {
			String json = objectMapper.writeValueAsString(emp);
			kafkaTemplate.send("t_employee", json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

}

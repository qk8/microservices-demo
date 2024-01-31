package com.demo.itemservice.producers;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.demo.itemservice.entities.Item;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

@Service
@AllArgsConstructor
public class ItemProducer {
	
	private final KafkaTemplate<String, String> kafkaTemplate;
	private final ObjectMapper objectMapper;
	
	@SneakyThrows
	public void sendItem(Item item) {
		kafkaTemplate.send("item", objectMapper.writeValueAsString(item));
	}

}

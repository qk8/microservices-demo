package com.demo.itemservice.listeners;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;

import com.demo.itemservice.entities.Item;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

@Controller
@AllArgsConstructor
public class ItemListener {
	
	private final ObjectMapper objectMapper;

    @KafkaListener(topics = "item")
    @SneakyThrows
    public void listenItem(final String event) {
        Item itemFromKafka = objectMapper.readValue(event, Item.class);
        System.out.println("<<<<<<<<<<<<<<MESSAGE FROM KAFKA: " + itemFromKafka);
    }

}

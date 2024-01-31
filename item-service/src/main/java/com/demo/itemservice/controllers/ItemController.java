package com.demo.itemservice.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.itemservice.models.requests.ItemRequest;
import com.demo.itemservice.models.responses.ItemResponse;
import com.demo.itemservice.services.ItemService;

@RestController
public class ItemController {
	
	private final ItemService itemService;
	
	
	public ItemController(ItemService itemService) {
		this.itemService = itemService;
	}
	
	@GetMapping()
	public List<ItemResponse> getAllItems() {
		
		return itemService.getAllItems();
		
	}
	
	
	@GetMapping("/{id}")
	public ItemResponse getItemById(@PathVariable("id") Long id) {
		
		return itemService.getItemById(id);
		
	}
	
	
	@PostMapping()
	public ItemResponse createItem(@RequestBody ItemRequest itemRequest) {
				
		return itemService.createItem(itemRequest);
		
	}
	
	
	@PutMapping("/{id}")
	public ItemResponse updateItem(@PathVariable("id") Long id, @RequestBody ItemRequest ItemRequest) {
		
		return itemService.updateItem(id, ItemRequest);
		
	}
	
	
	@DeleteMapping("/{id}")
	public void deleteItem(@PathVariable("id") Long id) {
		
		itemService.deleteItem(id);
		
	}
	
	@GetMapping("/sendItem/{id}")
	public void sendItem(@PathVariable("id") Long id) {
		
		itemService.sendItem(id);
	}

}







package com.demo.itemservice.services;

import java.util.List;

import com.demo.itemservice.models.requests.ItemRequest;
import com.demo.itemservice.models.responses.ItemResponse;

public interface ItemService {
	
	List<ItemResponse> getAllItems();
	
	ItemResponse getItemById(Long id);
	
	ItemResponse createItem(ItemRequest item);
	
	ItemResponse updateItem(Long id, ItemRequest item);
	
	void deleteItem(Long id);
	
	void sendItem(Long id);
}

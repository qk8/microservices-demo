package com.demo.itemservice.services.implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.demo.itemservice.entities.Item;
import com.demo.itemservice.mappers.ItemMapper;
import com.demo.itemservice.models.requests.ItemRequest;
import com.demo.itemservice.models.responses.ItemResponse;
import com.demo.itemservice.producers.ItemProducer;
import com.demo.itemservice.repositories.ItemRepository;
import com.demo.itemservice.services.ItemService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ItemServiceImpl implements ItemService {

	private final ItemMapper itemMapper = ItemMapper.INSTANCE;

	private final ItemRepository itemRepository;

	private final RedisTemplate<String, ItemResponse> redisTemplate;
	
	private final ItemProducer itemProducer;
	
	public ItemServiceImpl(ItemRepository itemRepository, RedisTemplate<String, ItemResponse> redisTemplate, ItemProducer itemProducer) {
		this.itemRepository = itemRepository;
		this.redisTemplate = redisTemplate;
		this.itemProducer = itemProducer;
	}

	@Override
	public List<ItemResponse> getAllItems() {
		
		if(redisTemplate.hasKey("testList"))
			return redisTemplate.opsForList().range("testList", 0, -1);
				
		List<Item> allItems = itemRepository.findAll();
		List<ItemResponse> allItemResponses = allItems.stream().map(item -> itemMapper.itemToItemResponse(item)).collect(Collectors.toList());
		
		redisTemplate.opsForList().rightPushAll("testList", allItemResponses);
		
		return allItemResponses; 
	}

	@Override
	public ItemResponse getItemById(Long id) {
		
		if(redisTemplate.hasKey(id.toString()))
			return redisTemplate.opsForValue().get(id.toString());

		Item item = itemRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		ItemResponse itemResponse = itemMapper.itemToItemResponse(item);

		redisTemplate.opsForValue().set(id.toString(), itemResponse);
		
		return itemResponse;
	}

	@Override
	public ItemResponse createItem(ItemRequest itemRequest) {

		Item item = itemMapper.itemRequestToItem(itemRequest);
		Item createdItem = itemRepository.save(item);
		ItemResponse createdItemResponse = itemMapper.itemToItemResponse(createdItem);
		
		redisTemplate.delete("testList");

		return createdItemResponse;

	}

	@Override
	public ItemResponse updateItem(Long id, ItemRequest itemRequest) {

		Item item = itemMapper.itemRequestToItem(itemRequest);
		Item itemFromDB = itemRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		itemFromDB.setName(item.getName());
		itemFromDB.setCategory(item.getCategory());
		Item updatedItem = itemRepository.save(itemFromDB);
		ItemResponse updatedItemResponse = itemMapper.itemToItemResponse(updatedItem);
		
		redisTemplate.delete("testList");
		redisTemplate.delete(id.toString());

		return updatedItemResponse;
	}

	@Override
	public void deleteItem(Long id) {

		Item itemFromDB = itemRepository.findById(id).orElseThrow(EntityNotFoundException::new);

		itemRepository.delete(itemFromDB);
		
		redisTemplate.delete("testList");
		redisTemplate.delete(id.toString());

	}

	@Override
	public void sendItem(Long id) {
		
		Item itemFromDB = itemRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		
		itemProducer.sendItem(itemFromDB);
		
	}

	

}

package com.demo.itemservice.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.demo.itemservice.entities.Item;
import com.demo.itemservice.models.requests.ItemRequest;
import com.demo.itemservice.models.responses.ItemResponse;



@Mapper
public interface ItemMapper {
	
	ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);
	
	Item itemRequestToItem(ItemRequest itemRequest);
	ItemResponse itemToItemResponse(Item item);
	

}

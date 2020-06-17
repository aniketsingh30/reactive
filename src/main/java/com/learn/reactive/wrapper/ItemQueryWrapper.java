package com.learn.reactive.wrapper;

import org.springframework.beans.factory.annotation.Autowired;

import com.learn.reactive.bean.Item;
import com.learn.reactive.dao.IItemQueryDao;
import com.learn.reactive.document.ItemDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ItemQueryWrapper implements IItemQueryWrapper{
	@Autowired
	IItemQueryDao itemQueryDao;
	
	@Autowired
	ObjectMapper objectMapper;
	public Mono<String> getHello() throws Exception {
		return itemQueryDao.getHello();
	}
	@Override
	public Flux<Item> findAll() {
		Flux<ItemDTO>  itemDtoFlux = itemQueryDao.findAll();
		 return itemDtoFlux.map(itemDto->{
			Item item = objectMapper.convertValue(itemDto, Item.class);
			return item;
		});
	}
	@Override
	public Mono<Item> getItemById(String id) {
		Mono<ItemDTO>  itemDtoMono = itemQueryDao.findById(id);
		 return itemDtoMono.map(itemDto->{
			Item item = objectMapper.convertValue(itemDto, Item.class);
			return item;
		});
	}
	@Override
	public Mono<Item> getItemByDescription(String description) {
		Mono<ItemDTO>  itemDtoMono = itemQueryDao.findItemByDescription(description);
		 return itemDtoMono.map(itemDto->{
			Item item = objectMapper.convertValue(itemDto, Item.class);
			return item;
		});
	}
}

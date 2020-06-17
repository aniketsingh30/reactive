package com.learn.reactive.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.learn.reactive.bean.Item;
import com.learn.reactive.bean.SubItem;
import com.learn.reactive.wrapper.IItemQueryWrapper;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ItemQueryService implements IItemQueryService {
	@Autowired
	IItemQueryWrapper itemQueryWrapper;

	public Mono<String> getHello() throws Exception {
		return itemQueryWrapper.getHello();
	}

	@Override
	public Mono<ResponseEntity<OrderResponseService>> findAll() {
		Flux<Item> itemFlux = itemQueryWrapper.findAll();
		return itemFlux.collectList().map(itemList -> {
			OrderResponseService orderResponseService = new OrderResponseService();
			orderResponseService.setResponse(itemList);
			orderResponseService.setStatusCode("200");
			return (ResponseEntity.ok().body(orderResponseService));
		});

	}

	@Override
	public Mono<ResponseEntity<OrderResponseService>> getItemById(String id) {
		Mono<Item> itemMono = itemQueryWrapper.getItemById(id);
		return itemMono.map(item -> {
			OrderResponseService orderResponseService = new OrderResponseService();
			orderResponseService.setResponse(item);
			orderResponseService.setStatusCode("200");
			return (ResponseEntity.ok().body(orderResponseService));
		});

	}

	@Override
	public Mono<ResponseEntity<OrderResponseService>> getItemByDescription(String description) {
		Mono<Item> itemMono = itemQueryWrapper.getItemByDescription(description);
		return itemMono.map(item -> {
			OrderResponseService orderResponseService = new OrderResponseService();
			orderResponseService.setResponse(item);
			orderResponseService.setStatusCode("200");
			return (ResponseEntity.ok().body(orderResponseService));
		});

	}

	// return flux from mono
	@Override
	public Flux<SubItem> findSubItemsById(String id) {
		Mono<Item> itemMono = itemQueryWrapper.getItemById(id);
		return itemMono.map(item -> {
			return item.getSubitem();
		}).flatMapMany(Flux::fromIterable);

	}
}

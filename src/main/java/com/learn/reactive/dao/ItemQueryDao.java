package com.learn.reactive.dao;

import org.springframework.beans.factory.annotation.Autowired;

import com.learn.reactive.document.ItemDTO;
import com.learn.reactive.repository.IItemQueryRepoitory;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ItemQueryDao implements IItemQueryDao{
	@Autowired
	IItemQueryRepoitory itemQueryRepoitory;
	public Mono<String> getHello() {
		return Mono.just("Hello, Aniket!");
	}

	@Override
	public Flux<ItemDTO> findAll() {
		return itemQueryRepoitory.findAll();
	}

	@Override
	public Mono<ItemDTO> findById(String id) {
		return itemQueryRepoitory.findById(id);
	}

	@Override
	public Mono<ItemDTO> findItemByDescription(String description) {
		return itemQueryRepoitory.findItemByDescription(description);
	}
}

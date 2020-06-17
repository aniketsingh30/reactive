package com.learn.reactive.dao;

import org.springframework.stereotype.Component;

import com.learn.reactive.document.ItemDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public interface IItemQueryDao {
	public Mono<String> getHello() throws Exception ;

	public Flux<ItemDTO> findAll();


	public Mono<ItemDTO> findItemByDescription(String description);

	public Mono<ItemDTO> findById(String id);
	
}

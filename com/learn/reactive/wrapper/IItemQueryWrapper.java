package com.example.juint.wrapper;

import org.springframework.stereotype.Component;

import com.example.juint.bean.Item;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public interface IItemQueryWrapper {
	public Mono<String> getHello() throws Exception ;

	public Flux<Item> findAll();

	public Mono<Item> getItemById(String id);

	public Mono<Item> getItemByDescription(String description);

	
}

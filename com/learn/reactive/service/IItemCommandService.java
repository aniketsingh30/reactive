package com.example.juint.service;

import org.springframework.http.ResponseEntity;

import com.example.juint.bean.Item;

import reactor.core.publisher.Mono;

public interface IItemCommandService {
	public Mono<ResponseEntity<Item>> save(Item item) throws Exception;
	public Mono<ResponseEntity<Item>> update(Item item,String id) throws Exception;
	public Mono<Void> deleteById(String id) throws Exception;

}

package com.example.juint.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.juint.bean.SubItem;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface IItemQueryService {
	public Mono<String> getHello() throws Exception ;

	public Mono<ResponseEntity<OrderResponseService>> findAll();

	public Mono<ResponseEntity<OrderResponseService>> getItemById(String id);

	public Mono<ResponseEntity<OrderResponseService>> getItemByDescription(String description);

	public Flux<SubItem> findSubItemsById(String id);

	
}

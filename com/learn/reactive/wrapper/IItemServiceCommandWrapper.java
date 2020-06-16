package com.example.juint.wrapper;

import com.example.juint.bean.Item;

import reactor.core.publisher.Mono;

public interface IItemServiceCommandWrapper {
	public Mono<Item> save(Item item) throws Exception;
	public Mono<Item> update(Item item, String id) throws Exception;
	public Mono<Void> deleteById(String id) throws Exception;

}

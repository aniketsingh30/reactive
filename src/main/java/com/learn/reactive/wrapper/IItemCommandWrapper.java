package com.learn.reactive.wrapper;

import com.learn.reactive.bean.Item;

import reactor.core.publisher.Mono;

public interface IItemCommandWrapper {
	public Mono<Item> save(Item item) throws Exception;
	public Mono<Item> update(Item item, String id) throws Exception;
	public Mono<Void> deleteById(String id) throws Exception;

}

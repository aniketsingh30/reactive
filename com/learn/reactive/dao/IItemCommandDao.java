package com.example.juint.dao;

import com.example.juint.document.ItemDTO;

import reactor.core.publisher.Mono;

public interface IItemCommandDao {
	public Mono<ItemDTO> save(ItemDTO item) throws Exception;
	public Mono<ItemDTO> update(ItemDTO item) throws Exception;
	public Mono<Void> deleteById(String id) throws Exception;

}

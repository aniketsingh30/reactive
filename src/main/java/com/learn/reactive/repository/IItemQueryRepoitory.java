package com.learn.reactive.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.learn.reactive.document.ItemDTO;

import reactor.core.publisher.Mono;

@Repository
public interface IItemQueryRepoitory extends ReactiveMongoRepository<ItemDTO, String> {
	Mono<ItemDTO> findItemByDescription(String description);

}

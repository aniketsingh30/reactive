package com.learn.reactive.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.learn.reactive.document.ItemDTO;

@Repository
@Qualifier("itemCommandRepository")
public interface IItemCommandRepository  extends ReactiveMongoRepository<ItemDTO, String>{}


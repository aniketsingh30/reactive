package com.example.juint.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.example.juint.document.ItemDTO;

@Repository
public interface IItemCommandRepository  extends ReactiveMongoRepository<ItemDTO, String>{}


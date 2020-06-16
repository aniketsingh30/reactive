package com.example.juint.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.juint.bean.Item;
import com.example.juint.wrapper.IItemServiceCommandWrapper;

import reactor.core.publisher.Mono;

@Service
public class ItemCommandService implements IItemCommandService {

	@Autowired
	IItemServiceCommandWrapper itemCommandServiceCommandWrapper;

	@Override
	public Mono<ResponseEntity<Item>> save(Item item) throws Exception {
		return itemCommandServiceCommandWrapper.save(item).map(currentItem->{
			return ResponseEntity.ok().body(currentItem);
		});
	}

	@Override
	public Mono<ResponseEntity<Item>> update(Item item, String id) throws Exception {
		return itemCommandServiceCommandWrapper.update(item, id).map(currentItem->{
			return ResponseEntity.ok().body(currentItem);
		});

	}

	@Override
	public Mono<Void> deleteById(String id) throws Exception {
		return itemCommandServiceCommandWrapper.deleteById(id);
	}

}
